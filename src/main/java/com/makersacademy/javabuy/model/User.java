package com.makersacademy.javabuy.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GenerationType;

import lombok.Data;

import static java.lang.Boolean.TRUE;

import java.util.List;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean enabled;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> userReviews;

    public List<Review> getUserReviews() {
        return userReviews;
    }

    public User() {
        this.enabled = TRUE;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = TRUE;
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public Long getId() { return this.id; }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}
