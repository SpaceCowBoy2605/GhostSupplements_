package com.ghostappi.backend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        mvc.perform(get("/shoppingcart")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void getShoppingCartByIdTest() throws Exception {
        mvc.perform(get("/shoppingcart/3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idShoppingCart", is(3)));
    }

    @Test
    public void getShopppingCartByIdNotFoundTest() throws Exception {
        mvc.perform(get("/shoppingcart/0").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The requested item is not registered")));
    }
    

    @Test
    public void createShoppingCartTest() throws Exception {
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setIdShoppingCart(100);
        newShoppingCart.setQuantity(100);
        newShoppingCart.setTotal(1000);
        newShoppingCart.setIdCustomer(10);

        mvc.perform(post("/shoppingcart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newShoppingCart)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Saved record")));
    }

    @Test
    public void updateShoppingCartTest() throws Exception {
        ShoppingCart updatedCart = new ShoppingCart();
        updatedCart.setQuantity(50);
        updatedCart.setTotal(500);

        mvc.perform(put("/shoppingcart/8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCart)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Updated record")));
    }

    @Test
    public void deleteShoppingCartTest() throws Exception {
        mvc.perform(delete("/shoppingcart/100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted record")));
    }
    @Test
public void updateShoppingCartNotFoundTest() throws Exception {
    ShoppingCart updatedShoppingCart = new ShoppingCart();
    updatedShoppingCart.setQuantity(50);
    updatedShoppingCart.setTotal(500);
    updatedShoppingCart.setIdCustomer(10);

    mvc.perform(put("/shoppingcart/0")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedShoppingCart)))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(content().string(containsString("The requested item is not registered")));
}

@Test
public void deleteShoppingCartNotFoundTest() throws Exception {
    mvc.perform(delete("/shoppingcart/0").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().string(containsString("The requested item is not registered")));
}
}
