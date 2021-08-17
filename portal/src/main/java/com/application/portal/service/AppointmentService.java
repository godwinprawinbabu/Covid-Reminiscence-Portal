package com.application.portal.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.portal.domain.Appointment;
import com.application.portal.domain.Centres;
import com.application.portal.domain.User;
import com.application.portal.exception.domain.DosesNotAvailableException;
import com.application.portal.repository.AppointmentRepository;
import com.application.portal.repository.CentresRepository;
import com.application.portal.repository.UserRepository;

@Service
@Transactional
public class AppointmentService {
	
	private AppointmentRepository appointmentRepository;
	private CentresRepository centresRepository;
	private UserRepository userRepository;
	private EmailService emailService;
	private DosesService dosesService;

	
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, CentresRepository centresRepository, UserRepository userRepository, EmailService emailService,DosesService dosesService) {
        this.appointmentRepository = appointmentRepository;
        this.centresRepository=centresRepository;
        this.userRepository=userRepository;
        this.emailService=emailService;
        this.dosesService=dosesService;
    }

	public Appointment addNewAppointment(Date date, String slot, Long centresId, String vaccine, Integer count, Long userId)
			throws IOException,MessagingException, DosesNotAvailableException {
		Appointment appointment = new Appointment();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(date);
        appointment.setDate(dateString);
        appointment.setSlot(slot);
        appointment.setVaccine(vaccine);
        appointment.setCount(count);
		Centres centres =centresRepository.findById(centresId).get();
		appointment.setCentres(centres);
		User user= userRepository.findById(userId).get();
		appointment.setUser(user);
		appointmentRepository.save(appointment);
		dosesService.updateDoses(centresId, vaccine, count, date);
		emailService.sendNewAppointmentEmail(appointment.getId(), dateString, slot, centres.getName(), user.getFirstName(), user.getEmail(),vaccine,count);
		return appointment;
	}
	
    public void deleteAppointment(Long appointmentId) throws IOException {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        if(null!=appointment)
        	appointmentRepository.deleteById(appointment.getId());
    }

}
