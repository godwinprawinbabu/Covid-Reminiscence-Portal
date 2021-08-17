package com.application.portal.resource;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.portal.domain.Doses;
import com.application.portal.domain.HttpResponse;
import com.application.portal.exception.domain.DosesNotAvailableException;
import com.application.portal.service.DosesService;

@RestController
@RequestMapping("/doses")
public class DosesResource {
	
	private DosesService dosesService;
    public static final String DOSES_DELETED_SUCCESSFULLY = "Doses deleted successfully";

	
	@Autowired
	public DosesResource(DosesService dosesService) {
		this.dosesService=dosesService;
	}
    @PostMapping("/{centresId}/add")
    public ResponseEntity<Doses> addDoses(@PathVariable (value = "centresId") Long centresId,
                                           @RequestParam("vaccine") String vaccine,
                                           @RequestParam("count") Integer count,
                                           @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) throws  IOException {
        Doses doses = dosesService.addDoses(centresId, vaccine, count, date);
        return new ResponseEntity<>(doses, OK);
    }
    
    @GetMapping("/find/{centresId}/{date}")
    public ResponseEntity<List<Doses>> getDoses(@PathVariable("centresId") Long centresId, @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<Doses> doses = dosesService.findDosesByCentres_idAndDate(centresId, date);
        return new ResponseEntity<>(doses, OK);
    }
    
    @PostMapping("/update")
    public ResponseEntity<Doses> updateDoses(@RequestParam("centresId") Long centresId,
                                       @RequestParam("vaccine") String vaccine,
                                       @RequestParam("count") Integer count,
                                       @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) throws IOException,DosesNotAvailableException {
        Doses updatedDoses = dosesService.updateDoses(centresId, vaccine, count, date);
        return new ResponseEntity<>(updatedDoses, OK);
    }
    
    @DeleteMapping("/delete/{dosesId}")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("dosesId") Long dosesId) throws IOException {
    	dosesService.deleteDoses(dosesId);
        return response(OK, DOSES_DELETED_SUCCESSFULLY);
    }
    
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
