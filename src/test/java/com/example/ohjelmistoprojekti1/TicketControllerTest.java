package com.example.ohjelmistoprojekti1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ohjelmistoprojekti1.domain.classes.Event;
import com.example.ohjelmistoprojekti1.domain.classes.Order;
import com.example.ohjelmistoprojekti1.domain.classes.Ticket;
import com.example.ohjelmistoprojekti1.domain.repositories.EventRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketTypeRepository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TicketControllerTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private TicketTypeRepository ticketTypeRepo;
	@Autowired
	private TicketRepository ticketRepo;
	
	@Test
	public void checkCredidentials() throws Exception {
		TestRestTemplate testRestTemplate
		 = new TestRestTemplate("niilo", "salasana");
		ResponseEntity<String> response = testRestTemplate.
		  getForEntity("http://localhost:" + port + "/", String.class);
		  
		assertEquals(response.getStatusCode(), (HttpStatus.OK));
	}
	
	
	
	
	/*
	@Test
	public void ApiTicketsCodeReturnsTicket() throws Exception {
		
		//test ticket
		Event jazz = new Event();
		jazz.setName("Jazzit");
		jazz.setDescription("Esiintyjinä Essi");
		jazz.setPrice(100);
		jazz.setVenue("Pori");
		jazz.setStartTime("2020-07-16T19:00:00");
		jazz.setEndTime("2020-07-05T23:59:00");
		jazz.setTicketInventory(9);
		eventRepo.save(jazz);	
		
		Ticket ticket = new Ticket (eventRepo.findByName("Jazzit").get(0), 20.4, ticketTypeRepo.findByType("student").get(0), new Order(), true, UUID.randomUUID());
		ticketRepo.save(ticket);
		
		String code = ticket.getTicketcode().toString();

		
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/tickets/" +code,
				String.class)).contains("Hello, World");
	}
*/
}
