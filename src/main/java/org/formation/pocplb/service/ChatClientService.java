package org.formation.pocplb.service;

import lombok.extern.slf4j.Slf4j;
import org.formation.pocplb.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

@Service
@Slf4j
public class ChatClientService {

    private final ChatClient chatClient;
    private final VectorStoreService vectorStoreService;

    @Value("classpath:templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;
    @Value("classpath:templates/catalogue-plbsi-template.st")
    private Resource cataloguePromptTemplate;

    public ChatClientService(ChatClient.Builder chatClientBuilder, VectorStoreService vectorStoreService, AITools aiTools, ChatMemory chatMemory) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory),
                        new SimpleLoggerAdvisor())
                .defaultTools(aiTools)
                .build();
        this.vectorStoreService = vectorStoreService;
    }

    public Flux<String> getCatalogue(String instruction, String uuid) {
        PromptTemplate promptTemplate = new PromptTemplate(cataloguePromptTemplate);

        return this.chatClient.prompt(promptTemplate.create(
                        Map.of("instruction", instruction),
                        OpenAiChatOptions.builder()
                                .build()))
                .advisors(a -> a
                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, uuid)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .stream()
                .content();
    }
    public Flux<String> getAnswer(Question question, String uuid) {

        List<Document> results = vectorStoreService.searchTopN(question.profil(), 3);
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        return this.chatClient.prompt(promptTemplate.create(
                        Map.of("input", question.profil(), "instruction", question.instruction(),"documents", String.join("\n", results.stream().map(Document::getText).toList())),
                        OpenAiChatOptions.builder()
                                .build()))
                .advisors(a -> a
                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, uuid)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .stream()
                .content();
    }
    public Flux<String> converse(String userMessage, String uuid) {

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        return this.chatClient.prompt()
                .user(userMessage)
                .advisors(a -> a
                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, uuid)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 100))
                .stream()
                .content();

    }

}
