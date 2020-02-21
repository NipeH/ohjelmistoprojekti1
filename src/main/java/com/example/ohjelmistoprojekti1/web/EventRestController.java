package com.example.ohjelmistoprojekti1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ohjelmistoprojekti1.domain.CustomerRepository;
import com.example.ohjelmistoprojekti1.domain.Event;
import com.example.ohjelmistoprojekti1.domain.EventRepository;
import com.example.ohjelmistoprojekti1.domain.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.TicketTypeRepository;
import com.example.ohjelmistoprojekti1.domain.UserRepository;
import com.example.ohjelmistoprojekti1.domain.UserTypeRepository;



@RestController
public class EventRestController {
	
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
	
	@RequestMapping(value="/events", method = RequestMethod.GET)
	public @ResponseBody List <Event> RestEvents(){
		return (List<Event>) erepo.findAll();
	}
	
	
	//tesstejä, ei toimi... 
	@RequestMapping(value="/Ruisrock", method = RequestMethod.GET)
	public @ResponseBody List <Event> RestEventsname(){
		return (List<Event>) erepo.findByName("Ruisrock");
	}
}


