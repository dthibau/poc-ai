package org.formation.pocplb.controllers;

import org.formation.pocplb.model.Answer;
import org.formation.pocplb.model.Question;
import org.formation.pocplb.service.ChatClientService;
import org.formation.pocplb.service.ChatModelService;
import org.formation.pocplb.service.IndexingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class QuestionControllerTest {

    @Mock
    private ChatClientService chatClientService;

    @Mock
    private ChatModelService chatModelService;

    @Mock
    private IndexingService indexingService;

    @InjectMocks
    private QuestionController questionController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    @Test
    void testFormatMessage() {
        // Test the private formatMessage method
        String result = questionController.formatMessage("Test **bold** text\n### Heading");
        
        // Validate HTML formatting
        assert(result.contains("&nbsp;"));
        assert(result.contains("<b>"));
        assert(result.contains("</b>"));
        assert(result.contains("<h3>"));
    }

    @Test
    void testAskQuestionWithBody() throws Exception {
        // Given
        Question question = new Question("Test instruction", "Java developer");
        Answer expectedAnswer = new Answer("doc1", "Test answer");
        
        when(chatModelService.getAnswer(any(Question.class))).thenReturn(expectedAnswer);

        // When/Then
        mockMvc.perform(post("/askWithInstruction")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"instruction\":\"Test instruction\",\"profil\":\"Java developer\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.refFound").value("doc1"))
                .andExpect(jsonPath("$.answer").value("Test answer"));

        verify(chatModelService, times(1)).getAnswer(any(Question.class));
    }

    @Test
    void testReindex() throws Exception {
        // When/Then
        mockMvc.perform(post("/reindex"))
                .andExpect(status().isOk());

        verify(indexingService, times(1)).index();
    }

    @Test
    void testIndexFormation() throws Exception {
        // When/Then
        mockMvc.perform(post("/index/{ref}", "SPG"))
                .andExpect(status().isOk());

        verify(indexingService, times(1)).indexFormation("SPG");
    }
}