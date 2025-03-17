package org.formation.pocplb.config;

import org.formation.pocplb.repository.FormationRepository;
import org.formation.pocplb.repository.SessionRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.mockito.Mockito;

import java.util.List;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    @Primary
    public VectorStore mockVectorStore() {
        VectorStore mock = Mockito.mock(VectorStore.class);
        Mockito.when(mock.similaritySearch(Mockito.any())).thenReturn(List.of());
        return mock;
    }
    
    @Bean
    @Primary
    public ChatModel mockChatModel() {
        return Mockito.mock(ChatModel.class);
    }
    
    @Bean
    @Primary
    public ChatClient.Builder mockChatClientBuilder() {
        ChatClient mockChatClient = Mockito.mock(ChatClient.class);
        ChatClient.Builder mockBuilder = Mockito.mock(ChatClient.Builder.class);
        Mockito.when(mockBuilder.build()).thenReturn(mockChatClient);
        return mockBuilder;
    }
    
    @Bean
    @Primary
    public FormationRepository mockFormationRepository() {
        return Mockito.mock(FormationRepository.class);
    }
    
    @Bean
    @Primary
    public SessionRepository mockSessionRepository() {
        return Mockito.mock(SessionRepository.class);
    }
}