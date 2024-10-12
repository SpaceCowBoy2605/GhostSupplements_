package com.ghostappi.backend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostappi.backend.controller.ShoppingCartController;
import com.ghostappi.backend.model.ShoppingCart;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
	private ShoppingCartController controller;

    @Autowired
    private ObjectMapper objectMapper;  

    @Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
    @Test
    public void getAllShoppingCartsTest() throws Exception {
        mvc.perform(get("/shoppingcart").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void getShoppingCartByIdTest() throws Exception {
        mvc.perform(get("/shoppingcart/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idShoppingCart", is(1)));
    }

    @Test
    public void getShopppingCartByIdNotFoundTest() throws Exception {
        mvc.perform(get("/shoppingcart/0").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Shopping cart not found")));
    }

    @Test
    public void createShoppingCartTest() throws Exception {
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setIdShoppingCart(100);  // Ejemplo de valores, puedes ajustarlo según tus restricciones
        newShoppingCart.setQuantity(100);
        newShoppingCart.setTotal(1000);
        newShoppingCart.setIdCustomer(10);

        mvc.perform(post("/shoppingcart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newShoppingCart)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total", is(1000)));
    }

    @Test
    public void deleteShopppingCartTest() throws Exception {
        mvc.perform(delete("/shoppingcart/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Shopping Cart deleted successfully")));
    }
}

