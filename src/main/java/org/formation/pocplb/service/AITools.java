package org.formation.pocplb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.formation.pocplb.model.plbsi.Session;
import org.formation.pocplb.repository.SessionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class AITools {

    private final SessionRepository sessionRepository;

    @Tool(description = "Récupère les prochaines sessions d'une formation")
    List<String> getNextSessions(String refFormation) {
        List<Session> sessions = sessionRepository.findNextByFormation(refFormation);
        return sessions.stream().map(s -> s.toString()).toList();
    }
    @Tool(description = "Met à jour le catalogue plbsi")
    void createFormation(String json) {
        log.info("Creating formation {}", json);
    }

}
