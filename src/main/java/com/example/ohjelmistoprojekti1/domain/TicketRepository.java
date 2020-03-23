package com.example.ohjelmistoprojekti1.domain;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
	List<Ticket> findByTicketcode(UUID ticketcode);

}
