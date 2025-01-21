package org.formation.pocplb.repository;


import org.formation.pocplb.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FiliereRepository extends JpaRepository<Filiere, Integer> {

	@Query("select f from Filiere f left join fetch f.categories where f.id = :id")
	public Optional<Filiere> fullLoad(@Param("id") int id);
}
