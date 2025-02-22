package org.formation.pocplb.model.plbsi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="categorie_cat")
@Data
public class CategorieConnexe {

	@Id
	@Column(name="id_categorie_cat")
	@JsonIgnore
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_categorie")
	@JsonIgnore
	private Categorie baseCategorie;
	
	@ManyToOne
	@JoinColumn(name="id_categorie_assoc")
	@JsonIgnore
	private Categorie linkedCategorie;
	
	@Column(name="`order`")
	@JsonIgnore
	private int order;


	@Transient
	public String getLinkedLibelle() {
		return linkedCategorie.getLibelle();
	}


}
