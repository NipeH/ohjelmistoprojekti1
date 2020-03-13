package com.example.ohjelmistoprojekti1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


//Tämä on vain datan siirtoa varten! Tarvitaan eventcontrollerissa myyntitapahtuman luonnissa.
@Entity
public class OrderRow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;
	
	@NotNull
	private String tickettypeid;
	
	private String orderid;
	
	@NotNull
	private int pcs;

	public OrderRow() {
		super();
	}
	

	public OrderRow(@NotNull String tickettypeid, String orderid, @NotNull int pcs) {
		super();
		this.tickettypeid = tickettypeid;
		this.orderid = orderid;
		this.pcs = pcs;
	}



	public String getTickettypeid() {
		return tickettypeid;
	}

	public void setTickettypeid(String tickettypeid) {
		this.tickettypeid = tickettypeid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public int getPcs() {
		return pcs;
	}

	public void setPcs(int pcs) {
		this.pcs = pcs;
	}

}
