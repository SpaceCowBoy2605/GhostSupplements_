package com.ghostappi.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ghostappi.backend.model.ShoppingCart;
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, Integer>{
   
    ShoppingCart findByIdShoppingCart(Integer idShoppingCart);
    void deleteByIdShoppingCart(Integer idShoppingCart);
    
}
