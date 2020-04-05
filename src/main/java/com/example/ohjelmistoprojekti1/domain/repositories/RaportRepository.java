package com.example.ohjelmistoprojekti1.domain.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.ohjelmistoprojekti1.domain.classes.Raport;


public interface RaportRepository extends CrudRepository <Raport, Long>{

	Raport findByTickettype(String tickettype);
}
