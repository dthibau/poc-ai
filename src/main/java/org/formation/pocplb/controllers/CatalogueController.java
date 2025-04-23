package org.formation.pocplb.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.formation.pocplb.model.Question;
import org.formation.pocplb.service.ChatClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/catalogue")
public class CatalogueController {
    private final ChatClientService chatClientService;

    private boolean openB;
    private boolean openH3;

    @GetMapping
    public Flux<String> askQuestion(@RequestParam String message, @RequestParam String uuid) {

        return chatClientService.getCatalogue(message, uuid).map(this::formatMessage);
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
