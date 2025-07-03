package org.formation.pocplb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class VectorStoreService {

    private final VectorStore vectorStore;

    public VectorStoreService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }
    public List<Document> searchTopN(String query, int n) {
        log.info("Starting similarity search");
        List<Document> results = this.vectorStore.similaritySearch(SearchRequest.builder().query(query).topK(n).build());
        log.info("Found {} documents", results.size());
        return results;
    }
}
