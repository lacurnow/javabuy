package com.makersacademy.javabuy.repository;

import com.makersacademy.javabuy.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
}
