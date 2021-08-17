package com.application.portal.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.portal.domain.Centres;
import com.application.portal.domain.Doses;
import com.application.portal.exception.domain.DosesNotAvailableException;
import com.application.portal.repository.CentresRepository;
import com.application.portal.repository.DosesRepository;

@Service
@Transactional
public class DosesService {
	
	private DosesRepository dosesRepository;
	private CentresRepository centresRepository;
    public static final String DOSES_NOT_AVAILABLE = "Doses Not Available on the following date, Please try boking fewer slots ";


	
    @Autowired
    public DosesService(DosesRepository dosesRepository,CentresRepository centresRepository) {
        this.dosesRepository = dosesRepository;
        this.centresRepository = centresRepository;

    }
	public Doses addDoses(Long centresId,String vaccine,Integer count,Date date)
			throws IOException {
		Doses doses = new Doses();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(date);
		Centres centres =centresRepository.findById(centresId).get();
		doses.setCentres(centres);
		doses.setVaccine(vaccine);
		doses.setDate(dateString);
		doses.setCount(count);
		dosesRepository.save(doses);
		return doses;
	}
	
    public List<Doses> findDosesByCentres_idAndDate(Long centresId,Date date) {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(date);

        return dosesRepository.findDosesByCentres_idAndDate(centresId, dateString);
    }
    
    public Doses updateDoses(Long centresId,String vaccine,Integer count,Date date) throws IOException,DosesNotAvailableException {
    	Doses doses = getDosesCountForDate(centresId, vaccine, date);
if(doses!=null) {
	int currentCount=doses.getCount();
	if(count>currentCount) {
		throw new DosesNotAvailableException("Doses");
	}
	currentCount=currentCount-count;
	doses.setCount(currentCount);
	dosesRepository.save(doses);
} 
        return doses;
    }
    
    private Doses getDosesCountForDate(Long centresId,String vaccine,Date date) {
    	List<Doses> dosesList= new ArrayList<>();
    	
    	 dosesList = findDosesByCentres_idAndDate(centresId,date);
    	 Doses doses= dosesList.stream().filter(vaccineDoses->vaccineDoses.getVaccine().equals(vaccine)).findAny().orElse(null);
    	 
    	 return doses;
        
    }
    
    public void deleteDoses(Long dosesId) throws IOException {
        Doses doses = dosesRepository.findById(dosesId).get();
        if(null!=doses)
        dosesRepository.deleteById(doses.getId());
    }
}
