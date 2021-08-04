package com.application.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.portal.domain.Centres;

public interface CentresRepository extends JpaRepository<Centres, Long> {

    List<Centres> findCentresByStateAndDistrict(String state,String district);

	
}
