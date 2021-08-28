package com.eshopping.cartservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eshopping.cartservice.model.Cart;
@Repository
public interface CartRepository extends MongoRepository<Cart, String>{
	public Optional<Cart> findCartByUserId(String UserId);

}
