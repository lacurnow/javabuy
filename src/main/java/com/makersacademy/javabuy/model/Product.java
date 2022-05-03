package com.makersacademy.javabuy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import static java.lang.Boolean.FALSE;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String photo;
    private Boolean sold;

    public Product() {}

    public Product(String name, Double price, String description, String photo) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.photo = photo;
        this.sold = FALSE;

    }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getPrice() { return this.price; }
    public void setPrice(String price) { this.price = price; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public String getPhoto() { return this.photo; }
    public void setPhoto(String photo) { this.photo = photo; }


}
