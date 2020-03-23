package com.example.ohjelmistoprojekti1.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
public class User {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userid;
	
	private String firstname;
	
	private String lastname;
	
	private String phonenumber;
	
	
	private String email;
	
    @Column(name = "username", unique = true) 
	@NotNull(message="Anna käyttäjätunnus")
	@JsonIgnore
	private String username;
	
	@NotNull(message="Anna salasana")
	//@JsonIgnore
	private String password;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usertypeid")
	private UserType usertype;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private List<Order> orders;


	public User() {
		super();
		
	}





	public User(String firstname, String lastname, String phonenumber, String email,
			@NotNull(message = "Anna käyttäjätunnus") String username,
			@NotNull(message = "Anna salasana") String password, UserType usertype, List<Order> orders) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.orders = orders;
	}





	public User(String firstname, String lastname, String phonenumber, String email,
			@NotNull(message = "Anna käyttäjätunnus") String username,
			@NotNull(message = "Anna salasana") String password, UserType usertype) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}





	public long getUserid() {
		return userid;
	}


	public void setUserid(long userid) {
		this.userid = userid;
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


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}
	


	public void setPassword(String password) {
		this.password = password;
	}




	public UserType getUsertype() {
		return usertype;
	}


	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	

}
