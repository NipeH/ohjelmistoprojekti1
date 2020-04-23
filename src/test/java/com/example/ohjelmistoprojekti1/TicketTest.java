package com.example.ohjelmistoprojekti1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.ohjelmistoprojekti1.domain.classes.Event;
import com.example.ohjelmistoprojekti1.domain.classes.Order;
import com.example.ohjelmistoprojekti1.domain.classes.Ticket;
import com.example.ohjelmistoprojekti1.domain.repositories.EventRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketTypeRepository;

@DataJpaTest
@RunWith(JUnitPlatform.class)
@ActiveProfiles("test")
public class TicketTest {
	
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private TicketTypeRepository ticketTypeRepo;
	
	
	@Test
	//check that new tickets are valid for use (used = null)
	public void notUsed() {
		
		//new event is needed to save a ticket
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
		
		assertEquals(ticket.getUsed(), null);
	}
	
	@Test
	//check that ticket reading marks ticket as used
	public void readTicket() {
		
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
		
	    // Get the current date time 
	    LocalDateTime time = LocalDateTime.now();
		
		ticket.read();
		
		assertTrue(time.isBefore(ticket.getUsed()) || time.isEqual(ticket.getUsed())  );
	}


}

