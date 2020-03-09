package com.example.ohjelmistoprojekti1.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class EventController {

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
	@PostMapping(value = "/api/events")
	@ResponseStatus(value = HttpStatus.CREATED) // Palauttaa 201 onnistuessaan
	public Event addEvent(@Valid @RequestBody Event event) {
		try {
			return erepo.save(event);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Tapahtuman luonti ei onnistunut, tarkista pakolliset kentät", e);

		}
	}

	// poista
	@DeleteMapping("/api/events/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT) // 204 jos onnistuu
	public void deleteEvent(@PathVariable("id") @Min(1) Long eventid) {// parametri väh.1:n pituinen
		try {
			erepo.deleteById(eventid);
		} catch (Exception e) {
			// palauttaa status 404, jos id:tä vastaavaa eventiä ei löydy
			if (!erepo.findById(eventid).isPresent()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Found", e);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to delete event", e);
			}
		}
	}

	// muokkaa PUT-metodilla -> ylikirjoittava, idempotentti metodi
	@PutMapping("/api/events/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Event overrideEvent(@Valid @RequestBody Event editEvent, @PathVariable("id") @Min(1) Long eventid) {

		try {
			return erepo.findById(eventid).map(event -> {
				event.setName(editEvent.getName());
				event.setVenue(editEvent.getVenue());
//				event.setTime(editEvent.getTime());
//				event.setDate(editEvent.getDate());
				event.setDescription(editEvent.getDescription());
				event.setPrice(editEvent.getPrice());
				event.setTicketInventory(editEvent.getTicketInventory());
				return erepo.save(event);
			}).orElseGet(() -> {
				editEvent.setEventid(eventid);
				return erepo.save(editEvent);
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	// hakee kaikki tapahtumat
	@GetMapping(value = "/api/events")
	public @ResponseBody List<Event> RestEvents() {
		return (List<Event>) erepo.findAll();
	}

	// hae parametrina tulevalla idllä
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "api//events/{id}")
	public @ResponseBody Optional<Event> eventById(@PathVariable("id") Long eventid) {
		ResponseStatusException e = new ResponseStatusException(HttpStatus.NOT_FOUND, "Tapahtumaa ei löydy");
		if (!erepo.findById(eventid).isPresent()) {
			throw e;
		} else {
			return erepo.findById(eventid);
		}

	}

	// hae parametrina tulevalla nimellä
	@GetMapping("/api/events/search/{name}={value}")
	public @ResponseBody List<Event> eventByProperty(@PathVariable("name") String name,
			@PathVariable("value") String value) {
		List<Event> found = new ArrayList<>();
		if (name.equals("name")) {
			found.addAll(erepo.findByNameIgnoreCase(value));
		}
		if (name.equals("venue")) {
			ArrayList<Event> all = (ArrayList<Event>) erepo.findAll();
			for (Event event : all) {
				if (event.getVenue().toLowerCase().contains(value)) {
					found.add(event);
				}
			}
		}
		
		if (name.equals("description")) {
			ArrayList<Event> all = (ArrayList<Event>) erepo.findAll();
			for (Event event : all) {
				if (event.getDescription().toLowerCase().contains(value)) {
					found.add(event);
				}
			}
		}
		return found;
	}

	// muokkaa
	@PatchMapping("/api/events/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Event editEvent(@Valid @RequestBody Map<String, Object> newEventProperties,
			@PathVariable("id") @Min(1) Long eventid) {
		try {

			return erepo.findById(eventid).map(event -> {

//				käydään läpi, mitä arvoja pyyntö sisältää:
				if (newEventProperties.containsKey("name")) {
					event.setName((String) newEventProperties.get("name"));
				}

				if (newEventProperties.containsKey("venue")) {
					event.setVenue((String) newEventProperties.get("venue"));
				}

				if (newEventProperties.containsKey("description")) {
					event.setDescription((String) newEventProperties.get("description"));
				}

				if (newEventProperties.containsKey("price")) {
					event.setPrice((double) newEventProperties.get("price"));
				}

				if (newEventProperties.containsKey("ticketInventory")) {
					event.setTicketInventory((int) newEventProperties.get("ticketInventory"));
				}

				return erepo.save(event);
//			}).orElseGet(() -> {
//				editEvent.setEventid(eventid);
//				return erepo.save(editEvent);
			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
}
