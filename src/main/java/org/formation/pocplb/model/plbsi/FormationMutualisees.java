package org.formation.pocplb.model.plbsi;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="formation_mutualisees")
public class FormationMutualisees {

	@Id
	private int id;
	
	@OneToMany(mappedBy="formationMutualisees")
	private List<Formation> formations = new ArrayList<Formation>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}
	public void addFormation(Formation formation) {
		formations.add(formation);
		formation.setFormationMutualisees(this);
	}
	public void removeFormation(Formation formation) {
		formations.remove(formation);
		formation.setFormationMutualisees(null);
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
		FormationMutualisees other = (FormationMutualisees) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
