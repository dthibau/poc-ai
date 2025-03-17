package org.formation.pocplb.service;

import org.formation.pocplb.model.Answer;
import org.formation.pocplb.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.document.Document;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChatModelServiceTest {

    @Mock
    private ChatModel chatModel;

    @Mock
    private VectorStoreService vectorStoreService;

    @InjectMocks
    private ChatModelService chatModelService;

    @Test
    void testGetAnswer() {
        // Given
        Question question = new Question("Test instruction", "Java developer");
        Document doc1 = new Document("doc1", "Java Formation", java.util.Map.of());
        Document doc2 = new Document("doc2", "Spring Boot Formation", java.util.Map.of());
        List<Document> documents = Arrays.asList(doc1, doc2);
        
        ChatResponse mockResponse = createMockChatResponse("This is a test answer");
        
        when(vectorStoreService.searchTopN("Java developer", 3)).thenReturn(documents);
        when(chatModel.call(any(Prompt.class))).thenReturn(mockResponse);
        
        // Set the private field for the prompt template using reflection
        ReflectionTestUtils.setField(chatModelService, "ragPromptTemplate", 
            new org.springframework.core.io.ByteArrayResource("Instruction: {{instruction}}\nInput: {{input}}\nDocuments: {{documents}}".getBytes()));

        // When
        Answer answer = chatModelService.getAnswer(question);

        // Then
        assertThat(answer).isNotNull();
        assertThat(answer.refFound()).isEqualTo("doc1, doc2");
        assertThat(answer.answer()).isEqualTo("This is a test answer");
    }

    @Test
    void testGetSimpleAnswer() {
        // Given
        Question question = new Question("Test instruction", "Simple question");
        ChatResponse mockResponse = createMockChatResponse("Simple answer");
        
        when(chatModel.call(any(Prompt.class))).thenReturn(mockResponse);

        // When
        Answer answer = chatModelService.getSimpleAnswer(question);

        // Then
        assertThat(answer).isNotNull();
        assertThat(answer.refFound()).isEmpty();
        assertThat(answer.answer()).isEqualTo("Simple answer");
    }
    
    private ChatResponse createMockChatResponse(String responseText) {
        ChatResponse mockResponse = mock(ChatResponse.class);
        Generation generation = mock(Generation.class);
        AssistantMessage assistantMessage = mock(AssistantMessage.class);
        
        when(mockResponse.getResult()).thenReturn(generation);
        when(generation.getOutput()).thenReturn(assistantMessage);
        when(assistantMessage.getText()).thenReturn(responseText);
        
        return mockResponse;
    }
}
