package org.formation.pocplb.model.plbsi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorie")
@Data
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorieGenerator")
    @SequenceGenerator(name = "categorieGenerator", sequenceName = "categorie_id", initialValue = 1500000, allocationSize = 1)
    @Column(name = "id_categorie")
    @JsonIgnore
    private int id;

    @Column(name = "cat_libelle")
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "id_filiere_principale")
    Filiere filiere;

    @Column(name = "cat_rang")
    @JsonIgnore
    private int rang;

    @Column(name = "cat_url")
    @JsonIgnore
    private String url;
    // Web
    @Lob
    @Column(name = "cat_description", columnDefinition = "text")
    @JsonIgnore
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baseCategorie")
    List<CategorieConnexe> categoriesConnexes = new ArrayList<CategorieConnexe>();

    public String getDescriptionAsString() {
        return HTMLUtils.getData(description);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Categorie other = (Categorie) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Categorie [id=" + id + ", libelle=" + libelle + "]";
    }

}
