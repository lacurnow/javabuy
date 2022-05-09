package com.makersacademy.javabuy.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_REVIEWS")

public class UserReview {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private Integer rating;
  private Timestamp timestamp;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userid")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "authorid")
  private User author;

  public UserReview() {
  }

  public UserReview(String title, String content, Integer rating) {
    this.title = title;
    this.content = content;
    this.rating = rating;
  }

  public Long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getRating() {
    return this.rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getAuthor() {
    return this.author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public void generateTimestamp() {
    long now = System.currentTimeMillis();
    Timestamp timestamp = new Timestamp(now);
    this.timestamp = timestamp;
  }

  public String formatTimestamp() {
    LocalDateTime localDateTime = this.timestamp.toLocalDateTime();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE d MMM y, HH:mm:ss");
    String formatted = localDateTime.format(formatter);
    return formatted;
  }

}
