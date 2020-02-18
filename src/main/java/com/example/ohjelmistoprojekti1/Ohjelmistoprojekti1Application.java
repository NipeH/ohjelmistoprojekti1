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
			Ticket ticket = new Ticket(event, 10.00, childTicket, null);
			ticketRepo.save(ticket);
			
			
/*			//Usertype
			userTypeRepo.save(new UserType ("User"));
			userTypeRepo.save(new UserType ("Admin"));
*/
			
			//User
			User user = new User();
			user.setFirstname("Nipe");
			user.setLastname("Koira");
			user.setEmail("@gmail.vom");
			user.setPhonenumber("3435");
			user.setUsername("Nipe");
			user.setPassword("Nipe");
			userRepo.save(user);
			
/*		
*			userRepo.save(new User ("Essi", "Kissa", "Koira", "1234", "essi", "essi", userTypeRepo.findByName("User").get(0)));
*
 * 		
 */
			
			
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

