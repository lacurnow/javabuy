package com.makersacademy.javabuy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
@Table(name = "FAVOURITE_ITEMS")
public class FavouriteItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    boolean is_favourite;

    public FavouriteItems() {}


    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }
  
    public Product getProduct() { return this.product; }
    public void setProduct(Product product) { this.product = product; }

    public void setFavourite() {is_favourite = true;}

    }
