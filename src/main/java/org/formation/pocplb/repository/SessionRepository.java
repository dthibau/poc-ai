package org.formation.pocplb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.formation.pocplb.model.plbsi.Formation;
import org.formation.pocplb.model.plbsi.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class SessionRepository {

    @Autowired
    EntityManager entityManager;


    @SuppressWarnings("unchecked")
    public List<Session> findNextByFormation(String refFormation) {
        Query q = entityManager
                .createQuery(
                        "from Session s where s.formation.reference = :refFormation and DATE(s.debut) > :debut order by s.debut asc")
                .setParameter("refFormation", refFormation).setParameter("debut", new Date());

        return q.getResultList();

    }


}
