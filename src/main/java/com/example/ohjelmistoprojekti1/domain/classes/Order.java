package com.example.ohjelmistoprojekti1.domain.classes;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "\"Order\"")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderid;

	private double total;

	private ZonedDateTime timestamp;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
	private List<Ticket> tickets;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne
	@JoinColumn(name = "customerid")
	private Customer customer;

	public Order() {
		super();
		timestamp = ZonedDateTime.now();
		tickets = new ArrayList<>();
	}

	public String getTimestamp() {
		return timestamp.toString();
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void addTicket(Ticket t) {
		tickets.add(t);
		
	}

}
