package com.makersacademy.javabuy.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import static java.lang.Boolean.TRUE;

import java.math.BigDecimal;

import static java.lang.Boolean.FALSE;


import java.util.Set;

import java.util.List;


import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "favouriteProducts")
    Set<User> favourites;
    private String name;
    private BigDecimal price;
    private String description;
    private String photo;
    private Boolean sold;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seller_id")
    private User user;

    @OneToMany(mappedBy="product", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }
    
    public Product() {}

    public Product(String name, BigDecimal price, String description, String photo, Boolean sold) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.photo = photo;
        this.sold = sold;
    }
    
    public Long getId() { return this.id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return this.price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public String getPhoto() { return this.photo; }
    public void setPhoto(String photo) { this.photo = photo; }


    public void setAsSold() { this.sold = TRUE; }
    public Boolean getSold() { return this.sold; }


    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return id.equals(this.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
