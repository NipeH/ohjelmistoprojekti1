package com.example.ohjelmistoprojekti1.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ohjelmistoprojekti1.domain.classes.TicketType;

public interface TicketTypeRepository extends CrudRepository<TicketType, Long> {

	List<TicketType> findByType (String Type);
}
