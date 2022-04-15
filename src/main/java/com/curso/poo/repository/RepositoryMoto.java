package com.curso.poo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.curso.poo.model.Moto;

public interface RepositoryMoto extends JpaRepository<Moto, Integer>{

	@Modifying
	@Query("UPDATE Moto m SET m.andando=true WHERE m.id=:id")
	void andarMoto(@Param("id") Integer id);
	
	@Query("SELECT m FROM Moto m WHERE m.cor LIKE LOWER(CONCAT('%', :cor, '%'))")
	Page<Moto> buscarMotoPage(@Param("cor") String cor, Pageable pageable);
}
