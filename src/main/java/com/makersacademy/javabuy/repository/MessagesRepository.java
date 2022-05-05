package com.makersacademy.javabuy.repository;

import java.util.List;

import com.makersacademy.javabuy.model.Message;
import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessagesRepository extends CrudRepository<Message, Long> {
  @Query("SELECT m FROM Message m WHERE m.product = ?1 and m.enquirer = ?2")
  Iterable<Message> findMessagesByProductAndEnquirer(Product product, User enquirer);

  @Query("SELECT DISTINCT m.enquirer FROM Message m WHERE m.product = ?1")
  Iterable<User> findEnquirersByProduct(Product product);
  Iterable<Message> findAllBySellerOrderByTimestamp(User seller);

}
