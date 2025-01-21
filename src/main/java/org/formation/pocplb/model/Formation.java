package org.formation.pocplb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Indexed;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "formation")
@Indexed
@Data
public class Formation implements Serializable, Comparable<Formation> {

    private static final long serialVersionUID = -211404718465836235L;

    @Lob
    @Column(name = "moyens_pedagogiques", columnDefinition = "longtext")
    @JsonIgnore
    String moyensPedagogiques = "";
    @Lob
    @Column(name = "modalites_suivi", columnDefinition = "longtext")
    @JsonIgnore
    String modalitesSuivi = "";
    @Column(name = "for_nouveaute", columnDefinition = "enum('oui','non')")
    String nouveaute = "non";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "formationGenerator")
    @SequenceGenerator(name = "formationGenerator", sequenceName = "formation_id", initialValue = 700000, allocationSize = 1)
    @Column(name = "id_formation")
    @JsonIgnore
    private Integer idFormation;
    @Column(name = "for_reference", columnDefinition = "text")
    private String reference;
    @Column(name = "for_libelle", columnDefinition = "text")
    private String libelle;
    @Column(name = "for_duree", columnDefinition = "decimal")
    @Min(value = 1)
    private Integer duree;
    @Column(name = "for_prix", columnDefinition = "decimal")
    private Float prix;
    @Column(name = "for_origine", columnDefinition = "text")
    private String origine;

    @Column(name = "for_niveau")
    private String niveau = "Fondamental";

    @Column(name = "for_url", columnDefinition = "text")
    private String url;
    @Column(name = "argumentaire", columnDefinition = "mediumtext")
    private String blocPublic = "";

    @Column(name = "for_precision_libelle", columnDefinition = "text")
    private String sousTitre;


    @Lob
    @Column(columnDefinition = "mediumtext")
    @JsonIgnore
    private String descriptif;
    @Lob
    @Column(name = "for_contenu", columnDefinition = "mediumtext")
    @JsonIgnore
    private String contenu;
    @Lob
    @Column(name = "for_objectifs_operationnels", columnDefinition = "mediumtext")
    @JsonIgnore
    private String objectifs_operationnels;
    @Lob
    @Column(name = "for_objectifs", columnDefinition = "mediumtext")
    @JsonIgnore
    private String objectifs_pedagogiques;
    @Lob
    @Column(name = "for_prerequis", columnDefinition = "mediumtext")
    @JsonIgnore
    private String prerequis;
    @Lob
    @Column(name = "for_participants", columnDefinition = "mediumtext")
    @JsonIgnore
    private String participants;
    @Lob
    @Column(name = "for_travaux_pratiques", columnDefinition = "mediumtext")
    private String travauxPratiques;
    @Column(name = "for_replace")
    private String libelleCertification;
    @Column(name = "for_infos", columnDefinition = "mediumtext")
    @Lob
    private String descriptifCertification;
    @Column(name = "for_top10", columnDefinition = "tinyint")
    private Integer top10 = 0;
    private String certification;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "archivedDate")
    @JsonIgnore
    private Date archivedDate;
    @OneToMany(mappedBy = "formation", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<FormationFiliere> formationFilieres = new ArrayList<FormationFiliere>();
    @ManyToMany
    @JoinTable(name = "formation_formation", joinColumns = {
            @JoinColumn(name = "id_formation_principale", referencedColumnName = "id_formation")}, inverseJoinColumns = {
            @JoinColumn(name = "id_formation_suivante", referencedColumnName = "id_formation")})
    @JsonIgnore
    private List<Formation> formationAssociees;
    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<FormationPartenaire> formationsPartenaire = new ArrayList<FormationPartenaire>();
    @ManyToOne(optional = true)
    @JsonIgnore
    private FormationMutualisees formationMutualisees;



    public String getMoyensPedagogiquesAsString() {
        return HTMLUtils.getData(moyensPedagogiques);
    }
    public String getModaliteSuiviAsString() {
        return HTMLUtils.getData(modalitesSuivi);
    }
    public String getDescriptifAsString() {
        return HTMLUtils.getData(descriptif);
    }

    public String getContenuAsString() {
        return HTMLUtils.getData(contenu);
    }
    public String getObjectifsOperationnelsAsString() {
        return HTMLUtils.getData(objectifs_operationnels);
    }
    public String getObjectifsPedagogiquesAsString() {
        return HTMLUtils.getData(objectifs_pedagogiques);
    }
    public String getPrerequisAsString() {
        return HTMLUtils.getData(prerequis);
    }
    public String getParticipantsAsString() {
        return HTMLUtils.getData(participants);
    }


    @Transient
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Categorie getCategorie() {
        List<Categorie> cats = getFormationFilieres().stream().filter(f -> f.isPrincipale())
                .map(ff -> ff.getCategorie())
                .collect(Collectors.toList());
        if (cats.size() > 0) {
            return cats.get(0);
        } else {
            Categorie cat = new Categorie();
            cat.setLibelle("");
            return cat;
        }
    }

    @Transient
    public String getCategorieLibelle() {
        return getCategorie() != null ? getCategorie().getLibelle() : "";
    }



    @Transient
    public List<String> getAutresCategories() {

        return getCategoriesSecondaires().stream().map(c -> c.getLibelle()).collect(Collectors.toList());
    }

    @Transient
    @JsonIgnore
    public List<Categorie> getCategoriesSecondaires() {

        List<Categorie> ret = new ArrayList<>();
        getFormationFilieres().stream().filter(f -> !f.isPrincipale()).forEach(f -> ret.add(f.getCategorie()));

        return ret;
    }



    @Transient
    public List<Partenaire> getPartenaires() {

        return getFormationsPartenaire().stream().map(fp -> fp.getPartenaire()).collect(Collectors.toList());

    }

    @Transient
    public String getFormationsPartenaireAsString() {
        StringBuffer sbf = new StringBuffer();
        boolean bFirst = true;
        if (getFormationsPartenaire() != null) {
            for (FormationPartenaire fp : getFormationsPartenaire()) {
                if (bFirst) {
                    sbf.append(fp.getPartenaire().getNom() + "(" + fp.getPrix() + ")");
                    bFirst = false;
                } else {
                    sbf.append("/" + fp.getPartenaire().getNom() + "(" + fp.getPrix() + ")");
                }
            }
        }
        return sbf.toString();
    }

    @Transient
    @JsonIgnore
    public Filiere getFilierePrincipale() {
        return getCategorie() != null ? getCategorie().getFiliere() : null;

    }
    @Transient
    public String getFiliereLibelle() {
        return getFilierePrincipale() != null ? getFilierePrincipale().getLibelle() : "";

    }

    @Transient
    public List<String> getAutresFilieres() {

        List<String> ret = new ArrayList<>();
        getFormationFilieres().stream().filter(f -> !f.isPrincipale()).forEach(f -> ret.add(f.getLibelle()));

        return ret;
    }

    @Transient
    public String getUrlPlb() {
        if (getUrl() == null || getFilierePrincipale().getUrl() == null) {
            return null;
        }
        return "/formation/" + getFilierePrincipale().getUrl() + "/" + getUrl() + "," + getFilierePrincipale().getId()
                + "-" + getIdFormation() + ".php";
    }

    public void setUrlPlb(String url) {
        // Just for Json Parsing
    }


    @Transient
    public FormationFiliere getFormationFiliere(Categorie categorie) {
        for (FormationFiliere ff : formationFilieres) {
            if (ff.getCategorie().equals(categorie)) {
                return ff;
            }
        }
        return null;
    }

    @Transient
    public FormationFiliere getFormationFilierePrincipale() {
        return formationFilieres.stream().filter(ff -> ff.isPrincipale()).findFirst()
                .orElseThrow(() -> new IllegalStateException("Formation sans catégorie " + this));

    }


    @Transient
    public boolean containsFiliere(Filiere filiere) {
        for (FormationFiliere ff : formationFilieres) {
            if (ff.getFiliere().equals(filiere)) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public boolean containsCategorie(Categorie categorie) {
        for (FormationFiliere ff : formationFilieres) {
            if (ff.getCategorie() != null && ff.getCategorie().equals(categorie)) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public boolean containsPartenaire(Partenaire partenaire) {
        for (FormationPartenaire fp : formationsPartenaire) {
            if (fp.getPartenaire() != null && fp.getPartenaire().equals(partenaire)) {
                return true;
            }
        }
        return false;
    }



    @Override
    public String toString() {
        return "Formation [idFormation=" + idFormation + ", reference=" + reference + "]";
    }

    @Override
    public int compareTo(Formation o) {
        return getLibelle().compareTo(o.getLibelle());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Formation other = (Formation) obj;
        if (idFormation == null) {
            return other.idFormation == null;
        } else return idFormation.equals(other.idFormation);
    }

    @Override
    public int hashCode() {
        if (idFormation == 0) {
            return super.hashCode();
        }
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idFormation == null) ? 0 : idFormation.hashCode());
        return result;
    }

}
