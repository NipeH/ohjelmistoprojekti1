package com.example.ohjelmistoprojekti1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ohjelmistoprojekti1.domain.classes.Customer;
import com.example.ohjelmistoprojekti1.domain.classes.Event;
import com.example.ohjelmistoprojekti1.domain.classes.Order;
import com.example.ohjelmistoprojekti1.domain.classes.Ticket;
import com.example.ohjelmistoprojekti1.domain.classes.TicketType;
import com.example.ohjelmistoprojekti1.domain.classes.User;
import com.example.ohjelmistoprojekti1.domain.classes.UserType;
import com.example.ohjelmistoprojekti1.domain.repositories.CustomerRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.EventRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.OrderRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.TicketTypeRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.UserRepository;
import com.example.ohjelmistoprojekti1.domain.repositories.UserTypeRepository;

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
	
	//data transfer objects
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
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
			event.setStartTime("2020-09-16T19:00:00");
			event.setEndTime("2020-09-16T21:00:00");
			event.setTicketInventory(2);
			eventRepo.save(event);
			
			Event ruisrock = new Event();
			ruisrock.setName("Ruisrock");
			ruisrock.setDescription("Esiintyjinä mm. Major Lazer");
			ruisrock.setPrice(200);
			ruisrock.setVenue("Turun ruissalo");
			ruisrock.setStartTime("2020-07-16T19:00:00");
			ruisrock.setEndTime("2020-07-05T23:59:00");
			ruisrock.setTicketInventory(200);
			eventRepo.save(ruisrock);
			
			Event jazz = new Event();
			jazz.setName("Jazz");
			jazz.setDescription("Esiintyjinä Jazz");
			jazz.setPrice(100);
			jazz.setVenue("Pori");
			jazz.setStartTime("2020-07-16T19:00:00");
			jazz.setEndTime("2020-07-05T23:59:00");
			jazz.setTicketInventory(9);
			eventRepo.save(jazz);	

		
			
			
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
			UUID ticketcode = UUID.randomUUID();
			ticket3.setTicketcode(ticketcode);
			System.out.println("TESTILIPPUKOODI:" + ticketcode);
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
			
			//Admin
			User admin = new User();
			admin.setFirstname("Mestari");
			admin.setLastname("Johtaja");
			admin.setEmail("@netti.se");
			admin.setPhonenumber("0666");
			admin.setUsername("Admin");
			admin.setPassword("Admin");
			admin.setUsertype(userTypeRepo.findByUsertype("admin").get(0));
			userRepo.save(admin);

			//vain $2a alkuiset toimii, tällä saa: https://www.browserling.com/tools/bcrypt
			userRepo.save(new User ("Essi", "Kissa", "0408786", "1234@gmail.fi", "essi", "$2a$10$P.cgawwkrHmeCVQxxwIEJ.bIWfeAaqps.sVlVzxDEF9fcXZ4zag7i", userTypeRepo.findByUsertype("user").get(0)));
			//passw = essi
			userRepo.save(new User ("Admin", "Kissa", "0408786", "1234@gmail.fi", "admin", "$2a$10$WJzZRs49Nl5iTVQ3HaYIJOIi5Krqzd9z92bTeERbcMdkXR7ZFvarm", userTypeRepo.findByUsertype("admin").get(0)));	
			//passw = admin
			userRepo.save(new User("Niilo", "Nakki",  "0442420666", "asd@gmail.com", "niilo", "$2a$10$rOHR9t/k65shG6KwD1MED.xro5RYTDiSjS1X3LOWX6V/80uKQ7RiS" , userTypeRepo.findByUsertype("user").get(0)));
			// passw = salasana
			


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

