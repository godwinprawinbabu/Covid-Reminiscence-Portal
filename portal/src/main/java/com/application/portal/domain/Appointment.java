package com.application.portal.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.application.portal.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Appointment extends AuditModel implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
	private String date;
	private String slot;
	private String vaccine;
	private Integer count;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "centres_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Centres centres;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
	
	
	
	public Appointment() {
	}
	public Appointment(Long id, String date, String slot, String vaccine,Integer count, Centres centres, User user) {
		this.id = id;
		this.date = date;
		this.slot = slot;
		this.centres = centres;
		this.user = user;
		this.vaccine=vaccine;
		this.count=count;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	public String getVaccine() {
		return vaccine;
	}
	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Centres getCentres() {
		return centres;
	}
	public void setCentres(Centres centres) {
		this.centres = centres;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
