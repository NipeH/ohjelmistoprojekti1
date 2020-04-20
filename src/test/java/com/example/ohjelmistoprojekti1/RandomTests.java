package com.example.ohjelmistoprojekti1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ohjelmistoprojekti1.domain.classes.Event;
//import com.example.ohjelmistoprojekti1.domain.classes.Order;
//import com.example.ohjelmistoprojekti1.domain.classes.Ticket;
import com.example.ohjelmistoprojekti1.domain.repositories.EventRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketTypeRepository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
//import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RandomTests {
	
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
		//Test that login returns 401 with wrong creds, integ
		public void checkCredidentials() throws Exception {
			TestRestTemplate testRestTemplate = new TestRestTemplate("niilo", "kissakoira");
			ResponseEntity<String> response = testRestTemplate.
			  getForEntity("http://localhost:" + port + "/", String.class);
			  
			assertEquals(response.getStatusCode(), (HttpStatus.UNAUTHORIZED));
		}
	

	  	@Test
	  	//Test get-method api/events, integ
		public void getEventListing() throws URISyntaxException {
	        //add user details
			TestRestTemplate testRestTemplate = new TestRestTemplate("niilo", "salasana");
		    final String baseUrl = "http://localhost:" + port + "/api/events";
		    URI uri = new URI(baseUrl);
		 
		    ResponseEntity<String> result = testRestTemplate.getForEntity(uri, String.class);

		    assertEquals(200, result.getStatusCodeValue());
		    assertEquals(true, result.getBody().contains("eventid"));
	  	}
	  	
	  	
	    @Test
	    //test adding event, e2e
	    public void testAddEvent() throws URISyntaxException 
	    {
	        final String baseUrl = "http://localhost:"+port+"/api/events/";
	        URI uri = new URI(baseUrl);
	        //add user details
	  		TestRestTemplate testRestTemplate = new TestRestTemplate("niilo", "salasana");
	        Event event = new Event("Adam Pier show", "Adam esiintyy", 13.0, "Tampere", 3);
	        
	        /* if needed add to request below
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("add header here", "true");      
	 		*/
	        
	        HttpEntity<Event> request = new HttpEntity<>(event);
	         
	        ResponseEntity<String> result = testRestTemplate.postForEntity(uri, request, String.class);
	         
	        assertEquals(201, result.getStatusCodeValue());
	    }
	
	
	
}
