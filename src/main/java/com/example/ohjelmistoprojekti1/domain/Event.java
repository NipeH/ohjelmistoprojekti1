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

	@NotNull
	private Time time;

	@NotNull
	private int ticketInventory;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Ticket> tickets;

	public Event() {
		super();
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

	public Time getTime() {
		return time;
	}
	
	/** returns String presentation of time as hh:mm */
	public String getTimeStr() {
		String timestr = time.toString();
		String[] pcs = timestr.split(":");
		timestr = pcs[0] + ":" + pcs[1];
		return timestr;
	}

//	public void setTime(Object timeObj) {
//		if (timeObj instanceof Time) {
//			this.time = (Time) timeObj;
//		} else if (timeObj instanceof String) {
//			String s = (String) timeObj;
//			s += ":00";
//			this.time = Time.valueOf(s);
//		}
//	}

	/** @param time parameter in form: "hh:mm:ss" */
	public void setTime(String time) {
		this.time = java.sql.Time.valueOf(time);
	}
	
	/** @param time parameter in form: "hh:mm" */
	public void setTimeStr(String time) {
		time += ":00";
		this.time = java.sql.Time.valueOf(time);
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
