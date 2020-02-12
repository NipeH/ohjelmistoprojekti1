package com.example.ohjelmistoprojekti1.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

public class UserType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long usertypeid;
	
	@NotNull
	private String usertype;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usertype")
	private List<User> users;

	public UserType() {
		super();
	}

	public UserType(long usertypeid, @NotNull String usertype, List<User> users) {
		super();
		this.usertypeid = usertypeid;
		this.usertype = usertype;
		this.users = users;
	}

	public long getUsertypeid() {
		return usertypeid;
	}

	public void setUsertypeid(long usertypeid) {
		this.usertypeid = usertypeid;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	

}
