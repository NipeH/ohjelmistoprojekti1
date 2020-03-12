package com.example.ohjelmistoprojekti1.domain;

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
	@JsonIgnore
	@JoinColumn(name = "eventid")
	private Event event;

	private double price;

	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "tickettypeid")
	private TicketType type;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "orderid")
	private Order orders;

	private boolean isValid;

	public Ticket() {
		super();
	}

	public Ticket(@NotNull Event event,  TicketType type) {
		super();
		this.event = event;
		this.type = type;
	}

	public Ticket(@NotNull Event event, double price,  TicketType type) {
		super();
		this.event = event;
		this.price = price;
		this.type = type;
	}

	public Ticket(@NotNull Event event, double price,  TicketType type, Order orders) {
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

}
