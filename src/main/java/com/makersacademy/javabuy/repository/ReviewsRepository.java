package com.makersacademy.javabuy.repository;

import com.makersacademy.javabuy.model.Review;

import org.springframework.data.repository.CrudRepository;

public interface ReviewsRepository extends CrudRepository<Review, Long> {

  Iterable<Review> findByProductId(Long product_id);

}