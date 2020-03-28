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

import com.example.ohjelmistoprojekti1.domain.CustomerRepository;
import com.example.ohjelmistoprojekti1.domain.Event;
import com.example.ohjelmistoprojekti1.domain.EventRepository;
import com.example.ohjelmistoprojekti1.domain.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.Ticket;
import com.example.ohjelmistoprojekti1.domain.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.TicketTypeRepository;
import com.example.ohjelmistoprojekti1.domain.UserRepository;
import com.example.ohjelmistoprojekti1.domain.UserTypeRepository;



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
	
	@RequestMapping(value = {"/essi"})
	public String essi() {
		return "essi";
	}
	
	/*
	 * @RequestMapping(value = "/findTicket") public @ResponseBody List<Event>
	 * RestEvents() { return (List<Event>) erepo.findAll(); }
	 */
    @RequestMapping(value= "/findTicket{id}")
    public String EventList(Model model) {	
        model.addAttribute("events", erepo.findAll());
        return "index";
    }
    
	@RequestMapping("/findTicket{code}")
	@ResponseStatus(HttpStatus.OK)
	public Ticket getTicket (@PathVariable("code") UUID tcode) {
		try {
			
			return trepo.findByTicketcode(tcode).get(0);
			
		}	catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}














