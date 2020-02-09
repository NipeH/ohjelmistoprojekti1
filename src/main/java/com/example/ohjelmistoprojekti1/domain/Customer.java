package com.example.ohjelmistoprojekti1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;*/
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long customerid;
	
	//@NotNull(message="Syötä arvo")
	//@Size(min = 2, message="Vähintään 2 kirjainta")
	private String firstname;
	
	//@NotNull(message="Syötä arvo")
	//@Size(min = 2, message="Vähintään 2 kirjainta")
	private String lastname;
	
	//@NotNull(message="Syötä arvo")
	//@Size(min = 2, message="Vähintään 2 kirjainta")
	private String email;
	
	//@NotNull(message="Syötä arvo")
	//@Size(min = 2, message="Vähintään 2 kirjainta")
	private String address;
	
	//@NotNull(message="Syötä arvo")
	//@Size(min = 2, message="Vähintään 2 kirjainta")
	private String phone;

	
/*	JOINAUS: OSTO LUOKAN NIMI JA ID:
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="OSTOLUOKAN PK ID")
	private Osto ???;
*/
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}


public Customer(long customerid, String firstname, String lastname, String email, String address, String phone) {
	super();
	this.customerid = customerid;
	this.firstname = firstname;
	this.lastname = lastname;
	this.email = email;
	this.address = address;
	this.phone = phone;
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


@Override
public String toString() {
	return "Customer [customerid=" + customerid + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
			+ email + ", address=" + address + ", phone=" + phone + "]";
}
	

	
}
