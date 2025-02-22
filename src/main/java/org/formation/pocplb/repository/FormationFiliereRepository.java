package org.formation.pocplb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.formation.pocplb.model.plbsi.Categorie;
import org.formation.pocplb.model.plbsi.Formation;
import org.formation.pocplb.model.plbsi.FormationFiliere;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FormationFiliereRepository extends JpaRepository<FormationFiliere, Long> {

	public Optional<FormationFiliere> findByFormationAndCategorie(Formation formation, Categorie categorie);
	
	@Query("select max(ff.rang) from FormationFiliere ff where ff.categorie=:categorie")
	public Optional<Integer> findMaxRangForCategorie(@Param("categorie") Categorie categorie);
	
	public List<FormationFiliere> findByCategorie(Categorie categorie);
}
