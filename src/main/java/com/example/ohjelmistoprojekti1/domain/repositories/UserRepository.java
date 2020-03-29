package com.example.ohjelmistoprojekti1.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ohjelmistoprojekti1.domain.classes.User;

public interface UserRepository extends CrudRepository<User, Long> {
        User findByUsername(String username);
}
