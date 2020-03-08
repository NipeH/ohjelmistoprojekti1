package com.example.ohjelmistoprojekti1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ohjelmistoprojekti1.domain.CustomerRepository;
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

	// Luodaan uusi myyntitapahtuma
	// Lähetetään urlissa eventid ja lipputyyppi typeid
	// Entä jos halutaan tallentaa monta lippua samaan orderiin? lähetetäänkö kpl
	// määrä?
	// - vai tarvitaanko sittenkin yksi taulu vielä? OrderRow ja Order ? jotta
	// yhdessä myyntitapahtumassa voi olla useita lippuja
	@PostMapping(value = "api/orders/{eventid}/{typeid}")
	@ResponseStatus(value = HttpStatus.CREATED) // Palauttaa 201 onnistuessaan
	public Ticket ticket(@PathVariable("eventid") Long eventid, @PathVariable("typeid") Long typeid) {
		Ticket ticket = new Ticket();
		Order order = new Order();

		// haetaan parametrinä tulevalla typeidllä TicketType olio
		ttrepo.findById(typeid);
		TicketType ttype = ttrepo.findById(typeid).get();

		orepo.save(order);

		// haetaan tickettiin parametrinä tuleva tapahtuma
		// tarviikohan tässä ees mappaysta.. no toimii anyways
		return erepo.findById(eventid).map(event -> {
			ticket.setEvent(event);
			ticket.setOrders(order); // samassa yhteydessä luotu orderi
			ticket.setValid(true); // lippu voimassa
			ticket.setType(ttype); // asetetaan lipputyyppi
			ticket.setPrice(event.getPrice() * ttype.getDiscount()); // haetaan eventin hinta ja kerrotaan se
																		// lipputyypin alella
			return trepo.save(ticket);
		}).orElseThrow(() -> new ResourceNotFoundException("Eventid " + eventid + " not found")); //

	}

	// ALUSTAVA - EI TOIMI - ONKO TARPEELLINENKAAN
	// IDEANA JOS HALUTAAN LÄHETTÄÄ TICKETTYYPPI BODYSSA

	// Luodaan uusi myyntitapahtuma
	// Lähetetään urlissa eventid ja lipputyyppi bodyssa (tickettydeid)
	// Entä jos halutaan tallentaa monta lippua samaan orderiin? lähetetäänkö kpl
	// määrä?
	// - vai tarvitaanko sittenkin yksi taulu vielä? OrderRow ja Order ?
	@PostMapping(value = "api/orders/{eventid}/")
	@ResponseStatus(value = HttpStatus.CREATED) // Palauttaa 201 onnistuessaan
	public Ticket ticket(@PathVariable("eventid") Long eventid, @RequestBody TicketType ttype) {
		Ticket ticket = new Ticket();
		Order order = new Order();
		// ttype.getTicketypeid();

		orepo.save(order);

		// haetaan tickettiin parametrinä tuleva tapahtuma
		return erepo.findById(eventid).map(event -> {
			ticket.setEvent(event);
			ticket.setOrders(order); // samassa yhteydessä luotu orderi
			ticket.setValid(true); // lippu voimassa
			ticket.setType(ttype);
			ticket.setPrice(event.getPrice() * ttype.getDiscount()); // haetaan eventin hinta ja kerrotaan valitun
																		// tickettyypin alennuksella
			return trepo.save(ticket);
		}).orElseThrow(() -> new ResourceNotFoundException("Eventid " + eventid + " not found")); //

	}
	
	@GetMapping(value = "api/orders")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Order> getAll() {
		return (List<Order>) orepo.findAll();
	}

	 
}
