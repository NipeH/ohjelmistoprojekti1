package com.example.ohjelmistoprojekti1.domain.repositories;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.ohjelmistoprojekti1.domain.classes.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
	List<Ticket> findByTicketcode(UUID ticketcode);

}
