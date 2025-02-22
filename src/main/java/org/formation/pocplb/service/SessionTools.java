package org.formation.pocplb.service;

import lombok.RequiredArgsConstructor;
import org.formation.pocplb.model.plbsi.Session;
import org.formation.pocplb.repository.SessionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SessionTools {

    private final SessionRepository sessionRepository;

    @Tool(description = "Récupère les prochaines sessions d'une formation")
    List<String> getNextSessions(String refFormation) {
        List<Session> sessions = sessionRepository.findNextByFormation(refFormation);
        return sessions.stream().map(s -> s.toString()).toList();
    }

}
