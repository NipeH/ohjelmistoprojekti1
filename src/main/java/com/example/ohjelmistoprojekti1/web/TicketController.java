package com.example.ohjelmistoprojekti1.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ohjelmistoprojekti1.domain.classes.Event;
import com.example.ohjelmistoprojekti1.domain.classes.Ticket;
import com.example.ohjelmistoprojekti1.domain.repositories.EventRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketTypeRepository;

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

	// Deaktivoi lippu ja kasvata tai vähennä ko. tapahtuman ticketinventorya,
	// lähetetään bodyssa "isValid": "true" tai "false"
	@PatchMapping("/api/tickets/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Ticket editTicket(@RequestBody Map<String, Object> isValid, @PathVariable("id") Long id) {

		try {
			Ticket ticket = trepo.findById(id).get();
			Event event = ticket.getEvent();

			// Hakee pyynnöstä false tai true
			String val = (String) isValid.get("isValid");
			// deaktivoi lipun
			if (val.equals("false")) {
				ticket.setValid(false);
				event.setTicketInventory(event.getTicketInventory() + 1);
			}
			// aktivoi lipun
			if (val.equals("true")) {
				ticket.setValid(true);
				event.setTicketInventory(event.getTicketInventory() - 1);
			}

			return trepo.save(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	// hakee kaikki liput
	@GetMapping(value = "/api/tickets")
	public @ResponseBody List<Ticket> getAllTickets() {
		return (List<Ticket>) trepo.findAll();
	}

	// Hae lipun tiedot lippukoodin perusteella 
	@GetMapping("/api/tickets/{code}")
	@ResponseStatus(HttpStatus.OK)
	public Ticket getTicket(@PathVariable("code") UUID tcode) {
		try {

			return trepo.findByTicketcode(tcode).get(0);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// Lukee lipun ja palauttaa ok tai ei ok
	@PatchMapping("/api/tickets/read/{code}")
	@ResponseStatus(HttpStatus.OK)
	public String readTicket(@PathVariable("code") UUID tcode) {
		Ticket ticket = trepo.findByTicketcode(tcode).get(0);

		try {

			if (!ticket.isValid()) {
				return "Lippu on peruttu";
			}
			if (!(ticket.getUsed() == null)) {
				return "Lippu on jo käytetty";
			} else {
				ticket.read();
				trepo.save(ticket);
				return "OK!";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	// POISTA LIPPU
	@DeleteMapping("/api/tickets/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT) // 204 jos onnistuu
	public void deleteTicket(@PathVariable("id") @Min(1) Long id) {// parametri väh.1:n pituinen
		try {
			trepo.deleteById(id);
		} catch (Exception e) {
			// palauttaa status 404, jos id:tä vastaavaa eventiä ei löydy
			if (!trepo.findById(id).isPresent()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Found", e);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to delete event", e);
			}
		}
	}

}
