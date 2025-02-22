package org.formation.pocplb.model.plbsi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "session_lieu")
@Data
public class SessionLieu {

    public final static String SYNONYMS_SEPARATOR="|";
    @Id
    private Long id;
    private String nom;

    private String emplacement;

    @Column(name = "emplacement_synonyms")
    private String emplacementSynonyms;




    private Integer rang;
    /**
     * Partenaire==null => PLB
     */

    
    @OneToMany(mappedBy = "sessionLieu", cascade = CascadeType.ALL)
    List<Session> sessions = new ArrayList<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SessionLieu other = (SessionLieu) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return emplacement;
    }

}
