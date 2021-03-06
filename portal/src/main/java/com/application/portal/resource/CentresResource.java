package com.application.portal.resource;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.portal.domain.Centres;
import com.application.portal.domain.HttpResponse;
import com.application.portal.service.CentresService;

@RestController
@RequestMapping("/centres")
public class CentresResource {
	
	private CentresService centresService;
    public static final String CENTRES_DELETED_SUCCESSFULLY = "Centres deleted successfully";

	
	@Autowired
	public CentresResource(CentresService centresService) {
		this.centresService=centresService;
	}
	
    @GetMapping("/find/{state}/{district}")
    public ResponseEntity<List<Centres>> getCentres(@PathVariable("state") String state, @PathVariable("district") String district) {
        List<Centres> centres = centresService.findCentresByStateAndDistrict(state, district);
        return new ResponseEntity<>(centres, OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Centres>> getAllCentres() {
        List<Centres> centres = centresService.getCentres();
        return new ResponseEntity<>(centres, OK);
    }
	
    @PostMapping("/add")
    public ResponseEntity<Centres> addNewCentre(@RequestParam("name") String name,
                                           @RequestParam("email") String email,
                                           @RequestParam("phone") String phone,
                                           @RequestParam("country") String country,
                                           @RequestParam("state") String state,
                                           @RequestParam("district") String district) throws  IOException {
        Centres centre = centresService.addNewCentre(name, email, phone, country, state, district);
        return new ResponseEntity<>(centre, OK);
    }
    
    @DeleteMapping("/delete/{centresId}")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("centresId") Long centresId) throws IOException {
    	centresService.deleteCentres(centresId);
        return response(OK, CENTRES_DELETED_SUCCESSFULLY);
    }
    
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
