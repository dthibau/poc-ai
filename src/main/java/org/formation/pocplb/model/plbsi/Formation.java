package org.formation.pocplb.model.plbsi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import org.formation.pocplb.model.views.FormationViews;
import org.springframework.stereotype.Indexed;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "formation")
@Indexed
@Data
public class Formation implements Serializable, Comparable<Formation> {

    private static final long serialVersionUID = -211404718465836235L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "formationGenerator")
    @SequenceGenerator(name = "formationGenerator", sequenceName = "formation_id", initialValue = 700000, allocationSize = 1)
    @Column(name = "id_formation")
    @JsonIgnore
    private Integer idFormation;
    @Column(name = "for_reference", columnDefinition = "text")
    @JsonView(FormationViews.SimpleView.class)
    private String reference;
    @JsonView(FormationViews.SimpleView.class)
    @Column(name = "for_libelle", columnDefinition = "text")
    private String libelle;
    @Column(name = "for_duree", columnDefinition = "decimal")
    @Min(value = 1)
    @JsonView(FormationViews.SimpleView.class)
    private Integer duree;
    @Column(name = "for_prix", columnDefinition = "decimal")
    private Float prix;

    @Column(name = "for_niveau")
    private String niveau = "Fondamental";

    @Column(name = "for_url", columnDefinition = "text")
    private String url;
    @Column(name = "argumentaire", columnDefinition = "mediumtext")
    private String blocPublic = "";

    @Column(name = "for_precision_libelle", columnDefinition = "text")
    @JsonView(FormationViews.SimpleView.class)
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
    @JsonView(FormationViews.SimpleView.class)
    private String objectifs_operationnels;
    @Lob
    @Column(name = "for_objectifs", columnDefinition = "mediumtext")
    @JsonView(FormationViews.SimpleView.class)
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
    @JsonIgnore
    private String travauxPratiques;
    @Column(name = "for_replace")
    private String libelleCertification;
    @Column(name = "for_infos", columnDefinition = "mediumtext")
    @Lob
    @JsonIgnore
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

    @ManyToOne(optional = true)
    @JsonIgnore
    private FormationMutualisees formationMutualisees;


    public String getTravauxPratiquesAsString() {
        return travauxPratiques != null ? HTMLUtils.getData(travauxPratiques) : "";
    }
    public String getDescriptifCertificationAsString() {
        return descriptifCertification != null ? HTMLUtils.getData(descriptifCertification) : "";
    }

   public String getDescriptifAsString() {
        return descriptif != null ? HTMLUtils.getData(descriptif) : "";
    }

    public String getContenuAsString() {
        return contenu != null ? (contenu.length() > 3000 ? HTMLUtils.getData(contenu.substring(0,3000)) : HTMLUtils.getData(contenu)) : "";
    }
    public String getObjectifsOperationnelsAsString() {
        return objectifs_operationnels != null ? HTMLUtils.getData(objectifs_operationnels) : "";
    }
    public String getObjectifsPedagogiquesAsString() {
        return objectifs_pedagogiques != null ? HTMLUtils.getData(objectifs_pedagogiques) : "";
    }
    public String getPrerequisAsString() {
        return prerequis != null ? HTMLUtils.getData(prerequis) : "";
    }
    public String getParticipantsAsString() {
        return participants != null ? HTMLUtils.getData(participants) : "";
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
