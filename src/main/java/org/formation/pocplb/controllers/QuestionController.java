package org.formation.pocplb.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.formation.pocplb.model.Answer;
import org.formation.pocplb.model.Question;
import org.formation.pocplb.service.ChatClientService;
import org.formation.pocplb.service.IndexingService;
import org.formation.pocplb.service.ChatModelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "OpenAI", description = "Endpoints pour interagir avec OpenAI et recommander des formations")
public class QuestionController {

    public static String DEFAULT_INSTRUCTION = """
    Tu es un assistant qui recommande à un utilisateur les formations adpatées en fonction de la description de son profil.
    Essaie de lui proposer une de ces formations en argumentant ton choix et en comparant les différentes formations possibles.
    Essaie d'être vendeur.
    Tu peux éventuellement mixer les contenus des formations.
    """;
    private final ChatClientService chatClientService;
    private final ChatModelService chatModelService;
    private final IndexingService indexingService;

    @PostMapping("/reindex")
    @Operation(
            summary = "Réindexe les formations dans le moteur de recherche",
            description = "Réindexe les formations dans le moteur de recherche pour les rendre disponibles pour les recommandations",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Les formations ont été réindexées avec succès"
                    ),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public void reindex() {
        indexingService.index();
    }

    @PostMapping("/index/{ref}")
    @Operation(
            summary = "Indexe une formation dans le moteur de recherche",
            description = "Indexe une formation dans le moteur de recherche pour la rendre disponible pour les recommandations",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "La formation a été indexée avec succès"
                    ),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public void indexFormation(String ref) {
        indexingService.indexFormation(ref);
    }

    @PostMapping("/ask")
    @Operation(
            summary = "Pose une question à OpenAI pour recommander des formations",
            description = "Envoie le profil d'un utilisateur à OpenAI et récupère une réponse avec les formations adaptées.\nLes instructions par défaut sont : \n" +
                    "Tu es un assistant qui recommande à un utilisateur les formations adpatées en fonction de la description de son profil.\n",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Profil de l'utilisateur sous forme de texte",
                    required = true,
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Exemple de profil",
                                    value = "Ingénieur logiciel avec 5 ans d'expérience en Java et Spring Boot"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Réponse de OpenAI contenant les formations recommandées",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Answer.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Requête invalide"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public Answer askQuestion(@RequestBody String profil) {
        Question q = new Question(DEFAULT_INSTRUCTION, profil);
        return chatClientService.getAnswer(q);
    }

    @PostMapping("/askWithInstruction")
    @Operation(
            summary = "Pose une question à OpenAI avec des instructions spécifiques",
            description = "Envoie une question avec des instructions et un profil utilisateur à OpenAI pour obtenir des formations adaptées.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objet contenant les instructions et le profil utilisateur",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Question.class),
                            examples = @ExampleObject(
                                    name = "Exemple de question",
                                    value = "{ \"instructions\": \"Recommander 3 formations pertinentes\", \"profil\": \"Développeur Java avec 3 ans d'expérience\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Réponse de OpenAI contenant les formations recommandées",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Answer.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Requête invalide"),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
            }
    )
    public Answer askQuestion(@RequestBody Question question) {
        return chatModelService.getAnswer(question);
    }

}
