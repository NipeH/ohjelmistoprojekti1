package com.example.ohjelmistoprojekti1.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderid;
	
	private double total;
	
	private Timestamp timestamp;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="order")
	private List<User> users;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="order")
	private List<Customer> customers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="order")
	private List<Ticket> tickets;

	public Order() {
		super();
	}

	public Order(long orderid, double total, Timestamp timestamp, List<User> users, List<Customer> customers,
			List<Ticket> tickets) {
		super();
		this.orderid = orderid;
		this.total = total;
		this.timestamp = timestamp;
		this.users = users;
		this.customers = customers;
		this.tickets = tickets;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
}
