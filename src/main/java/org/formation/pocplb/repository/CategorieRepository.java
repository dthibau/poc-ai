package org.formation.pocplb.repository;


import org.formation.pocplb.model.plbsi.Categorie;
import org.formation.pocplb.model.plbsi.CategorieConnexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

	@Query("select c from Categorie c where c.filiere.id = :filiereId order by c.rang")
	public List<Categorie> findByFiliereId(@Param("filiereId") int filiereId);

	@Query("select c from Categorie c left join fetch c.categoriesConnexes where c.id = :id")
	public Optional<Categorie> fullLoad(@Param("id") int id);
	
	@Query("select cc from CategorieConnexe cc where cc.baseCategorie.id = :id order by cc.order")
	public List<CategorieConnexe> getCategorieConnexes(@Param("id") int id);
	
	@Query("select cc.baseCategorie from CategorieConnexe cc where cc.linkedCategorie.id = :id order by cc.baseCategorie.rang")
	public List<Categorie> getCategorieReferentes(@Param("id") int id);
		
}
