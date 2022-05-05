package com.makersacademy.javabuy.model;

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
@Table(name = "REVIEWS")

public class Review {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private Integer rating;
  private String photo;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="user_id")
  private User user;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="product_id")
  private Product product;

  public Review() {}

  public Review(String title, String content, Integer rating, String photo) {
    this.title = title;
    this.content = content;
    this.rating = rating;
    this.photo = photo;
  }  
  
  public Long getId() { return this.id; }

  public String getTitle() { return this.title; }
  public void setTitle(String title) { this.title = title; }

  public String getContent() { return this.content; }
  public void setContent(String content) { this.content = content; }

  public Integer getRating() { return this.rating; }
  public void setRating(Integer rating) { this.rating = rating; }

  public String getPhoto() { return this.photo; }
  public void setPhoto(String photo) { this.photo = photo; }

  public User getUser() { return this.user; }
  public void setUser(User user) { this.user = user; }

  public Product getProduct() { return this.product; }
  public void setProduct(Product product) { this.product = product; }
}
