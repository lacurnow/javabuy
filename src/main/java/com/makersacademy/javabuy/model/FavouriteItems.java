package com.makersacademy.javabuy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "FAVOURITE_ITEMS")
public class FavouriteItems {

    @EmbeddedId
    FavouriteitemsKey id ;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    boolean is_favourite;

    @Embeddable
    class FavouriteitemsKey implements Serializable {
    
        @Column(name = "user_id")
        Long userId;
    
        @Column(name = "product_id")
        Long productId;
    
        
    }
    }
