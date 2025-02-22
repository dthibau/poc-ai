package org.formation.pocplb.model.plbsi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "filiere")
@Data
public class Filiere {

    @Id
    @Column(name = "id_filiere")
    @JsonIgnore
    private int id;

    @Column(name = "fil_libelle")
    private String libelle;

    @Column(name = "fil_url")
    private String url;


    @Lob
    @Column(name = "fil_description", columnDefinition = "mediumtext")
    @JsonIgnore
    private String description;

    @Column(name = "fil_titre")
    private String titre;


    @Column(name = "fil_specialite_formation")
    private Integer specialiteFormation;

    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL)
    @OrderBy("rang")
    @JsonIgnore
    List<Categorie> categories = new ArrayList<Categorie>();

    public String getDescriptionAsString() {
        return HTMLUtils.getData(description);
    }

    @Override
    public String toString() {
        return libelle;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Filiere other = (Filiere) obj;
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

}
