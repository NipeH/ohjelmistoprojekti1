package com.example.ohjelmistoprojekti1.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity
public class TicketType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ticketTypeId;

	@NotNull
	private String type;

	private double discount;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="type")
	private List<Ticket> tickets;
 
 

	public TicketType() {
		super();
		this.type = "Normal";
		this.discount = 0.0;
	}

	public TicketType(@NotNull String type, double discount) {
		super();
		this.type = type;
		this.discount = discount;
	}
	
//muita?
	public TicketType(long ticketTypeId, @NotNull String type, double discount, List<Ticket> tickets) {
		super();
		this.ticketTypeId = ticketTypeId;
		this.type = type;
		this.discount = discount;
		this.tickets = tickets;
	}

	public long getTicketTypeId() {
		return ticketTypeId;
	}

	public void setTicketTypeId(long ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (ticketTypeId ^ (ticketTypeId >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketType other = (TicketType) obj;
		if (Double.doubleToLongBits(discount) != Double.doubleToLongBits(other.discount))
			return false;
		if (ticketTypeId != other.ticketTypeId)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
