package org.formation.pocplb.model.plbsi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "formation_session")
@Data
public class Session implements Comparable<Session> {

    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Id
    @Column(name = "id_session")
    private int id;
    @Column(name = "forsess_date_debut", columnDefinition = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate debut;
    @Column(name = "forsess_date_fin", columnDefinition = "date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fin;


    @Column(name = "gescof_nbparticiants")
    private Integer nbParticipants;

    @Column(name = "gescof_etat")
    private Integer etat;
    @Column(name = "gescof_stageintra")
    private Boolean stageIntra;


    @Column(name = "status_web")
    @Enumerated(EnumType.STRING)
    private SessionStatusWeb statusWeb;

    @ManyToOne
    @JoinColumn(name = "id_formation")
    @JsonIgnore
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "session_lieu_id")
    private SessionLieu sessionLieu;

    public Session() {

    }

    public Session(Formation formation) {
        this.formation = formation;
    }

    @Transient
    public int getYear() {
        return debut != null ? debut.getYear() : -1;
    }

    @Transient
    public int getMonth() {
        return debut != null ? debut.getMonthValue() - 1 : -1;
    }

    @Transient
    public int getDay() {
        return debut != null ? debut.getDayOfMonth() : -1;
    }

    @Transient
    public int getDayFin() {
        if (fin != null) {
            return fin.getDayOfMonth();
        }
        return -1;
    }

    @Transient
    public String getAsString() {
        return this.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Session other = (Session) obj;
        if (id == 0 && other.id == 00) {
            return getFormation().equals(other.getFormation()) && getDebut().equals(other.getDebut());
        }
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        String sDebut = debut != null ? dateFormatter.format(debut) : "?";
        String sFin = fin != null ? dateFormatter.format(fin) : "?";
        return getSessionLieu() + " Du " + sDebut + " au " + sFin;
    }

    @Override
    public int compareTo(Session o) {
        return getDebut().compareTo(o.getDebut());
    }


    public String resolveStatut() {
        if (statusWeb != null) {
            return statusWeb.name();
        }
        if (this.nbParticipants >= 12) {
            return SessionStatusWeb.COMPLETE.name();
        } else if (this.nbParticipants >= 2 && this.nbParticipants < 12) {
            return SessionStatusWeb.CONFIRME.name();
        } else if (this.nbParticipants > 0) {
            return SessionStatusWeb.BONNE_VOIE_DE_MAINTIEN.name();
        }
        return "";
    }
}
