package com.example.ohjelmistoprojekti1.domain;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long eventid;

	@NotNull
	private String name, description;

	private double price;

	@NotNull
	private String venue;

	@NotNull
	private Date date;

	private String time;

	@NotNull
	private int ticketInventory;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Ticket> tickets;

	public Event() {
		super();
	}

	public void setTime(String time) {
		try {
			String[] psc = time.split(":");
			int tunnit = Integer.parseInt(psc[0]);
			int minuutit = Integer.parseInt(psc[1]);
			if (tunnit > 24) {
				tunnit = 24;
			}
			if (tunnit < 0) {
				tunnit = 0;
			}
			if (minuutit > 60) {
				minuutit = 60;
			}
			if (minuutit < 0) {
				minuutit = 0;
			}
			time = tunnit + ":" + minuutit;
		} catch (Exception e) {
			System.out.println("Virhe ajan asettamisessa: " + e.getMessage());
		}
		this.time = time;
	}

	public String getTime() {
		return this.time;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public long getEventid() {
		return eventid;
	}

	public void setEventid(long eventid) {
		this.eventid = eventid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/** @param date parameter in form: "yyyy-mm-dd" */
	public void setDate(String date) {
		this.date = java.sql.Date.valueOf(date);
	}

	public int getTicketInventory() {
		return this.ticketInventory;
	}

	public void setTicketInventory(int ticketInventory) {
		this.ticketInventory = ticketInventory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
