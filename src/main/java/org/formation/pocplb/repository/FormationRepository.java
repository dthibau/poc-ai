package org.formation.pocplb.repository;


import org.formation.pocplb.model.plbsi.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FormationRepository extends JpaRepository<Formation, Integer> {

	public Optional<Formation> findByReference(String reference);

	@Query("select ff.formation FROM FormationFiliere ff where ff.filiere.id = :filiereId and ff.formation.archivedDate is null order by ff.rang")
	public List<Formation> findByFiliereId(@Param("filiereId") int filiereId);

	@Query("select f from Formation f where f.archivedDate is null order by f.reference asc")
	public List<Formation> findAllUnarchived();

}
