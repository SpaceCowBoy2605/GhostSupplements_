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
import com.ghostappi.backend.controller.CouponController;
import com.ghostappi.backend.model.Coupon;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class CouponControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
	private CouponController controller;

    @Autowired
    private ObjectMapper objectMapper;  

    @Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
    @Test
    public void getAllCouponsTest() throws Exception {
        mvc.perform(get("/coupons").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void getCouponByIdTest() throws Exception {
        mvc.perform(get("/coupons/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCoupon", is(1)));
    }

    @Test
    public void getCouponByIdNotFoundTest() throws Exception {
        mvc.perform(get("/coupons/0").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Coupon not found")));
    }

    @Test
    public void createCouponTest() throws Exception {
        Coupon newCoupon = new Coupon();
        newCoupon.setIdCoupon(100);  // Ejemplo de valores, puedes ajustarlo según tus restricciones
        newCoupon.setCodeDiscount("NEWCODE10");
        newCoupon.setDescription("New coupon description");
        newCoupon.setDiscountPercentage(10);

        mvc.perform(post("/coupons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCoupon)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.codeDiscount", is("NEWCODE10")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("New coupon description")));
    }

    @Test
    public void deleteCouponTest() throws Exception {
        mvc.perform(delete("/coupons/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Coupon deleted successfully")));
    }
}

