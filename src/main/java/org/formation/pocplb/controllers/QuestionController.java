package org.formation.pocplb.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.formation.pocplb.model.Answer;
import org.formation.pocplb.model.Question;
import org.formation.pocplb.service.ChatClientService;
import org.formation.pocplb.service.IndexingService;
import org.formation.pocplb.service.ChatModelService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@RestController
@Slf4j
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

    private boolean openB;
    private boolean openH3;

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
    public void indexFormation(@PathVariable String ref) {
        indexingService.indexFormation(ref);
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
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
    public Flux<String> askQuestion(@RequestParam String message, @RequestParam String uuid) {
        Question q = new Question(DEFAULT_INSTRUCTION, message);
        openB = true;
        openH3 = true;
        return chatClientService.getAnswer(q, uuid).map(this::formatMessage);
    }

    @GetMapping(value = "/converse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> converse(@RequestParam String message, @RequestParam String uuid) {
        openB = true;
        openH3 = true;
        return chatClientService.converse(message, uuid).map(this::formatMessage);
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

    // Made public for testing
    public String formatMessage(String message) {
        log.debug("Original message : {}", message);
        message = message.replace(" ", "&nbsp;");
        // Remplacer les retours à la ligne par <br />
        message = message.replace("\n", "<br />");
        log.debug("Space and CR : {}", message);
        // Transformer le markdown en HTML basique (gras et italique)
        int index=0;
        while((index = message.indexOf("**", index)) != -1) {
            String tag = openB ? "<b>" : "</b>";
            message = message.substring(0, index) + tag + message.substring(index+2);
            log.info("Current message : {}", message);
            openB = !openB;
        }
        log.debug("Bold : {}", message);

        index = 0;
        String lookFor = openH3 ? "###" : "<br />";
        while((index = message.indexOf(lookFor, index)) != -1) {
            if ( openH3) {
                message = message.substring(0, index) + "<h3>" + message.substring(index+3);
            } else {
                message = message.substring(0, index) + "</h3>" + message.substring(index+6);
            }
            log.debug("H3 Current message : {}", message);
            openH3 = !openH3;
            lookFor = openH3 ? "###" : "<br />";
        }


        return message;
    }
}
