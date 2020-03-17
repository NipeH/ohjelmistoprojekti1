package com.example.ohjelmistoprojekti1.domain;

public class AuthenticationResponse {
// tarvitaan tokenissa
	private final String jwt;
	
	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}
	
	public String getJwt() {
		return jwt;
	}
}
