package org.formation.pocplb.repository;


import org.formation.pocplb.model.Categorie;
import org.formation.pocplb.model.Filiere;
import org.formation.pocplb.model.Formation;
import org.formation.pocplb.model.Partenaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FormationRepository extends JpaRepository<Formation, Integer> {

	@Query("select count(f.id) from Formation f where f.archivedDate is null")
	public Integer countUnArchived();

	@Query("select count(f.id) from Formation f where f.archivedDate is not null")
	public Integer countArchived();

	@Query("select ff.formation FROM FormationFiliere ff where ff.categorie.id = :categorieId and ff.formation.archivedDate is null order by ff.rang")
	public List<Formation> findByCategorieId(@Param("categorieId") int categorieId);
	
	@Query("select count(ff) FROM FormationFiliere ff where ff.categorie.id = :categorieId and ff.formation.archivedDate is null order by ff.rang")
	public Integer countByCategorieId(@Param("categorieId") int categorieId);

	@Query("select f from Formation f where f.archivedDate is null order by f.reference asc")
	public List<Formation> findAllUnarchived();

//	@Query("select f from Formation f where f.archivedDate is null")
	@Query("select distinct ff.formation from FormationFiliere ff where ff.formation.archivedDate is null")
	public Page<Formation> findAllUnarchived(Pageable pageable);

//	@Query("select f from Formation f where f.archivedDate is not null")
	@Query("select distinct ff.formation from FormationFiliere ff where ff.formation.archivedDate is not null")
	public Page<Formation> findAllArchived(Pageable pageable);
	
	

	@Query("select ff.formation FROM FormationFiliere ff WHERE (:filiere is null or ff.filiere = :filiere) and (:categorie is null"
			  + " or ff.categorie = :categorie) and ff.formation.archivedDate is null")
	public Page<Formation> findUnarchivedFormationsFiliere(@Param("filiere") Filiere filiere, @Param("categorie") Categorie categorie, Pageable pageable);
	
	@Query("select ff.formation FROM FormationFiliere ff WHERE (:filiere is null or ff.filiere = :filiere) and (:categorie is null"
			  + " or ff.categorie = :categorie) and ff.formation.archivedDate is not null")
	public Page<Formation> findArchivedFormationsFiliere(@Param("filiere") Filiere filiere, @Param("categorie") Categorie categorie, Pageable pageable);

	@Query("select fp.formation FROM FormationPartenaire fp WHERE fp.partenaire = :partenaire and fp.formation.archivedDate is null")
	public Page<Formation> findUnarchivedFormationsPartenaire(@Param("partenaire") Partenaire partenaire, Pageable pageable);
	
	@Query("select fp.formation FROM FormationPartenaire fp WHERE fp.partenaire = :partenaire and fp.formation.archivedDate is not null")
	public Page<Formation> findArchivedFormationsPartenaire(@Param("partenaire") Partenaire partenaire, Pageable pageable);
	
//	@Query("select f FROM Formation f WHERE f.formationsPartenaire is empty and f.archivedDate is null")
    @Query("select ff.formation FROM FormationFiliere ff WHERE ff.formation.formationsPartenaire is empty and ff.formation.archivedDate is null")
	public Page<Formation> findUnarchivedExcluPLB(Pageable pageable);
	
//	@Query("select f FROM Formation f WHERE f.formationsPartenaire is empty and f.archivedDate is not null")
	@Query("select ff.formation FROM FormationFiliere ff WHERE ff.formation.formationsPartenaire is empty and ff.formation.archivedDate is not null")
	public Page<Formation> findArchivedExcluPLB(Pageable pageable);
	
	@Query("select ff.formation FROM FormationFiliere ff inner join ff.formation.formationsPartenaire fp WHERE (:filiere is null or ff.filiere = :filiere) and (:categorie is null"
			  + " or ff.categorie = :categorie) and fp.partenaire = :partenaire and ff.formation.archivedDate is null")
	public Page<Formation> findUnarchivedFormationsFilierePartenaire(@Param("filiere") Filiere filiere, @Param("categorie") Categorie categorie, @Param("partenaire") Partenaire partenaire, Pageable pageable);

	@Query("select ff.formation FROM FormationFiliere ff inner join ff.formation.formationsPartenaire fp WHERE (:filiere is null or ff.filiere = :filiere) and (:categorie is null"
			  + " or ff.categorie = :categorie) and fp.partenaire = :partenaire and ff.formation.archivedDate is not null")
	public Page<Formation> findArchivedFormationsFilierePartenaire(@Param("filiere") Filiere filiere, @Param("categorie") Categorie categorie, @Param("partenaire") Partenaire partenaire, Pageable pageable);

	@Query("select ff.formation FROM FormationFiliere ff WHERE (:filiere is null or ff.filiere = :filiere) and (:categorie is null"
			  + " or ff.categorie = :categorie) and ff.formation.formationsPartenaire is EMPTY and ff.formation.archivedDate is null")
	public Page<Formation> findUnarchivedFormationsFiliereExcluPLB(@Param("filiere") Filiere filiere, @Param("categorie") Categorie categorie, Pageable pageable);

	@Query("select ff.formation FROM FormationFiliere ff WHERE (:filiere is null or ff.filiere = :filiere) and (:categorie is null"
			  + " or ff.categorie = :categorie) and ff.formation.formationsPartenaire is EMPTY and ff.formation.archivedDate is not null")
	public Page<Formation> findArchivedFormationsFiliereExcluPLB(@Param("filiere") Filiere filiere, @Param("categorie") Categorie categorie, Pageable pageable);

	public Optional<Formation> findByReference(String reference);
}
