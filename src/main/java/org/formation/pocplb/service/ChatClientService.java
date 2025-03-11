package org.formation.pocplb.service;

import lombok.extern.slf4j.Slf4j;
import org.formation.pocplb.model.Answer;
import org.formation.pocplb.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChatClientService {

    private final ChatClient chatClient;
    private final VectorStoreService vectorStoreService;

    @Value("classpath:templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    public ChatClientService(ChatClient.Builder chatClientBuilder, VectorStoreService vectorStoreService, SessionTools sessionTools) {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultTools(sessionTools)
                .build();
        this.vectorStoreService = vectorStoreService;
    }

    public Flux<String> getAnswer(Question question) {

        List<Document> results = vectorStoreService.searchTopN(question.profil(), 3);
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        return this.chatClient.prompt(promptTemplate.create(
                        Map.of("input", question.profil(), "instruction", question.instruction(),"documents", String.join("\n", results.stream().map(Document::getText).toList())),
                        OpenAiChatOptions.builder()
                                .build()))
                .stream()
                .content();




    }

}
