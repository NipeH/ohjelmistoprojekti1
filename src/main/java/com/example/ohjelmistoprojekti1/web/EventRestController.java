package com.example.ohjelmistoprojekti1.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping(value="/events")
	public @ResponseBody List <Event> RestEvents(){
		return (List<Event>) erepo.findAll();
	}

	
	//lisää tapahtuma
    @PostMapping("/add/event")
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
	@GetMapping(value="/event/{id}")
    public @ResponseBody Optional<Event> eventById(@PathVariable("id") Long eventid) {	
    return erepo.findById(eventid);
    }     
	
	//hae parametrina tulevalla nimellä
	@RequestMapping(value="/event/{name}", method = RequestMethod.GET)
    public @ResponseBody List<Event> eventByName(@PathVariable("name") String name) {	
    return erepo.findByNameIgnoreCase(name);
    } 	
	
	//poista
	@DeleteMapping("/delete/event/{id}")		
	void deleteEvent(@PathVariable("id") Long eventid ) {
		erepo.deleteById(eventid);
	}
	
	//muokkaa	
    @PutMapping("/edit/event/{id}")
	public Event editEvent(@RequestBody Event editEvent, @PathVariable("id") Long eventid) {	

    	return erepo.findById(eventid)
    			.map(event -> {
    				event.setName(editEvent.getName());
    				event.setVenue(editEvent.getVenue());
    				event.setTime(editEvent.getTime());
    				event.setDate(editEvent.getDate());
    				event.setDescription(editEvent.getDescription());
    				event.setPrice(editEvent.getPrice());
    				event.setTicketInventory(editEvent.getTicketInventory());
    				return erepo.save(event);
    			}) 
    			.orElseGet(() -> {
    				editEvent.setEventid(eventid);
    				return erepo.save(editEvent);
    			});
		
	}		

	
}


