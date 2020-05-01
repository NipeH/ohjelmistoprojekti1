package com.example.ohjelmistoprojekti1.domain.classes;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ticketid;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "eventid")
	private Event event;

	private double price;

	@ManyToOne
	@JoinColumn(name = "tickettypeid")
	private TicketType type;

	@ManyToOne
	@JsonIgnore // pakko olla tässä päällä
	@JoinColumn(name = "orderid")
	private Order orders;

	private boolean isValid;

	private ZonedDateTime used;

	@Column(name = "ticketcode", unique = true)
	private UUID ticketcode;

	public Ticket() {
		super();
		used = null;
	}

	public Ticket(@NotNull Event event, double price, TicketType type, Order orders) {
		super();
		this.event = event;
		this.price = price;
		this.type = type;
		this.orders = orders;
	}

	public Ticket(@NotNull Event event, double price, @NotNull TicketType type, Order orders, boolean isValid) {
		super();
		this.event = event;
		this.price = price;
		this.type = type;
		this.orders = orders;
		this.isValid = isValid;
	}

	// Used in tickettest class
	public Ticket(@NotNull Event event, double price, TicketType type, Order orders, boolean isValid, UUID ticketcode) {
		super();
		this.event = event;
		this.price = price;
		this.type = type;
		this.orders = orders;
		this.isValid = isValid;
		this.ticketcode = ticketcode;
	}

	public Ticket(@NotNull Event event, double price, TicketType type, Order orders, boolean isValid,
			ZonedDateTime used, UUID ticketcode) {
		super();
		this.event = event;
		this.price = price;
		this.type = type;
		this.orders = orders;
		this.isValid = isValid;
		this.used = used;
		this.ticketcode = ticketcode;
	}

	public UUID getTicketcode() {
		return ticketcode;
	}

	public void setTicketcode(UUID ticketcode) {
		this.ticketcode = ticketcode;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

	public long getTicketid() {
		return ticketid;
	}

	public void setTicketid(long ticketid) {
		this.ticketid = ticketid;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price < 0) {
			price = 0;
		}
		this.price = price;
	}

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public boolean isValid() {
		return this.isValid;
	}

	/*
	public String getUsed() {
		try {
			return this.used.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
			//tän tarvii palauttaa nykymuodossaan null, niin palautin ton aiemman metodin joka näyttää ihan toimivan,
			 * muutan luettavaan muotoon frontissa ! 
		}
	}
	*/
	
	public ZonedDateTime getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = ZonedDateTime.parse(used);
	}

	public boolean read() {
		if (used == null && isValid) {
			used = ZonedDateTime.now();
			return true;

		} else {
			return false;
		}
	}

}
