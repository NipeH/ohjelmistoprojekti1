package com.example.ohjelmistoprojekti1.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ohjelmistoprojekti1.domain.CustomerRepository;
import com.example.ohjelmistoprojekti1.domain.Event;
import com.example.ohjelmistoprojekti1.domain.EventRepository;
import com.example.ohjelmistoprojekti1.domain.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.Ticket;
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
	
	//hakee kaikki tapahtumat
	@RequestMapping(value="/eventsrest", method = RequestMethod.GET)
	public @ResponseBody List <Event> RestEvents(){
		return (List<Event>) erepo.findAll();
	}
	
	//hae kaikki liput
	@RequestMapping(value="/alltickets", method = RequestMethod.GET)
	public @ResponseBody List <Ticket> allTickets(){
		return (List<Ticket>) trepo.findAll();
	}
	
	//lisää tapahtuma
    @PostMapping("/api/addevent")
    public Event addEvent(@RequestBody Event event) {    
    return erepo.save(event);
    }
    
	//lisää lippu
  //lisää lippu
    /*
    @PostMapping("/api/addticket")
    public Ticket addTicketEvent(@RequestBody Ticket ticket) {    
    return trepo.save(ticket);
    }
	*/
	

	
	//hae parametrina tulevalla idllä
	@RequestMapping(value="/event/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Event> eventById(@PathVariable("id") Long eventid) {	
    return erepo.findById(eventid);
    } 
	
	//hae parametrina tulevalla nimellä
	@RequestMapping(value="/events/{name}", method = RequestMethod.GET)
    public @ResponseBody List<Event> eventByName(@PathVariable("name") String name) {	
    return erepo.findByNameIgnoreCase(name);
    } 	
	
	
}


