package com.example.ohjelmistoprojekti1.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ohjelmistoprojekti1.domain.CustomerRepository;
import com.example.ohjelmistoprojekti1.domain.Event;
import com.example.ohjelmistoprojekti1.domain.EventRepository;
import com.example.ohjelmistoprojekti1.domain.Order;
import com.example.ohjelmistoprojekti1.domain.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.Ticket;
import com.example.ohjelmistoprojekti1.domain.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.TicketType;
import com.example.ohjelmistoprojekti1.domain.TicketTypeRepository;
import com.example.ohjelmistoprojekti1.domain.UserRepository;
import com.example.ohjelmistoprojekti1.domain.UserTypeRepository;



@RestController
public class OrderController {
	@Autowired
	private CustomerRepository crepo;

	@Autowired
	private EventRepository erepo;

	@Autowired
	private OrderRepository orepo;

	@Autowired
	private TicketRepository trepo;

	@Autowired
	private TicketTypeRepository ttrepo;

	@Autowired
	private UserRepository urepo;

	@Autowired
	private UserTypeRepository utrepo;
	
	
	//Luodaan uusi myyntitapahtuma
	//Lähetetään urlissa eventid ja lipputyyppi typeid 
	//Entä jos halutaan tallentaa monta lippua samaan orderiin? lähetetäänkö kpl määrä? RequestBody ticket
	// - vai tarvitaanko sittenkin yksi taulu vielä? OrderRow ja Order ? 
	@PostMapping(value = "/orders/{eventid}/{typeid}")
	@ResponseStatus(value = HttpStatus.CREATED) // Palauttaa 201 onnistuessaan
	public Ticket ticket (@PathVariable("eventid") Long eventid, @PathVariable("typeid") Long typeid) {
		Ticket ticket = new Ticket();
		Order order = new Order();
		
	//	TicketType type = ttrepo.findById(typeid);
		
		//haetaan tämä pvm ja muutetaan tallennetaan today stringiin
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String today = dtf.format(localDate);
		
		//asetetaan orderille current date ja tallennetaan
		//tähän vaihdetaan order.detDate(today) kun se toimii
		order.setToday(today);
		orepo.save(order);
		

		//haetaan tickettiin parametrinä tuleva tapahtuma
		return erepo.findById(eventid).map(event -> {
			ticket.setEvent(event);
			ticket.setOrders(order); //samassa yhteydessä luotu orderi
			ticket.setValid(true); //lippu voimassa	
	//		ticket.setType(type);
			return trepo.save(ticket);
		}).orElseThrow(() -> new ResourceNotFoundException("Eventid " + eventid + " not found")); //		
		
		/*	miten tämä saadaan tungettua johonkin?
		 * 		
					ttrepo.findById(typeid).map(tickettype -> {
						ticket.setType(tickettype);
					});
		*/

		
	}

	
	
	//Luodaan uusi myyntitapahtuma
	//Lähetetään urlissa eventid ja lipputyyppi bodyssa (tickettydeid) 
	//Entä jos halutaan tallentaa monta lippua samaan orderiin? lähetetäänkö kpl määrä? RequestBody ticket
	// - vai tarvitaanko sittenkin yksi taulu vielä? OrderRow ja Order ? 
	@PostMapping(value = "/orders/{eventid}/")
	@ResponseStatus(value = HttpStatus.CREATED) // Palauttaa 201 onnistuessaan
	public Ticket ticket (@PathVariable("eventid") Long eventid, @RequestBody TicketType ttype) {
		Ticket ticket = new Ticket();
		Order order = new Order();

		
		//haetaan tämä pvm ja muutetaan tallennetaan today stringiin
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String today = dtf.format(localDate);
		
		//asetetaan orderille current date ja tallennetaan
		//tähän vaihdetaan order.detDate(today) kun se toimii
		order.setToday(today);
		orepo.save(order);
		

		//haetaan tickettiin parametrinä tuleva tapahtuma
		return erepo.findById(eventid).map(event -> {
			ticket.setEvent(event);
			ticket.setOrders(order); //samassa yhteydessä luotu orderi
			ticket.setValid(true); //lippu voimassa	
			ticket.setType(ttype);
			return trepo.save(ticket);
		}).orElseThrow(() -> new ResourceNotFoundException("Eventid " + eventid + " not found")); //		
		
		/*	miten tämä saadaan tungettua johonkin?
		 * 		
					ttrepo.findById(typeid).map(tickettype -> {
						ticket.setType(tickettype);
					});
		*/

		
	}
	

}
