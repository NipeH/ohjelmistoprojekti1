package com.example.ohjelmistoprojekti1.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ohjelmistoprojekti1.domain.classes.Event;
import com.example.ohjelmistoprojekti1.domain.classes.Order;
import com.example.ohjelmistoprojekti1.domain.classes.Ticket;
import com.example.ohjelmistoprojekti1.domain.classes.TicketType;
import com.example.ohjelmistoprojekti1.domain.repositories.CustomerRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.EventRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketTypeRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.UserRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.UserTypeRepository;

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

	// HAE KAIKKI JÄRJESTELMÄSSÄ OLEVAT TILAUKSET:
	@GetMapping(value = "api/orders")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Order> getAll() {
		return (List<Order>) orepo.findAll();
	}

	// HAE TIETTY TILAUS:
	@GetMapping(value = "api/orders/{orderid}")
	public @ResponseBody Order getOrderById(@PathVariable("orderid") Long orderid) {
		return orepo.findById(orderid)
				.orElseThrow(() -> new ResourceNotFoundException("No order with an id of " + orderid + " found"));
	}

	// HAE TIETTYYN TILAUKSEEN KUULUVAT KAIKKI LIPUT:
	@GetMapping(value = "api/orders/{orderid}/tickets")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Ticket> getTicketsOnOrder(@PathVariable("orderid") Long orderid) {
		Order ord = orepo.findById(orderid)
				.orElseThrow(() -> new ResourceNotFoundException("No order with an id of " + orderid + " found"));

		return ord.getTickets();
	}

	@PostMapping(value = "api/orders/{orderid}/tickets")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody List<Ticket> newTicket(@RequestBody Map<String, Object> props,
			@PathVariable("orderid") Long orderid) {
		/*
		 * try { Ticket ticket = new Ticket();
		 * ticket.setOrders(orepo.findById(orderid).get());
		 * ticket.setEvent(erepo.findById((Long) props.get("eventid")).get()); return
		 * trepo.save(ticket);
		 */

		try {
			props.put("orderid", orderid);
			Long eventid = (Long) props.get("eventid");
			EventController ec = new EventController();
			return ec.tickets(eventid, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	// LUO TYHJÄ TILAUSPOHJA:
	@PostMapping(value = "api/orders")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody Optional<Order> newOrder(@RequestBody Order ord) {
		if (!orepo.existsById(ord.getOrderid())) {
			orepo.save(ord);
		}
		return orepo.findById(ord.getOrderid());
	}

	@DeleteMapping(value = "api/orders/{orderid}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable Long orderid) {
		try {
			orepo.deleteById(orderid);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
