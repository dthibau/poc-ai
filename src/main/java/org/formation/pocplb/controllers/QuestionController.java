package org.formation.pocplb.controllers;


import lombok.RequiredArgsConstructor;
import org.formation.pocplb.model.Answer;
import org.formation.pocplb.model.Question;
import org.formation.pocplb.service.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @PostMapping("/simpleask")
    public Answer askSimpleQuestion(@RequestBody Question question) {
        return openAIService.getSimpleAnswer(question);
    }
}
