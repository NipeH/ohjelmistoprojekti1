package com.example.ohjelmistoprojekti1.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long customerid;
	
	@NotNull(message="Syötä arvo")
	@Size(min = 2, message="Vähintään 3 kirjainta")
	private String firstname;
	
	@NotNull(message="Syötä arvo")
	private String lastname;
	
	private String email;

	private String address;
	
	@NotNull(message="Syötä arvo")
	private String phone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="customer")
	private List<Order> orders;

	
	public Customer() {
		super();
	}


	public Customer(String firstname, String lastname, String email, String address, String phone, List<Order> orders) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.orders = orders;
	}


	public long getCustomerid() {
		return customerid;
	}


	public void setCustomerid(long customerid) {
		this.customerid = customerid;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}



	
}
