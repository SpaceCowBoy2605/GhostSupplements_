package com.ghostappi.backend.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.ShoppingCart;
import com.ghostappi.backend.repository.ShoppingCartRepository;

@Service

public class ShoppingCartService {
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	public void saveShoppingCart(ShoppingCart shoppingCart) {
		shoppingCartRepository.save(shoppingCart);
	}

	public ShoppingCart getShoppingCartByCustomId(Integer idShoppingCart) {
		return shoppingCartRepository.findByIdShoppingCart(idShoppingCart);
	}

	public List<ShoppingCart> getAllShoppingCarts() {
		return shoppingCartRepository.findAll();
	}

	public ShoppingCart updateShoppingCart(Integer idhShoppingCart, ShoppingCart shoppingCart) {
		ShoppingCart existingCart = shoppingCartRepository.findByIdShoppingCart(idhShoppingCart);
		if (existingCart != null) {
			existingCart.setIdShoppingCart(idhShoppingCart);
			existingCart.setQuantity(shoppingCart.getQuantity());
			existingCart.setTotal(shoppingCart.getTotal());
			existingCart.setIdCustomer(shoppingCart.getIdCustomer());
			return shoppingCartRepository.save(existingCart);
		}
		return null;
	}

	public void deleteShoppingCartByCustomId(Integer idShoppingCart) {
		shoppingCartRepository.deleteByIdShoppingCart(idShoppingCart);
	}

}
