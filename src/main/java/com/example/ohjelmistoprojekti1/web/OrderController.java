package com.example.ohjelmistoprojekti1.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	// Luodaan uusi myyntitapahtuma
	// Lähetetään urlissa eventid ja lipputyyppi typeid sekä kpl määrä || lipputyyppi 3, 4 tai 5
	@PostMapping(value = "api/orders/{eventid}/{typeid}/{lkm}")
	@ResponseStatus(value = HttpStatus.CREATED) // Palauttaa 201 onnistuessaan
	public List<Ticket> tickets (@PathVariable("eventid") Long eventid, @PathVariable("typeid") Long typeid, @PathVariable ("lkm") int lkm) {

		List <Ticket>tickets = new ArrayList<>();
		Order order = new Order();
		Long orderid = orepo.save(order).getOrderid();
		//orepo.save(order);
		
		// haetaan parametrinä tulevalla typeidllä TicketType olio
		TicketType ttype = ttrepo.findById(typeid).get();
		Event event = erepo.findById(eventid).get();

	for (int i=1; i <= lkm; i++) {
		Ticket ticket = new Ticket();
	
		
			ticket.setEvent(event);
			ticket.setOrders(order); // samassa yhteydessä luotu orderi
			ticket.setValid(true); // lippu voimassa
			ticket.setType(ttype); // asetetaan lipputyyppi
			if (ttype.getTicketypeid() == 3) { // jos on aikuisten lippu, eli id 3, ei kerrota alella
				ticket.setPrice(event.getPrice());
			} else {
			ticket.setPrice(event.getPrice() * ttype.getDiscount()); // haetaan eventin hinta ja kerrotaan se
			}
			event.setTicketInventory(event.getTicketInventory()-1); // vähentää jäljellä olevista lipuista yhden lipun
			
			
			Long ticketid = trepo.save(ticket).getTicketid();
			tickets.add(trepo.findById(ticketid).get());
			
	
	}
	
	//juu ei toimi :D
	//	orepo.findById(orderid).get().setTotal(lkm * erepo.findById(eventid).get().getPrice() * ttrepo.findById(typeid).get().getDiscount());
		event.setTickets(tickets);
		return tickets;
	}

	// ALUSTAVA - EI TOIMI - ONKO TARPEELLINENKAAN
	// IDEANA JOS HALUTAAN LÄHETTÄÄ TICKETTYYPPI BODYSSA

	// Luodaan uusi myyntitapahtuma
	// Lähetetään urlissa eventid ja lipputyyppi bodyssa (tickettydeid)
	// Entä jos halutaan tallentaa monta lippua samaan orderiin? lähetetäänkö kpl
	// määrä?
	// - vai tarvitaanko sittenkin yksi taulu vielä? OrderRow ja Order ?
/*	@PostMapping(value = "api/orders/{eventid}/")
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
*/
	// Hae kaikki jarjestelmassa olevat tilaukset
	@GetMapping(value = "api/orders")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Order> getAll() {
		return (List<Order>) orepo.findAll();
	}

	// Hae tietty tilaus
	@GetMapping(value = "api/orders/{orderid}")
	public @ResponseBody Order getOrderById(@PathVariable("orderid") Long orderid) {
		return orepo.findById(orderid)
				.orElseThrow(() -> new ResourceNotFoundException("No order with an id of " + orderid + " found"));
	}
	
	//Hae tietyn tilauksen kaikki liput 
	@GetMapping(value = "api/orders/{orderid}/tickets")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Ticket> getTicketsOnOrder(@PathVariable("orderid") Long orderid) {
		Order ord= orepo.findById(orderid)
		.orElseThrow(() -> new ResourceNotFoundException("No order with an id of " + orderid + " found"));
		
		return ord.getTickets();
	}

	// Luo tyhja tilauspohja
	@PostMapping(value = "api/orders")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody Optional<Order> newOrder(@RequestBody Order ord) {
		if (!orepo.existsById(ord.getOrderid())) {
			orepo.save(ord);
		}
		return orepo.findById(ord.getOrderid());
	}

}
