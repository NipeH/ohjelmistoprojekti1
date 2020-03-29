package com.example.ohjelmistoprojekti1.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ohjelmistoprojekti1.domain.classes.UserType;



public interface UserTypeRepository extends CrudRepository<UserType, Long> {

List<UserType> findByUsertype(String usertype);

}
