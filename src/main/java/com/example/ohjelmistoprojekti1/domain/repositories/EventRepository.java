package com.example.ohjelmistoprojekti1.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ohjelmistoprojekti1.domain.classes.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

	List<Event> findByName(String name);

	List<Event> findByNameIgnoreCase(String name);

//	List <Event> findByDate(String date);
	List<Event> findByVenueIgnoreCase(String venue);
}
