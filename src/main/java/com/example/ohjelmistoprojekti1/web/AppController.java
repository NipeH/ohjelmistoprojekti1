package com.example.ohjelmistoprojekti1.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.ohjelmistoprojekti1.domain.classes.Event;
import com.example.ohjelmistoprojekti1.domain.classes.Ticket;
import com.example.ohjelmistoprojekti1.domain.repositories.CustomerRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.EventRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketTypeRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.UserRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.UserTypeRepository;




//Controller for pages
@Controller
public class AppController {
	
	@Autowired
	private OrderRepository orepo;

	@Autowired
	private TicketRepository trepo;

	@Autowired
	private TicketTypeRepository ttrepo;
	
	@Autowired
	private EventRepository erepo;
	
	
	@RequestMapping(value = {"/index", "/"})
	public String index() {
		return "index";
	}	
	
	
}














