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
		Order ord= orepo.findById(orderid)
		.orElseThrow(() -> new ResourceNotFoundException("No order with an id of " + orderid + " found"));
		
		return ord.getTickets();
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

}
