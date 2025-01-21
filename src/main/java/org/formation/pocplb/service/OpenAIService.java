package org.formation.pocplb.service;


import lombok.RequiredArgsConstructor;
import org.formation.pocplb.model.Answer;
import org.formation.pocplb.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.elasticsearch.ElasticsearchVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Service
public class OpenAIService  {

    private final ChatModel chatModel;

    private final ElasticsearchVectorStore elasticsearchVectorStore;

    @Value("classpath:templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    public Answer getAnswer(Question question) {

        List<Document> results = this.elasticsearchVectorStore.similaritySearch(SearchRequest.builder().query(question.question()).topK(5).build());
        List<String> resultStrings = results.stream().map(Document::getContent).toList();

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create(Map.of("input", question.question(), "documents", String.join("\n", resultStrings)));
        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }

    public Answer getSimpleAnswer(Question question) {

        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }
}
