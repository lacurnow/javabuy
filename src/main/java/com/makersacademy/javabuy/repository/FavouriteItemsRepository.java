package com.makersacademy.javabuy.repository;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.model.User;
import com.makersacademy.javabuy.model.FavouriteItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavouriteItemsRepository extends JpaRepository<FavouriteItems, Long>  {
    
}
