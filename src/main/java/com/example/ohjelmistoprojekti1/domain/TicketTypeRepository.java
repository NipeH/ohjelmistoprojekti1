package com.example.ohjelmistoprojekti1.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TicketTypeRepository extends CrudRepository<TicketType, Long> {

	List<TicketType> findByType (String Type);
}
