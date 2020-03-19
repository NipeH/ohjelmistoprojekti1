package com.example.ohjelmistoprojekti1.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ohjelmistoprojekti1.domain.Event;
import com.example.ohjelmistoprojekti1.domain.Order;
import com.example.ohjelmistoprojekti1.domain.Ticket;
import com.example.ohjelmistoprojekti1.domain.TicketType;
import com.example.ohjelmistoprojekti1.domain.User;
import com.example.ohjelmistoprojekti1.domain.UserRepository;
import com.example.ohjelmistoprojekti1.domain.UserType;
import com.example.ohjelmistoprojekti1.domain.UserTypeRepository;



@RestController
public class UserController {
	
	@Autowired
	private UserRepository urepo;

	@Autowired
	private UserTypeRepository utrepo;
	
	
	/*
	@PostMapping("/api/users")
	@ResponseStatus(value = HttpStatus.CREATED) // Palauttaa 201 onnistuessaan
	public @ResponseBody User newUser(@Valid @RequestBody User user) {
		

		try {
			//entä jos username on jo varattu ? unique value
			String passw = user.getPassword();
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
			String hash = bc.encode(passw);		
			user.setPassword(hash);
			return urepo.save(user);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Käyttäjän luonti ei onnistunut, tarkista pakolliset kentät", e);

		}
	
	}
	*/
	
	
	
	
	

	/*
	 *  {
        "firstname": "kuuu",
        "lastname": "Kissa",
        "phone": "1000",
        "email": "gmail",
        "username": "test",
        "password": "test",
		"usertype": "admin"
    	}
	 * 
	 * */
	// Uusi käyttäjä
	@PostMapping(value = "/api/users")
	@ResponseStatus(value = HttpStatus.CREATED)
	public User user(@RequestBody Map<String, Object> userinfo) {

		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		
		try {
		

				String firstname = String.valueOf(userinfo.get("firstname"));

				String lastname = String.valueOf(userinfo.get("lastname"));
	
				String phone = String.valueOf(userinfo.get("phone"));

				String email = String.valueOf(userinfo.get("email"));
	
				String username = String.valueOf(userinfo.get("username"));
				
				

				String password = String.valueOf(userinfo.get("password"));
				String hash = bc.encode(password);

			
				String usertype = String.valueOf(userinfo.get("usertype"));

				UserType type = utrepo.findByUsertype(usertype).get(0);			
				
				User user = new User();
				user.setFirstname(firstname);
				user.setLastname(lastname);
				user.setEmail(email);
				user.setPhonenumber(phone);
				user.setUsername(username);
				user.setPassword(hash);
				user.setUsertype(type);
				urepo.save(user);
				
									

			return user;
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Käyttäjä on jo olemassa", e);

		}
	}
	

}
