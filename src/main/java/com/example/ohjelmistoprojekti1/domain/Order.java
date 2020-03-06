package com.example.ohjelmistoprojekti1.domain;


import java.sql.Date;
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
	
	private Date date;

	//poistetaan kun date toimii..
	private String today;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy="orders")
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
	}


	//poistetaan
	public Order(double total, String today, List<Ticket> tickets) {
		super();
		this.total = total;
		this.today = today;
		this.tickets = tickets;
	}



	public Order(double total, Date date, List<Ticket> tickets, User user, Customer customer) {
		super();
		this.total = total;
		this.date = date;
		this.tickets = tickets;
		this.user = user;
		this.customer = customer;
	}
	
	


	public Order(Date date) {
		super();
		this.date = date;
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


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	/** @param date parameter in form: "yyyy-mm-dd" */
	public void setDate(String date) {
		this.date = java.sql.Date.valueOf(date);
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


	//poistetaan
	public String getToday() {
		return today;
	}


	public void setToday(String today) {
		this.today = today;
	}
	
	
	


	
}
