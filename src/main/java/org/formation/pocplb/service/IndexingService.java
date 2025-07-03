package org.formation.pocplb.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.formation.pocplb.model.views.FormationViews;
import org.formation.pocplb.repository.FormationRepository;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class IndexingService {

    private final FormationRepository formationRepository;
    private final ObjectMapper objectMapper;
    private final VectorStore vectorStore;



    public IndexingService(FormationRepository formationRepository, ObjectMapper objectMapper, VectorStore vectorStore) {
        this.formationRepository = formationRepository;
        this.objectMapper = objectMapper;
        this.vectorStore = vectorStore;
    }

    public void index() {
        formationRepository.findAllUnarchived().forEach(formation -> {
            // log.info("Loading document: {}",formation.getReference());
            Document document = null;
            try {
                int nbTokens = objectMapper.writeValueAsString(formation).split("\\s+").length;
                if (nbTokens > 3000) {
                    log.info("Document {} is too big ",objectMapper.writeValueAsString(formation));
                    log.warn("Document {} is too big {}",formation.getReference(),nbTokens);
                }
                document = new Document(formation.getReference(),objectMapper.writerWithView(FormationViews.SimpleView.class).writeValueAsString(formation), Map.of("type","formation_simple"));
                vectorStore.add(Collections.singletonList(document));
                log.info("Storing {}",formation.getReference());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Index rag_index imported");
    }

    public void indexFormation(String reference) {
        formationRepository.findByReference(reference).ifPresent(formation -> {
            Document document = null;
            try {
                int nbTokens = objectMapper.writeValueAsString(formation).split("\\s+").length;
                if (nbTokens > 3000) {
                    log.info("Document {} is too big ",objectMapper.writeValueAsString(formation));
                    log.warn("Document {} is too big {}",formation.getReference(),nbTokens);
                }
                document = new Document(formation.getReference(),objectMapper.writeValueAsString(formation), Map.of("type","formation"));
                vectorStore.add(Collections.singletonList(document));
                log.info("Storing {}",formation.getReference());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
