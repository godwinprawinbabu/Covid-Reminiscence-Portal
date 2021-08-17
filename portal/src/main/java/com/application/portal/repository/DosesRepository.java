package com.application.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.portal.domain.Doses;

public interface DosesRepository extends JpaRepository<Doses, Long> {

    List<Doses> findDosesByCentres_idAndDate(Long centres_id,String date);

	
}
