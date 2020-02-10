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

@Entity
public class Event {
	// TODO: improve alternative methods for time and date management

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long eventId;

	@NotNull
	private String name, description;

	@NotNull
	private String venue;

	@NotNull
	private Date date;

	@NotNull
	private Time time;

	@NotNull
	private int ticketInventory;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Ticket> tickets;

	public Event() {
		super();
	}

	public Event(@NotNull String name, @NotNull String description, @NotNull String venue, @NotNull Date date,
			@NotNull Time time, @NotNull int ticketInventory, List<Ticket> tickets) {
		super();
		this.name = name;
		this.description = description;
		this.venue = venue;
		this.date = date;
		this.time = time;
		this.ticketInventory = ticketInventory;
		this.tickets = tickets;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	/** @param time parameter in form: "hh:mm:ss" */
	public void setTime(String time) {
		this.time = java.sql.Time.valueOf(time);
	}

	public int getTicketInventory() {
		return this.ticketInventory;
	}

	public void setTicketInventory(int ticketInventory) {
		this.ticketInventory = ticketInventory;
	}

}
