package com.makersacademy.javabuy.repository;

import com.makersacademy.javabuy.model.User;
import com.makersacademy.javabuy.model.UserReview;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserReviewsRepository extends CrudRepository<UserReview, Long> {
  Iterable<UserReview> findByUserOrderByTimestampDesc(User user);

  Integer countByUser(User user);

  @Query("SELECT ROUND(AVG(rating),0) FROM UserReview u WHERE u.user = ?1")
  Integer findRoundedAverageRatingByUser(User user);

}
