package com.example.ohjelmistoprojekti1.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
import com.example.ohjelmistoprojekti1.domain.Order;
import com.example.ohjelmistoprojekti1.domain.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.OrderRow;
import com.example.ohjelmistoprojekti1.domain.OrderRowRepository;
import com.example.ohjelmistoprojekti1.domain.Ticket;
import com.example.ohjelmistoprojekti1.domain.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.TicketType;
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

	// PALAUTTAA 500?????
	// lisää tapahtuma
	@PostMapping(value = "/api/events")
	@ResponseStatus(value = HttpStatus.CREATED) // Palauttaa 201 onnistuessaan
	public @ResponseBody Event addEvent(@Valid @RequestBody Event event) {
		try {
			return erepo.save(event);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Tapahtuman luonti ei onnistunut, tarkista pakolliset kentät", e);

		}
	}

	// poista tapahtuma
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
	@GetMapping(value = "api/events/{id}")
	public @ResponseBody Optional<Event> eventById(@PathVariable("id") Long eventid) {
		ResponseStatusException e = new ResponseStatusException(HttpStatus.NOT_FOUND, "Tapahtumaa ei löydy");
		if (!erepo.findById(eventid).isPresent()) {
			throw e;
		} else {
			return erepo.findById(eventid);
		}

	}

	// hae tapahtumia eri tuntomerkeillä: property voi saada esim. arvoja name,
	// venue, description.
	@GetMapping("/api/events/search/{property}={value}")
	public @ResponseBody List<Event> eventByProperty(@PathVariable("property") String property,
			@PathVariable("value") String value) {
		List<Event> found = new ArrayList<>();
		if (property.equals("name")) {
			found.addAll(erepo.findByNameIgnoreCase(value));
		}
		if (property.equals("venue")) {
			ArrayList<Event> all = (ArrayList<Event>) erepo.findAll();
			for (Event event : all) {
				if (event.getVenue().toLowerCase().contains(value)) {
					found.add(event);
				}
			}
		}

		if (property.equals("description")) {
			ArrayList<Event> all = (ArrayList<Event>) erepo.findAll();
			for (Event event : all) {
				if (event.getDescription().toLowerCase().contains(value)) {
					found.add(event);
				}
			}
		}
		return found;
	}

	// Hae tapahtumaan myydyt liput
	@GetMapping(value = "api/events/{eventid}/tickets_sold")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Ticket> getTicketsOnEvent(@PathVariable("eventid") Long eventid) {
		Event event = erepo.findById(eventid)
				.orElseThrow(() -> new ResourceNotFoundException("No event with an id of " + eventid + " found"));

		return event.getTickets();
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

				if (newEventProperties.containsKey("startTime")) {
					event.setStartTime((String) newEventProperties.get("startTime"));
				}
				if (newEventProperties.containsKey("endTime")) {
					event.setEndTime((String) newEventProperties.get("endTime"));
				}

				return erepo.save(event);

			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/api/events/{eventid}/tickets")
	@ResponseStatus(value = HttpStatus.OK)
	public List<Ticket> getTicketOnEvent(@PathVariable("eventid") Long eventid) {
		Event event = erepo.findById(eventid)
				.orElseThrow(() -> new ResourceNotFoundException("No event with id: " + eventid + " found"));
		return event.getTickets();
	}

	/*
	 * //Lähetetään bodyssa orderid, ticketType (esim. 4, 5 tai 3) // LUODAAN UUSI
	 * LIPPU TAPAHTUMAAN, EDELLYTTÄEN, ETTÄ LIPPUJA ON VIELÄ SAATAVILLA
	 * 
	 * @PostMapping(value = "/api/events/{eventid}/tickets")
	 * 
	 * @ResponseStatus(value = HttpStatus.CREATED) public @ResponseBody Ticket
	 * addTicketOnEvent(@PathVariable("eventid") Long eventid,
	 * 
	 * @RequestBody Map<String, Object> ticketprops) {
	 * 
	 * //Hakee eventin bodyssa tulevalla eventid:lla Event event =
	 * erepo.findById(eventid) .orElseThrow(() -> new
	 * ResourceNotFoundException("No event with id: " + eventid + " found"));
	 * 
	 * Ticket t = new Ticket(); if (event.getTicketInventory() > 0) {
	 * t.setEvent(event); //jos bodyssa tulee orderid -> if
	 * (ticketprops.containsKey("orderid")) { //asetetaan orderidksi bodysta tuleva
	 * orderid orderin idksi Long orderid = Long.valueOf((String)
	 * ticketprops.get("orderid")); Order o = orepo.findById(orderid).orElse(new
	 * Order()); //o.addTicket(t); orepo.save(o); t.setOrders(o); }
	 * 
	 * //hae bodyssa tuleva tickettype string if
	 * (ticketprops.containsKey("tickettypeid")) { Long ttid = Long.valueOf((String)
	 * ticketprops.get("ticketType")); TicketType tt =
	 * ttrepo.findById(ttid).orElse(new TicketType()); t.setType(tt); }
	 * 
	 * trepo.save(t); } return t; }
	 */

	// NO NULL VALUES
	// Lähetetään bodyssa olemassa oleva (esim. 15 kovakoodattu) orderid,
	// tickettypeid (esim. 4, 5 tai 3) ja pcs lippujen lkm
	// LUODAAN UUSI LIPPU TAPAHTUMAAN, EDELLYTTÄEN, ETTÄ LIPPUJA ON VIELÄ SAATAVILLA
	@PostMapping(value = "/api/events/{eventid}/tickets")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody List<Ticket> tickets(@PathVariable("eventid") Long eventid,
			@RequestBody Map<String, Object> ticketprops) {

		List<Ticket> tickets = new ArrayList<>();

		try {
			// Hakee eventin bodyssa tulevalla eventid:lla
			Event event = erepo.findById(eventid)
					.orElseThrow(() -> new ResourceNotFoundException("No event with id: " + eventid + " found"));

			// Haetaan bodysta pcs eli lippujen lkm
			// oletuksena yksi lippu
			int pcs = 1;

//			Jos pyynnöstä löytyy lippujen lukumäärä, se talletetaan muuttujaan pcs
			if (ticketprops.containsKey("pcs")) {
				pcs = Integer.valueOf((String) ticketprops.get("pcs"));
			}

			// Haetaan lipputyyppi bodysta tulevalla id:llä, jos sellainen on
//			Oletuksena tickettypen id on (ttid = 4), eli aikuisten normaalihintainen lippu
			Long ttid = (long) 4;
			TicketType ttype = ttrepo.findById(ttid).get();

//			Jos lipun tyyppi mainitaan, se talletetaan muuttujaan ttype
			if (ticketprops.containsKey("tickettypeid")) {
				ttid = Long.valueOf((String) ticketprops.get("tickettypeid"));
				ttype = ttrepo.findById(ttid)
						.orElseThrow(() -> new EntityNotFoundException("no ticket type with id: " + " found"));
			}

			// Haetaan orderid
			Order o = new Order();
			if (ticketprops.containsKey("orderid")) {
				Long orderid = Long.valueOf((String) ticketprops.get("orderid"));
				o = orepo.findById(orderid)
						.orElseThrow(() -> new EntityNotFoundException("No order with id: " + orderid + " found."));
			} else {
				orepo.save(o);
			}

			// Varmistetaan että lippuja on saatavilla
			if (event.getTicketInventory() >= pcs) {
				// Luodaan niin monta lippua kuin bodyssa maaritelty
				for (int i = 1; i <= pcs; i++) {

					Ticket t = new Ticket();
					t.setEvent(event);
					t.setValid(true);
					t.setType(ttype);
					t.setOrders(o);
					// Hinta = eventin hinta jos lipputyyppi on 3 eli aikuinen
					if (ttype.getTicketypeid() == 4) {
						t.setPrice(event.getPrice());
						o.setTotal(o.getTotal() + t.getPrice());
					} else {
						t.setPrice(event.getPrice() * ttype.getDiscount());
						o.setTotal(o.getTotal() + t.getPrice());
					}

					//UUID class provides a simple means for generating unique ids. The identifiers generated by UUID are actually universally unique identifiers.
					//Lippukoodi
					UUID ticketcode = UUID.randomUUID();
					t.setTicketcode(ticketcode);
					
					// Vähennetään yksi inventorysta
					event.setTicketInventory(event.getTicketInventory() - 1);
					Long tid = trepo.save(t).getTicketid();
					tickets.add(trepo.findById(tid).get());

				}
				// jos lippuja ei saatavilla tarpeeksi <- tulee tyhjä lista.
			}

			return tickets;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Tarkista pakolliset kentät, orderid, pcs ja tyckettypeid", e);

		}
	}

	// TULOSTETAAN MYYMÄTTÄ OLEVAT LIPUT
	@PostMapping(value = "/api/events/{eventid}/available_tickets")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody List<Ticket> tickets(@PathVariable("eventid") Long eventid) {

		List<Ticket> tickets = new ArrayList<>();

		try {
			// Hakee eventin bodyssa tulevalla eventid:lla
			Event event = erepo.findById(eventid)
					.orElseThrow(() -> new ResourceNotFoundException("No event with id: " + eventid + " found"));

			// Haetaan jäljellä olevien lippujen lkm
			int pcs = event.getTicketInventory();

			// Luodaan niin monta lippua kuin on myymättä
			for (int i = 1; i <= pcs; i++) {
				Ticket t = new Ticket();

				t.setEvent(event);
				t.setValid(true);
				t.setPrice(event.getPrice());
				Long tid = trepo.save(t).getTicketid();
				tickets.add(trepo.findById(tid).get());
			}

			return tickets;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Tarkista pakolliset kentät, orderid, pcs ja tyckettypeid", e);

		}
	}

	/*
	 * POISTETAAN MYÖHEMMIN - TESTIVERSIO //Lähetetään bodyssa OrderRow, jossa:
	 * orderid, tickettypeid (esim. 4, 5 tai 3) ja pcs lippujen lkm // LUODAAN UUSI
	 * LIPPU TAPAHTUMAAN, EDELLYTTÄEN, ETTÄ LIPPUJA ON VIELÄ SAATAVILLA
	 * 
	 * @PostMapping(value = "/api/events/{eventid}/tickets")
	 * 
	 * @ResponseStatus(value = HttpStatus.CREATED) public @ResponseBody List<Ticket>
	 * tickets(@PathVariable("eventid") Long eventid, @RequestBody OrderRow or) {
	 * 
	 * List <Ticket>tickets = new ArrayList<>();
	 * 
	 * //Hakee eventin bodyssa tulevalla eventid:lla Event event =
	 * erepo.findById(eventid) .orElseThrow(() -> new
	 * ResourceNotFoundException("No event with id: " + eventid + " found"));
	 * 
	 * //tallentaa bodyna tulevan orrepo.save(or);
	 * 
	 * //Haetaan bodysta pcs eli lippujen lkm int pcs = or.getPcs(); String orderid
	 * = or.getOrderid(); String ttypeid = or.getTickettypeid();
	 * 
	 * //Periaatteessa orElseen ei mennä koska TicketType on pakollinen, mutta
	 * jatkoa varten näin.. TicketType ttype =
	 * ttrepo.findById(Long.parseLong(ttypeid)).orElse(new TicketType());
	 * 
	 * //===========================================================================
	 * =============
	 * 
	 * //Luodaan niin monta lippua kuin bodyssa maaritelty for(int i=1; i<= pcs;
	 * i++) { Ticket t = new Ticket(); //Varmistetaan että lippuja on saatavilla if
	 * (event.getTicketInventory() > 0) { t.setEvent(event); t.setValid(true); if
	 * (orderid != null) { Order o =
	 * orepo.findById(Long.parseLong(orderid)).orElse(new Order()); orepo.save(o);
	 * t.setOrders(o); } t.setType(ttype); //Hinta = eventin hinta jos lipputyyppi
	 * on 3 eli aikuinen if (ttype.getTicketypeid() == 3) {
	 * t.setPrice(event.getPrice()); } else { t.setPrice(event.getPrice() *
	 * ttype.getDiscount()); } //Vähennetään yksi inventorysta
	 * event.setTicketInventory(event.getTicketInventory()-1); Long tid =
	 * trepo.save(t).getTicketid(); tickets.add(trepo.findById(tid).get());
	 * 
	 * } // entä jos lippuja ei saatavilla?
	 * 
	 * }
	 * 
	 * return tickets; }
	 * 
	 */
}
