package com.application.portal.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.portal.domain.Centres;
import com.application.portal.repository.CentresRepository;

@Service
@Transactional
public class CentresService {
	private CentresRepository centresRepository;

	
    @Autowired
    public CentresService(CentresRepository centresRepository) {
        this.centresRepository = centresRepository;
    }
    
    public List<Centres> getCentres() {
        return centresRepository.findAll();
    }

    public List<Centres> findCentresByStateAndDistrict(String state,String district) {
        return centresRepository.findCentresByStateAndDistrict(state, district);
    }

	public Centres addNewCentre(String name, String email, String phone, String country, String state, String district)
			throws IOException {
		Centres centres = new Centres();
		centres.setName(name);
		centres.setEmail(email);
		centres.setPhone(phone);
		centres.setCountry(country);
		centres.setState(state);
		centres.setDistrict(district);
		centresRepository.save(centres);
		return centres;
	}
	
    public void deleteCentres(Long centresId) throws IOException {
        Centres centres = centresRepository.findById(centresId).get();
        if(null!=centres)
        centresRepository.deleteById(centres.getId());
    }


}
