package org.formation.pocplb.service;

import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Profile("chroma")
public class ChromaService {


    private static final String CHROMA_COLLECTIONS = "/api/v1/collections";
    private static final String COUNT_COLLECTION = "/api/v1/collections/{collectionId}/count";

    private ChromaCollection chromaCollection;

    private final RestClient restClient;

    public ChromaService(@Value("${spring.ai.vectorstore.chroma.client.host}") String chromaHost,
                         @Value("${spring.ai.vectorstore.chroma.client.port}") String chromaPort) {
        this.restClient = createRestClient(chromaHost, chromaPort);
        ResponseEntity<ChromaCollection[]> response = restClient.get()
                .uri(CHROMA_COLLECTIONS)
                .retrieve()
                .toEntity(ChromaCollection[].class);
        chromaCollection = response.getBody()[0];
    }
    
    // Protected for testing
    protected RestClient createRestClient(String host, String port) {
        return RestClient.builder()
                .baseUrl(host + ":" + port)
                .build();
    }
    
    public int countDocuments() {
        return restClient.get()
                .uri(COUNT_COLLECTION, chromaCollection.id())
                .retrieve()
                .toEntity(Integer.class)
                .getBody();
    }
}
