package com.ghostappi.backend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostappi.backend.model.Category;
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
                .andExpect(content().string(containsString("The requested item is not registered")));
    }

    @Test
    public void createCouponTest() throws Exception {
        Coupon newCoupon = new Coupon();
        newCoupon.setIdCoupon(100);
        newCoupon.setCodeDiscount("NEWCODE10");
        newCoupon.setDescription("New coupon description");

        // Set initDate to current date
        newCoupon.setInitDate(new Date());

        // Set expirationDate to 24 hours in the future to meet validation requirements
        LocalDateTime expirationDateTime = LocalDateTime.now().plusDays(1);
        newCoupon.setExpirationDate(Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant()));

        newCoupon.setDiscountPercentage(10);
        Category category = new Category();
        category.setIdCategory(1);
        newCoupon.setIdCategory(category);

        mvc.perform(post("/coupons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCoupon)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Saved record")));
    }

    /*@Test
    public void deleteCouponTest() throws Exception {
        mvc.perform(delete("/coupons/9").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted record")));
    } */

    @Test
    public void updateCouponNotFoundTest() throws Exception {
        Coupon updatedCoupon = new Coupon();
        updatedCoupon.setCodeDiscount("UPDATEDCODE10");
        updatedCoupon.setDescription("Updated coupon description");

        // Set initDate to current date
        updatedCoupon.setInitDate(new Date());

        // Set expirationDate to 24 hours in the future
        LocalDateTime expirationDateTime = LocalDateTime.now().plusDays(1);
        updatedCoupon.setExpirationDate(Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant()));

        updatedCoupon.setDiscountPercentage(15);

        mvc.perform(put("/coupons/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCoupon)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The requested item is not registered")));
    }

    /* @Test
    public void deleteCouponNotFoundTest() throws Exception {
        mvc.perform(delete("/coupons/0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The requested item is not registered")));
    } */
}
