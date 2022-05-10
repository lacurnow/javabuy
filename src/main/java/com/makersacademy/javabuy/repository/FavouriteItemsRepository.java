package com.makersacademy.javabuy.repository;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.model.User;
import com.makersacademy.javabuy.model.FavouriteItems;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FavouriteItemsRepository extends JpaRepository<FavouriteItems, Long>  {

    Iterable<FavouriteItems> findByuser_id(Long userId);

    
}
