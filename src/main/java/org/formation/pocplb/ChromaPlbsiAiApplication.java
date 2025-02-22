package org.formation.pocplb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.autoconfigure.vectorstore.elasticsearch.ElasticsearchVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("chroma")
@SpringBootApplication(exclude = ElasticsearchVectorStoreAutoConfiguration.class)
@Slf4j
public class ChromaPlbsiAiApplication {


	public static void main(String[] args) {
		SpringApplication.run(ChromaPlbsiAiApplication.class, args);
	}

}
