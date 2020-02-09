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
	
}
