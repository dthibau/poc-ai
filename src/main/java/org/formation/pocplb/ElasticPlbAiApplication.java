package org.formation.pocplb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.autoconfigure.vectorstore.chroma.ChromaVectorStoreAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("elastic")
@SpringBootApplication(exclude = ChromaVectorStoreAutoConfiguration.class)
@Slf4j
public class ElasticPlbAiApplication {

}
