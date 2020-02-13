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

//test

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

	
/*	
	@ManyToOne
	@JoinColumn(name="orderId")
	private Order order;
*/
	
	public Customer() {
		super();
	}


//getterit ja setterit yms. kun joini kunnossa
	
}
