package com.application.portal.resource;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.portal.domain.Appointment;
import com.application.portal.domain.HttpResponse;
import com.application.portal.exception.domain.DosesNotAvailableException;
import com.application.portal.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentResource {

	private AppointmentService appointmentService;
    public static final String APPOINTMENT_DELETED_SUCCESSFULLY = "Appointment deleted successfully";
    
	@Autowired
	public AppointmentResource(AppointmentService appointmentService) {
		this.appointmentService=appointmentService;
	}
    
    @PostMapping("/add")
    public ResponseEntity<Appointment> addNewAppointment(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                           @RequestParam("slot") String email,
                                           @RequestParam("centreId") Long centreId,
                                           @RequestParam("vaccine") String vaccine,
                                           @RequestParam("count") Integer count,
                                           @RequestParam("userId") Long userId) throws  IOException,MessagingException,DosesNotAvailableException {
        Appointment appointment = appointmentService.addNewAppointment(date, email, centreId, vaccine, count, userId);
        return new ResponseEntity<>(appointment, OK);
    }
    
    
    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<HttpResponse> deleteAppointment(@PathVariable("appointmentId") Long appointmentId) throws IOException {
    	appointmentService.deleteAppointment(appointmentId);
        return response(OK, APPOINTMENT_DELETED_SUCCESSFULLY);
    }
    
    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
