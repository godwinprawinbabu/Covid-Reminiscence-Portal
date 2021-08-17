package com.application.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.portal.domain.Appointment;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {

}
