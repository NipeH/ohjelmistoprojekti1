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

	@NotNull
	private double price;

	@NotNull
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "tickettypeid")
	private TicketType type;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "orderid")
	private Order order;

	
	
	public Ticket() {
		super();
	}

	public Ticket(@NotNull Event event, @NotNull double price, @NotNull TicketType type) {
		super();
		this.event = event;
		this.price = price;
		this.type = type;
	}

	public Ticket(@NotNull Event event, @NotNull double price, @NotNull TicketType type, Order order) {
		super();
		this.event = event;
		this.price = price;
		this.type = type;
		this.order = order;
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
		this.price = price;
	}

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	

}
