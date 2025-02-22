package org.formation.pocplb.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.formation.pocplb.model.Answer;
import org.formation.pocplb.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class ChatModelService {

    private final ChatModel chatModel;
    private final VectorStoreService vectorStoreService;

    @Value("classpath:templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    public Answer getAnswer(Question question) {


        List<Document> results = vectorStoreService.searchTopN(question.profil(), 3);

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create(
                Map.of("input", question.profil(), "instruction", question.instruction(),"documents", String.join("\n", results.stream().map(Document::getText).toList())));
        ChatResponse response = chatModel.call(prompt);


            return new Answer(results.stream().map(d -> d.getId()).collect(Collectors.joining(", ")),
                    response.getResult().getOutput().getText());


    }

    public Answer getSimpleAnswer(Question question) {

        PromptTemplate promptTemplate = new PromptTemplate(question.profil());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);

        return new Answer("",response.getResult().getOutput().getText());
    }
}
