package com.example.ohjelmistoprojekti1.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ohjelmistoprojekti1.domain.classes.User;
import com.example.ohjelmistoprojekti1.domain.classes.UserType;
import com.example.ohjelmistoprojekti1.domain.repositories.UserRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.UserTypeRepository;



@RestController
public class UserController {
	
	@Autowired
	private UserRepository urepo;

	@Autowired
	private UserTypeRepository utrepo;
	
	
	//Vain admin voi lisätä käyttäjiä
	@PreAuthorize("hasAuthority('admin')") 
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
	@PreAuthorize("hasAuthority('admin')") 
	@DeleteMapping(value = "api/users/{userid}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Long userid) {
		try {
			urepo.deleteById(userid);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}


}
