package com.example.ohjelmistoprojekti1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ticketId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "EventId")
	private Event event;

	@NotNull
	private double price;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "TicketTypeId")
	private TicketType type;

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(@NotNull Event event, @NotNull double price, @NotNull TicketType type) {
		super();
		this.event = event;
		this.price = price;
		this.type = type;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
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

}
