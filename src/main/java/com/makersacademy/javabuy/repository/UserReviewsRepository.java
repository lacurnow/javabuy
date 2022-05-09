package com.makersacademy.javabuy.repository;

import com.makersacademy.javabuy.model.User;
import com.makersacademy.javabuy.model.UserReview;

import org.springframework.data.repository.CrudRepository;

public interface UserReviewsRepository extends CrudRepository<UserReview, Long> {
  Iterable<UserReview> findByUserOrderByTimestampDesc(User user);
}
