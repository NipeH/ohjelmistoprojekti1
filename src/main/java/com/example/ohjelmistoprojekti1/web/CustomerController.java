package com.example.ohjelmistoprojekti1.web;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
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

import com.example.ohjelmistoprojekti1.domain.Customer;
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
public class CustomerController {
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
	
	//tässä mahdollisuus verkkokauppaan käyttjän rekisteröintiin vaatii toimiakseen lisäksi ainakin RegisterCustomer 

	//@GetMapping("/registerNewCustomer")
	//public String addNewCustomer(Model model) {
	//	System.out.println("registerNewCustomer");
	//	model.addAttribute("registerCustomer", new RegisterCustomer());
	//	return "register";

//	}
	//${}

	// jos parametrillä ei löydy palautetaan 404 EITOIMIIIIII
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
		return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// lisää asiakas
	@PostMapping(value = "/add/customer")
	@ResponseStatus(value = HttpStatus.CREATED) // Palauttaa 201 onnistuessaan
	public Customer addCustomer(@Valid @RequestBody Customer customer) {
		try {
			return crepo.save(customer);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Asiakkaan luonti ei onnistunut, tarkista pakolliset kentät", e);

		}
	}

	// poista
	@DeleteMapping("/delete/customer/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT) // 204 jos onnistuu
	public void deleteCustomer(@PathVariable("id") @Min(1) Long customerid) {
		try {
			crepo.deleteById(customerid);
		} catch (Exception e) {
			// palauttaa status 404
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Found", e);
		}
	}


	// muokkaa
	@PutMapping("/put/customer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Customer overrideCustomer(@Valid @RequestBody Customer editCustomer, @PathVariable("id") @Min(1) Long customerid) {

	try {
		return crepo.findById(customerid).map(customer -> {
			customer.setFirstname(editCustomer.getFirstname());
			customer.setLastname(editCustomer.getLastname());
			customer.setAddress(editCustomer.getAddress());
			customer.setEmail(editCustomer.getEmail());
			customer.setPhone(editCustomer.getPhone());

			return crepo.save(customer);
		}).orElseGet(() -> {
			editCustomer.setCustomerid(customerid);
			return crepo.save(editCustomer);
		});
	} catch (Exception e) {
		e.printStackTrace();
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}

	}

	// hakee kaikki asiakkaat
	@GetMapping(value = "/customers")
	public @ResponseBody List<Customer> RestCustomers() {
	return (List<Customer>) crepo.findAll();
	}

	// hae parametrina tulevalla idllä
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/customers/{id}")
	public @ResponseBody Optional<Customer> CustomerById(@PathVariable("id") Long customerid) {
	ResponseStatusException e = new ResponseStatusException(HttpStatus.NOT_FOUND, "Asiakasta ei löydy");
	if (!crepo.findById(customerid).isPresent()) {
		throw e;
	} else {
		return crepo.findById(customerid);
	}

	}

	// hae parametrina tulevalla sukunimellä
	@RequestMapping(value = "/customer/{name}", method = RequestMethod.GET)
	public @ResponseBody List<Customer> customerByLastName(@PathVariable("lastname") String lastname) {
		return crepo.findByLastnameIgnoreCase(lastname);
	}

	// muokkaa
	@PatchMapping("/edit/customer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Customer editCustomer(@Valid @RequestBody Customer editCustomer, @PathVariable("id") @Min(1) Long customerid) {

		try {
			return crepo.findById(customerid).map(customer -> {
				customer.setLastname(editCustomer.getLastname());
				customer.setAddress(editCustomer.getAddress());
				customer.setEmail(editCustomer.getEmail());
				customer.setPhone(editCustomer.getPhone());
				return crepo.save(customer);
			}).orElseGet(() -> {
				editCustomer.setCustomerid(customerid);
				return crepo.save(editCustomer);
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
}
