package com.example.ohjelmistoprojekti1.domain.classes;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
	private String name, description, venue;

	private double price;

	// Tapahtuman alkamisaika
	private ZonedDateTime startTime;
	// Tapahtuman päättymisaika
	private ZonedDateTime endTime;

	private int ticketInventory;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
	private List<Ticket> tickets;

	public Event() {
		super();

	}

	// this is for a test in RandomTests
	public Event(@NotNull String name, @NotNull String description, double price, @NotNull String venue,
			int ticketInventory) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.venue = venue;
		this.ticketInventory = ticketInventory;
	}

	/**
	 * Set starting time for event, for
	 * Example:\n"2020-04-28T11:47:54.294+03:00[Europe/Helsinki]@param
	 * yyyy-mm-ddThh:mm:ss.msm+hh:mm[TimeZone]
	 */
	public void setStartTime(String startTime) {
		try {
			this.startTime = ZonedDateTime.parse(startTime);
		} catch (Exception e) {
			System.out.println("Alkuajan asettaminen epäonnnistui, tarkista syöte: " + e.getMessage());
		}

	}

	public String getStartTime() {

		if (this.startTime.toString() == null) {
			return "-";
		} else {
			return this.startTime.toString();
		}
	}

	/**
	 * Set ending time for event, for
	 * Example:\n"2020-04-28T11:47:54.294+03:00[Europe/Helsinki]@param
	 * yyyy-mm-ddThh:mm:ss.msm+hh:mm[TimeZone]
	 */
	public void setEndTime(String endTime) {
		try {
			this.endTime = ZonedDateTime.parse(endTime);
		} catch (Exception e) {
			System.out.println("Loppuajan asettaminen epäonnnistui, tarkista syöte: " + e.getMessage());
		}
	}

	public String getEndTime() {

		if (this.endTime.toString() == null) {
			return "-";
		} else {
			return this.endTime.toString();
		}

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

	public void setTicketInventory(int ticketInventory) {
		this.ticketInventory = ticketInventory;
	}

	public int getTicketInventory() {
		return ticketInventory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
