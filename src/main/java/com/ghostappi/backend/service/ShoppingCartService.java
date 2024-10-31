package com.ghostappi.backend.service;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.ShoppingCart;
import com.ghostappi.backend.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ShoppingCart> getAll() {
        return mongoTemplate.findAll(ShoppingCart.class);
    }

    public ShoppingCart getById(Integer idShoppingCart) {
        Query query = new Query(Criteria.where("idShoppingCart").is(idShoppingCart));
        ShoppingCart cart = mongoTemplate.findOne(query, ShoppingCart.class);
        if (cart == null) {
            throw new NoSuchElementException("Shopping cart with id " + idShoppingCart + " not found");
        }
        return cart;
    }

    public void add(ShoppingCart resource) {
        mongoTemplate.save(resource);
    }

    public void update(ShoppingCart resource, Integer idShoppingCart) {
        Query query = new Query(Criteria.where("idShoppingCart").is(idShoppingCart));
        ShoppingCart existingCart = mongoTemplate.findOne(query, ShoppingCart.class);
        if (existingCart == null) {
            throw new NoSuchElementException("Shopping cart with id " + idShoppingCart + " not found");
        }
        Update update = new Update()
                .set("quantity", resource.getQuantity())
                .set("total", resource.getTotal());
        mongoTemplate.updateFirst(query, update, ShoppingCart.class);
    }

    public boolean delete(Integer idShoppingCart) {
        Query query = new Query(Criteria.where("idShoppingCart").is(idShoppingCart));
        ShoppingCart cart = mongoTemplate.findOne(query, ShoppingCart.class);
        if (cart == null) {
            throw new NoSuchElementException("Shopping cart with id " + idShoppingCart + " not found");
        }
        mongoTemplate.remove(query, ShoppingCart.class);
        return true;
    }
}
