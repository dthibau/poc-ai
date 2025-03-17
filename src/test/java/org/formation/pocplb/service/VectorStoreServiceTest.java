package org.formation.pocplb.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VectorStoreServiceTest {

    @Mock
    private VectorStore vectorStore;

    @InjectMocks
    private VectorStoreService vectorStoreService;

    @Test
    void testSearchTopN() {
        // Given
        String query = "Java programming";
        int topK = 3;
        
        Document doc1 = new Document("doc1", "Java Formation", java.util.Map.of());
        Document doc2 = new Document("doc2", "Spring Boot Formation", java.util.Map.of());
        Document doc3 = new Document("doc3", "JavaScript Formation", java.util.Map.of());
        
        List<Document> expectedDocs = Arrays.asList(doc1, doc2, doc3);
        
        when(vectorStore.similaritySearch(any(SearchRequest.class))).thenReturn(expectedDocs);

        // When
        List<Document> result = vectorStoreService.searchTopN(query, topK);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(doc1, doc2, doc3);
    }

    @Test
    void testSearchTopNWithEmptyResults() {
        // Given
        String query = "Non-existent topic";
        int topK = 5;
        
        List<Document> emptyResult = List.of();
        when(vectorStore.similaritySearch(any(SearchRequest.class))).thenReturn(emptyResult);

        // When
        List<Document> result = vectorStoreService.searchTopN(query, topK);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}