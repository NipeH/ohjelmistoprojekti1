package com.example.ohjelmistoprojekti1.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface UserTypeRepository extends CrudRepository<UserType, Long> {

/*	List<UserType> findByName(String usertype); */

}
