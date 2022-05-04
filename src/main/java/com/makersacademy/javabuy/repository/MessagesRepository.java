package com.makersacademy.javabuy.repository;

import com.makersacademy.javabuy.model.Message;

import org.springframework.data.repository.CrudRepository;

public interface MessagesRepository extends CrudRepository<Message, Long> {
}
