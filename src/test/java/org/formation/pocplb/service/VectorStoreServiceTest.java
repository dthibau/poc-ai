package org.formation.pocplb.service;

import org.formation.pocplb.controllers.CatalogueController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("chroma")
public class VectorStoreServiceTest {

    @Autowired
    private VectorStoreService vectorStoreService;

    @Test
    void testSearchTopN() {
        // Given
        String query = "Formation Jenkins : Intégration Continue" +
                "Compréhension Approfondie de Jenkins\n" +
                "Maîtrise des Pipelines Jenkins\n" +
                "Automatisation et Scripting avec Groovy\n" +
                "Gestion Avancée de Jenkins\n" +
                "Application Pratique";

        List<Document> docs = vectorStoreService.searchTopN(query,1);
        assertThat(docs.get(0).getScore()).isGreaterThan(CatalogueController.SEUIL_PERTINENCE);

        query ="Préparation mafé bœuf  Coupez vos tomates en dés, épluchez vos pommes de terre et carottes et coupez-les en deux, épluchez votre pate douce et coupez-la en gros morceaux, puis émincez vos oignons et le piment.  Coupez votre viande en petits cubes et faites-la revenir dans une marmite avec de l’huile et les oignons émincés jusqu’à coloration.  Ajoutez les tomates coupées en dés. Ajoutez 4 cuillères à soupe de sauce tomate. Laissez cuire à feu moyen 2 à 3 minutes puis recouvrez d’eau.";
        docs = vectorStoreService.searchTopN(query,1);
        assertThat(docs.get(0).getScore()).isLessThan(CatalogueController.SEUIL_PERTINENCE);


    }


}