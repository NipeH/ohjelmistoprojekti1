package com.example.ohjelmistoprojekti1.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	List <Customer> findByLastName (String lastname);
	List <Customer> findByLastNameIgnoreCase (String lastname);
	

}