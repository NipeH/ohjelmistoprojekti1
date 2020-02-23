package com.example.ohjelmistoprojekti1.domain;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long>{
	
	List <Event> findByName (String name);
	List <Event> findByNameIgnoreCase (String name);
	List <Event> findByDate(String date);
}
