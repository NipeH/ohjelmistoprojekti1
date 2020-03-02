package com.example.ohjelmistoprojekti1.web;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.ohjelmistoprojekti1.domain.CustomerRepository;
import com.example.ohjelmistoprojekti1.domain.Event;
import com.example.ohjelmistoprojekti1.domain.EventRepository;
import com.example.ohjelmistoprojekti1.domain.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.TicketTypeRepository;
import com.example.ohjelmistoprojekti1.domain.UserRepository;
import com.example.ohjelmistoprojekti1.domain.UserTypeRepository;

@RestController
@Validated
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

	// jos parametrillä ei löydy palautetaan 404 EITOIMIIIIII
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
		return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// lisää tapahtuma
	@PostMapping(value = "/add/event")
	@ResponseStatus(value = HttpStatus.CREATED) // Palauttaa 201 onnistuessaan
	public Event addEvent(@Valid @RequestBody Event event) {
		return erepo.save(event);
	}

	// poista
	@DeleteMapping("/delete/event/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT) // 204 jos onnistuu
	public void deleteEvent(@PathVariable("id") /* @Min(1) */Long eventid) { // vähintään 1 pituinen paramentri
																				// annettava
		try {
			erepo.deleteById(eventid);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Found", e);
		}
	}

	// muokkaa
	@PutMapping("/edit/event/{id}")
	public Event editEvent(@Valid @RequestBody Event editEvent, @PathVariable("id") @Min(1) Long eventid) {

		return erepo.findById(eventid).map(event -> {
			event.setName(editEvent.getName());
			event.setVenue(editEvent.getVenue());
			event.setTime(editEvent.getTimeStr());
			event.setDate(editEvent.getDate());
			event.setDescription(editEvent.getDescription());
			event.setPrice(editEvent.getPrice());
			event.setTicketInventory(editEvent.getTicketInventory());
			return erepo.save(event);
		}).orElseGet(() -> {
			editEvent.setEventid(eventid);
			return erepo.save(editEvent);
		});

	}

	// hakee kaikki tapahtumat
	@GetMapping(value = "/events")
	public @ResponseBody List<Event> RestEvents() {
		return (List<Event>) erepo.findAll();
	}

	// hae parametrina tulevalla idllä
	@GetMapping(value = "/events/{id}")
	public @ResponseBody Optional<Event> eventById(@PathVariable("id") Long eventid) {
		// try {
		return erepo.findById(eventid);
		/*
		 * } catch (NotFoundException ex) { throw new ResponseStatusException(
		 * HttpStatus.NOT_FOUND, "Tapahtumaa ei löydy", ex); }
		 */
	}

	// hae parametrina tulevalla nimellä
	@RequestMapping(value = "/event/{name}", method = RequestMethod.GET)
	public @ResponseBody List<Event> eventByName(@PathVariable("name") String name) {
		return erepo.findByNameIgnoreCase(name);
	}

}
