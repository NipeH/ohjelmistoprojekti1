package com.example.ohjelmistoprojekti1.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ohjelmistoprojekti1.domain.CustomerRepository;
import com.example.ohjelmistoprojekti1.domain.Event;
import com.example.ohjelmistoprojekti1.domain.EventRepository;
import com.example.ohjelmistoprojekti1.domain.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.TicketTypeRepository;
import com.example.ohjelmistoprojekti1.domain.UserRepository;
import com.example.ohjelmistoprojekti1.domain.UserTypeRepository;



@Controller
public class AppController {
	
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
	
	@RequestMapping(value = "/add")
	public String addEvent (Model model) {
	model.addAttribute("event", new Event());
	return "add";
	}
	
	@RequestMapping(value="/save",  method= RequestMethod.POST)
	public String saveEvent(Event event, Model model) {
		erepo.save(event);
		return "redirect:index";
	}
	
	@RequestMapping(value = {"/index", "/", "events"})
	public String allEvents(Model model) {
		model.addAttribute("events", erepo.findAll());
		return "events";
	}
	
	//muokkaa - template puuttuu
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editEvent(@PathVariable("id") Long eventid, Model model){
    model.addAttribute("event", erepo.findById(eventid));
    return "edit";
}
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable("id") Long eventid) {
    erepo.deleteById(eventid);
    return "redirect:../events";
    

}

}













