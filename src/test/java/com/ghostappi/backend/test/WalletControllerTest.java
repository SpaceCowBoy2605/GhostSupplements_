package com.ghostappi.backend.test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostappi.backend.controller.WalletController;
import com.ghostappi.backend.model.Wallet;

@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest {

        @Autowired
    private MockMvc mvc;

    @Autowired
    private WalletController walletController;

      @Autowired
    private ObjectMapper objectMapper;

        @Test
    void contextLoads() throws Exception {
        assertThat(mvc).isNotNull();
    }

       @Test
    public void getAllWalletsTest() throws Exception {
        mvc.perform(get("/Wallet").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(greaterThan(0)))); 
    }

    @Test
    public void getCardByIdTest() throws Exception {
        mvc.perform(get("/Wallet/1").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idWallet", is(1))); 
    }

    // Test para obtener una tarjeta por ID no encontrado
    @Test
    public void getWalletByIdNotFoundTest() throws Exception {
        mvc.perform(get("/Wallet/9999").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound()) 
            .andExpect(content().string(containsString("The requested resource was not registered.")));
    }

    @Test
    public void createWalletTest() throws Exception {
        Wallet newWallet = new Wallet();
        newWallet.setUserId(1); 
        mvc.perform(post("/Wallet") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newWallet)))
            .andExpect(status().isOk()) 
            .andExpect(content().string(containsString("Wallet added correctly")));
    }

        @Test
    public void updateWalletTest() throws Exception {
        Wallet updatedWallet = new Wallet();
        updatedWallet.setUserId(1); 
        mvc.perform(put("/Wallet/1") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedWallet)))
            .andExpect(status().isOk()) 
            .andExpect(content().string(containsString("Updated record"))); 
    }

    @Test
    public void updateWalletNotFoundTest() throws Exception {
        Wallet updatedWallet = new Wallet();
        updatedWallet.setUserId(1); 
        mvc.perform(put("/Wallet/9999") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedWallet)))
            .andExpect(status().isNotFound()) 
            .andExpect(content().string(containsString("Updated record")));
    }

    @Test
    public void deleteWalletTest() throws Exception {
        mvc.perform(delete("/Wallet/3").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()) 
            .andExpect(content().string(containsString("Wallet deleted successfully")));
    }

    @Test
    public void deleteWalletNotFoundTest() throws Exception {
        mvc.perform(delete("/Wallet/90").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound()) 
            .andExpect(content().string(containsString("Wallet with ID not found."))); 
    }


}
