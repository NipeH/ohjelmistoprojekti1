package com.example.ohjelmistoprojekti1;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.ohjelmistoprojekti1.domain.classes.Event;
import com.example.ohjelmistoprojekti1.domain.classes.Order;
import com.example.ohjelmistoprojekti1.domain.classes.Ticket;
import com.example.ohjelmistoprojekti1.domain.classes.TicketType;
import com.example.ohjelmistoprojekti1.domain.repositories.EventRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketTypeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TicketTest {
	
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private TicketTypeRepository ticketTypeRepo;
	@Autowired
	private TicketRepository ticketRepo;
	

	
	@Test
	//check that new tickets are valid for use = used = null
	public void notUsed() {
		
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
		ticketRepo.save(ticket);
		
	    // Get the current date time 
	    LocalDateTime time = LocalDateTime.now();
		
		ticket.read();
		
		Assert.assertTrue(time.isBefore(ticket.getUsed()) || time.isEqual(ticket.getUsed())  );
	}


}

