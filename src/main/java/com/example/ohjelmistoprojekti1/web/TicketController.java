package com.example.ohjelmistoprojekti1.web;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ohjelmistoprojekti1.domain.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.Ticket;
import com.example.ohjelmistoprojekti1.domain.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.TicketTypeRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ohjelmistoprojekti1.domain.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.Ticket;
import com.example.ohjelmistoprojekti1.domain.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.TicketTypeRepository;
import com.example.ohjelmistoprojekti1.domain.EventRepository;


@RestController
public class TicketController {
	
	@Autowired
	private OrderRepository orepo;

	@Autowired
	private TicketRepository trepo;

	@Autowired
	private TicketTypeRepository ttrepo;
	
	@Autowired
	private EventRepository erepo;

	// Muokkaa lippua, ainakin deaktivointi, lähetetään bodyssa "isValid": "true" tai "false"
	@PatchMapping("/api/tickets/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Ticket editTicket (@RequestBody Map<String, Object> isValid, @PathVariable("id") Long id) {
		
		try {
		Ticket ticket = trepo.findById(id).get();
		
			String val = (String) isValid.get("isValid");
			if (val.equals("false")) {
				ticket.setValid(false);
			}	
			if (val.equals("true")) {
				ticket.setValid(true);
			}	

		 return trepo.save(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	//Hae lipun tiedot lippukoodin perusteella (URL)
	@GetMapping("/api/tickets/{code}")
	@ResponseStatus(HttpStatus.OK)
	public Ticket getTicket (@PathVariable("code") UUID tcode) {
		try {
			
			return trepo.findByTicketcode(tcode).get(0);
			
		}	catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	//tämä urli on vähäh huono, voisi vaihtaa
	//Lue lippu / käytä lippu
	@PatchMapping("/api/tickets/read/{code}")
	@ResponseStatus(HttpStatus.OK)
	public Ticket readTicket (@PathVariable("code") UUID tcode) {
		Ticket ticket = trepo.findByTicketcode(tcode).get(0);		
		
		try {	
			ticket.read();
			return trepo.save(ticket);
			
		
		}	catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}	
	
	
	
}


















