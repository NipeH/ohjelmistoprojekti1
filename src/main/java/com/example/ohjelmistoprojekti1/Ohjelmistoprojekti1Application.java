package com.example.ohjelmistoprojekti1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ohjelmistoprojekti1.domain.Customer;
import com.example.ohjelmistoprojekti1.domain.CustomerRepository;
import com.example.ohjelmistoprojekti1.domain.Event;
import com.example.ohjelmistoprojekti1.domain.EventRepository;
import com.example.ohjelmistoprojekti1.domain.Order;
import com.example.ohjelmistoprojekti1.domain.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.Ticket;
import com.example.ohjelmistoprojekti1.domain.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.TicketType;
import com.example.ohjelmistoprojekti1.domain.TicketTypeRepository;
import com.example.ohjelmistoprojekti1.domain.User;
import com.example.ohjelmistoprojekti1.domain.UserRepository;
import com.example.ohjelmistoprojekti1.domain.UserType;
import com.example.ohjelmistoprojekti1.domain.UserTypeRepository;

@SpringBootApplication
public class Ohjelmistoprojekti1Application {
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private TicketTypeRepository ticketTypeRepo;
	@Autowired
	private TicketRepository ticketRepo;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserTypeRepository userTypeRepo;
	@Autowired
	private CustomerRepository customerRepo;	

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
			event.setPrice(50);
			event.setVenue("Nipen takapiha. Kelivaraus. Poudan yllättäessä siirrymme roskakatokseen");
			event.setDate("2020-09-16");
			event.setTime("19:00:00");
			event.setTicketInventory(2);
			eventRepo.save(event);
			
			Event ruisrock = new Event();
			ruisrock.setName("Ruisrock");
			ruisrock.setDescription("Esiintyjinä mm. Major Lazer");
			ruisrock.setPrice(200);
			ruisrock.setVenue("Turun ruissalo");
			ruisrock.setDate("2020-07-16");
			ruisrock.setTime("19:00:00");
			ruisrock.setTicketInventory(200);
			eventRepo.save(ruisrock);				

			
			// TicketType
			TicketType normalTicket = new TicketType();
			ticketTypeRepo.save(normalTicket);

			ticketTypeRepo.save(new TicketType ("children", 0.5));

			ticketTypeRepo.save(new TicketType ("student", 0.3));
			
			// Ticket
			/*
			Ticket ticket = new Ticket(event, 10.00, "children", null);
			ticketRepo.save(ticket);
			
			Ticket ticket2 = new Ticket(ruisrock, 10.00, "student", null);
			ticketRepo.save(ticket2);
			*/
			
			
			ticketRepo.save(new Ticket (eventRepo.findByName("Ruisrock").get(0), ticketTypeRepo.findByType("children").get(0)));
			
			Ticket ticket3 = new Ticket();
			ticket3.setEvent(eventRepo.findByName("Ruisrock").get(0));
			ticket3.setType(ticketTypeRepo.findByType("student").get(0));
			ticketRepo.save(ticket3);
			
			
			//Usertype
			userTypeRepo.save(new UserType ("user"));
			userTypeRepo.save(new UserType ("admin"));

			
			//User
			User user = new User();
			user.setFirstname("Nipe");
			user.setLastname("Koira");
			user.setEmail("@gmail.vom");
			user.setPhonenumber("3435");
			user.setUsername("Nipe");
			user.setPassword("Nipe");
			user.setUsertype(userTypeRepo.findByUsertype("user").get(0));
			userRepo.save(user);
			
		
			userRepo.save(new User ("Essi", "Kissa", "0408786", "1234@gmail.fi", "essi", "essi", userTypeRepo.findByUsertype("user").get(0)));
			userRepo.save(new User ("Kissa-admin", "Kissa", "0408786", "1234@gmail.fi", "essi", "essi", userTypeRepo.findByUsertype("admin").get(0)));

			
			
			//Customer
			Customer customer = new Customer();	
			customer.setFirstname("Essi");
			customer.setLastname("Kissa");
			customer.setAddress("Tinatie 3");
			customer.setEmail("hotmail.fi");
			customer.setPhone("040565");
			customerRepo.save(customer);
			
			/*
			//Order
			Order order = new Order();
			order.setTotal(30.5);
			order.setDate("2020-18-02");
			order.setUser(null);
			order.setCustomer(null);
			order.setTickets(null);
			*/
		};
	}
}

