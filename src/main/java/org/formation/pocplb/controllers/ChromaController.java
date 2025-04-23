package org.formation.pocplb.controllers;

import org.formation.pocplb.service.chroma.ChromaService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("chroma")
@RequestMapping("/chroma")
public class ChromaController {

    private final ChromaService chromaService;

    public ChromaController(ChromaService chromaService) {
        this.chromaService = chromaService;
    }

    @GetMapping("/count")
    public int count() {
        return chromaService.countDocuments();
    }
}
