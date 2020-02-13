package com.example.ohjelmistoprojekti1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;*/
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long customerid;
	
	//@NotNull(message="Syötä arvo")
	//@Size(min = 2, message="Vähintään 3 kirjainta")
	private String firstname;
	
	//@NotNull(message="Syötä arvo")
	private String lastname;
	
	private String email;

	private String address;
	
	//@NotNull(message="Syötä arvo")
	private String phone;

	
	@ManyToOne
	@JoinColumn(name = "orderid")
	private Order order;
	
	public Customer() {
		super();
	}

	public Customer(long customerid, String firstname, String lastname, String email, String address, String phone,
			Order order) {
		super();
		this.customerid = customerid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.order = order;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	
}
