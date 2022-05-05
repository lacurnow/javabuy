package com.makersacademy.javabuy.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
@Entity
@Table(name = "MESSAGES")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
  private Timestamp timestamp;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "productid")
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sellerid")
  private User seller;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "enquirerid")
  private User enquirer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "senderid")
  private User sender;

  public Message() {
  }

  public Message(String content) {
    this.content = content;
  }

  public Long getId() {
    return this.id;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getTimestamp() {
    return this.timestamp;
  }

  public Product getProduct() {
    return this.product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public User getSeller() {
    return this.seller;
  }

  public void setSeller(User seller) {
    this.seller = seller;
  }

  public User getEnquirer() {
    return this.enquirer;
  }

  public void setEnquirer(User enquirer) {
    this.enquirer = enquirer;
  }

  public User getSender() {
    return this.sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
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
