package com.republica.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.republica.start.entity.Simetric_private_key_entity;

public interface ChaveRepository extends JpaRepository<Simetric_private_key_entity, Long> {
	
	@Query("SELECT k FROM Simetric_private_key_entity k WHERE k.user_id = :userId")
	Simetric_private_key_entity findByUserId(@Param("userId") int userId);

	
}

