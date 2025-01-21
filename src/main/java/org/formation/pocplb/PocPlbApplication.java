package org.formation.pocplb;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.cat.indices.IndicesRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.formation.pocplb.repository.FormationRepository;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.elasticsearch.ElasticsearchVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@Slf4j
public class PocPlbApplication implements CommandLineRunner {

    @Autowired
    private FormationRepository formationRepository;

	@Autowired
	ElasticsearchClient client;

	@Autowired
	ElasticsearchVectorStore vectorStore;

	@Autowired
	ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(PocPlbApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {


		Optional<IndicesRecord> index = client.cat().indices().valueBody().stream().filter(s ->
						s.index().equals("poc-plb"))
				.findFirst();
		if (index.isEmpty() || index.get().docsCount().equals("0") ) {
			log.info("Index {} does not exist. Creating and importing index","poc_plb");
			AtomicInteger i = new AtomicInteger();
			i.set(1);
			formationRepository.findByCategorieId(1500016).forEach(formation -> {
				log.info("Loading document: {}",formation.getReference());
                Document document = null;
                try {
					log.info("Content {}",objectMapper.writeValueAsString(formation));
                    document = new Document(formation.getReference(),objectMapper.writeValueAsString(formation), Map.of("type","formation"));
					vectorStore.add(Collections.singletonList(document));
					log.info("Storing {}",formation.getReference());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
			});
			log.info("Index rag_index imported");

		}

	}
}
