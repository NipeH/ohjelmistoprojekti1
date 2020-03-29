package com.example.ohjelmistoprojekti1.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ohjelmistoprojekti1.domain.classes.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	List <Customer> findByLastname (String lastname);
	List <Customer> findByLastnameIgnoreCase (String lastname);
	

}