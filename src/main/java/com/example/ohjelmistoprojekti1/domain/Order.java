package com.example.ohjelmistoprojekti1.domain;

import java.time.ZonedDateTime;
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
	@JsonIgnore
	private List<Ticket> tickets;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "customerid")
	private Customer customer;

	public Order() {
		super();
		timestamp = ZonedDateTime.now();
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
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

}
