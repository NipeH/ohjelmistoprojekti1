package com.example.ohjelmistoprojekti1.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ohjelmistoprojekti1.domain.classes.Order;

public interface OrderRepository extends CrudRepository <Order, Long> {

}
