package org.formation.pocplb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.autoconfigure.vectorstore.elasticsearch.ElasticsearchVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PlbsiAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlbsiAiApplication.class, args);
	}

}
