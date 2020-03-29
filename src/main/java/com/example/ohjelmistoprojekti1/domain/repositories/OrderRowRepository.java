package com.example.ohjelmistoprojekti1.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ohjelmistoprojekti1.domain.classes.OrderRow;

public interface OrderRowRepository extends CrudRepository<OrderRow, Long> {

}
