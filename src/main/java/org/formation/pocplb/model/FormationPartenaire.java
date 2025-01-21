package org.formation.pocplb.model;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "formation_partenaire")
@Data
public class FormationPartenaire {

    @Id
    private int id;

    @ManyToOne
    private Formation formation;
    @ManyToOne
    private Partenaire partenaire;

    private Float prix;
    @Column(name = "code")
    private String reference;
    @Column(columnDefinition = "text")
    private String url;


    @Column(name = "tarif_journalier")
    private Float tarifJournalier;

    private String labs;

    private String support;
    
    private String voucher;

    public FormationPartenaire() {

    }

    @Transient
    public String getNom() {
        return partenaire.getNom();
    }

    public FormationPartenaire(Formation formation) {
        this.formation = formation;
    }

    public FormationPartenaire(Formation formation, Partenaire partenaire) {
        this.formation = formation;
        this.partenaire = partenaire;
    }

    public FormationPartenaire getCopy() {
        FormationPartenaire newFP = new FormationPartenaire();
        newFP.setId(this.id);
        newFP.setFormation(this.formation);
        newFP.setPartenaire(this.partenaire);
        newFP.setReference(this.reference);
        newFP.setPrix(this.prix);
        newFP.setUrl(this.url);

        return newFP;
    }

//	public void addSessions(List<Session> sessions) {
//		this.sessions.addAll(sessions);
//	}
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
        FormationPartenaire other = (FormationPartenaire) obj;
        if (id == 0) { // En cours d'édition
            if (partenaire != null && reference != null) {
                return partenaire.equals(other.getPartenaire()) && reference.equals(other.getReference());
            } else
                return false;
        } else if (id != other.id) // Persité en base
            return false;
        return true;
    }

    @Override
    public String toString() {
//		return "FormationPartenaire [id=" + id + ", formation=" + formation + ", partenaire=" + partenaire
//				+ ", sessions=" + sessions + ", prix=" + prix + ", code=" + reference + ", url=" + url + "]";
        return "FormationPartenaire [id=" + id + ", formation=" + formation + ", partenaire=" + partenaire
                + ", prix=" + prix + ", code=" + reference + ", url=" + url + "]";
    }

    public String logTarifString() {
        return "prix=" + prix;
    }

}
