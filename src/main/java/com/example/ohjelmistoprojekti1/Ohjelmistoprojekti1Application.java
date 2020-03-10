package com.example.ohjelmistoprojekti1;

import java.util.ArrayList;
import java.util.List;

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
//			event.setDate("2020-09-16");
//			event.setTime("19:00");
			event.setStartTime("2020-09-16T19:00:00");
			event.setEndTime("2020-09-16T21:00:00");
			event.setTicketInventory(2);
			eventRepo.save(event);
			
			Event ruisrock = new Event();
			ruisrock.setName("Ruisrock");
			ruisrock.setDescription("Esiintyjinä mm. Major Lazer");
			ruisrock.setPrice(200);
			ruisrock.setVenue("Turun ruissalo");
//			ruisrock.setDate("2020-07-16");
			ruisrock.setStartTime("2020-07-16T19:00:00");
			ruisrock.setEndTime("2020-07-05T23:59:00");
			ruisrock.setTicketInventory(200);
			eventRepo.save(ruisrock);				

		
			
			
			// TicketType
			TicketType normalTicket = new TicketType();
			ticketTypeRepo.save(normalTicket);

			ticketTypeRepo.save(new TicketType ("children", 0.5));
			ticketTypeRepo.save(new TicketType ("student", 0.8));
			
			// Ticket		
			
			ticketRepo.save(new Ticket (eventRepo.findByName("Ruisrock").get(0), ticketTypeRepo.findByType("children").get(0)));
			
			Ticket ticket3 = new Ticket();
			ticket3.setEvent(eventRepo.findByName("Ruisrock").get(0));
			ticket3.setType(ticketTypeRepo.findByType("student").get(0));
			ticket3.setValid(true);
			ticketRepo.save(ticket3);
			
			Ticket ticket2 = new Ticket();
			ticket2.setEvent(eventRepo.findByName("Syksyn sävel").get(0));
			ticket2.setType(ticketTypeRepo.findByType("student").get(0));
			ticket2.setValid(true);
			ticketRepo.save(ticket2);
			
			
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
			
			
			//Order
			Order order = new Order();
			order.setTotal(30.5);
			order.setUser(null);
			order.setCustomer(customer);
			List<Ticket> liput = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
			liput.add(new Ticket(event, 5.0, normalTicket));
			
			}
			
			order.setTickets(liput);
			orderRepo.save(order);
			
			Order tilaus2 = new Order();
			tilaus2.setTotal(20.0);
			tilaus2.setCustomer(customer);
			tilaus2.setUser(null);
			event.setEventid(1);
			event.setTicketInventory(-1);
			List<Ticket> liput2 = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
			liput2.add(new Ticket(event, 20.0, normalTicket));
			
			}
			
			order.setTickets(liput2);
			
			
			orderRepo.save(tilaus2);
			
			System.out.println("\nEnd of commandLineRunner\n");
		};
	}
}

