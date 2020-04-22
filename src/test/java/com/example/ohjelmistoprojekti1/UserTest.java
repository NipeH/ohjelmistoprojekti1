package com.example.ohjelmistoprojekti1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.ohjelmistoprojekti1.domain.classes.User;
import com.example.ohjelmistoprojekti1.domain.repositories.UserRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.UserTypeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTest {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserTypeRepository userTypeRepo;
	
	@Test
	//testaa uuden käyttäjän lisäämisen
		public void NewUser() {
		
		User user = new User();
		user.setFirstname("Testi");
		user.setLastname("Testaaja");
		user.setPhonenumber("0419632587");
		user.setEmail("Testi.testaaja@testi.com");
		user.setUsername("TestaajaT");
		user.setPassword("testaus");
		user.setUsertype(userTypeRepo.findByUsertype("admin").get(0));
		userRepo.save(user);		
		
		equals(userRepo.findByUsername("TestaajaT"));
	}
}
