package com.example.ohjelmistoprojekti1.domain.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//Class for salesraport object
@Entity
public class Raport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String tickettype;
	private int pcs;
	private double total;

	public Raport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Raport(String tickettype, int pcs, double total) {
		super();
		this.tickettype = tickettype;
		this.pcs = pcs;
		this.total = total;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTickettype() {
		return tickettype;
	}

	public void setTickettype(String tickettype) {
		this.tickettype = tickettype;
	}

	public int getPcs() {
		return pcs;
	}

	public void setPcs(int pcs) {
		this.pcs = pcs;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}


}
