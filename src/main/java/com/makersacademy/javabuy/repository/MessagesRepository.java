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

  // @Query("SELECT DISTINCT m.product FROM Message m WHERE m.seller = ?1")

  @Query("SELECT m FROM Message m WHERE m.enquirer = ?1 AND m.timestamp IN (SELECT MAX(m.timestamp) FROM Message m GROUP BY m.enquirer, m.product) ORDER BY m.timestamp DESC")
  Iterable<Message> findByEnquirerGroupByEnquirerAndProduct(User enquirer);

  @Query("SELECT m FROM Message m WHERE m.seller = ?1 AND m.timestamp IN (SELECT MAX(m.timestamp) FROM Message m GROUP BY m.enquirer, m.product) ORDER BY m.timestamp DESC")
  Iterable<Message> findBySellerGroupByEnquirerAndProduct(User seller);

  @Query("SELECT DISTINCT m.enquirer FROM Message m WHERE m.product = ?1")
  Iterable<User> findEnquirersByProduct(Product product);
  Iterable<Message> findAllBySellerOrderByTimestamp(User seller);

}
