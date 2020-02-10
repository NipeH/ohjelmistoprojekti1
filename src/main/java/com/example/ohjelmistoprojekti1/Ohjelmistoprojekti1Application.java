package com.example.ohjelmistoprojekti1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ohjelmistoprojekti1.domain.Event;
import com.example.ohjelmistoprojekti1.domain.EventRepository;
import com.example.ohjelmistoprojekti1.domain.Ticket;
import com.example.ohjelmistoprojekti1.domain.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.TicketType;
import com.example.ohjelmistoprojekti1.domain.TicketTypeRepository;

@SpringBootApplication
public class Ohjelmistoprojekti1Application {
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private TicketTypeRepository ticketTypeRepo;
	@Autowired
	private TicketRepository ticketRepo;

	public static void main(String[] args) {
		SpringApplication.run(Ohjelmistoprojekti1Application.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			// Test here repositories with some demo data:

			// event & event repo:
			Event event = new Event();
			event.setName("Syksyn sävel");
			event.setDescription("Suomen luonnon ja vuodenaikojen innoittama konserttiesitys."
					+ " Soittimina tusina sadeputkea ja märkä rätti");
			event.setVenue("Nipen takapiha. Kelivaraus. Poudan yllättäessä siirrymme roskakatokseen");
			event.setDate("2020-09-16");
			event.setTime("19:00:00");
			event.setTicketInventory(2);
			eventRepo.save(event);

			// TicketType
			TicketType normalTicket = new TicketType();
			ticketTypeRepo.save(normalTicket);

			TicketType childTicket = new TicketType("child", 0.5);
			ticketTypeRepo.save(childTicket);

			// Ticket
			Ticket ticket = new Ticket(event, 10.00, childTicket);
			ticketRepo.save(ticket);
		};
	}

}
