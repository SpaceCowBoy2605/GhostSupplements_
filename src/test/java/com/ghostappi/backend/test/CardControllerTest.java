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
import com.ghostappi.backend.controller.CardController;
import com.ghostappi.backend.model.Card;

@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CardController cardController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws Exception {
        assertThat(mvc).isNotNull();
    }


    @Test
    public void getAllCardsTest() throws Exception {
        mvc.perform(get("/Card").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(greaterThan(0)))); // Verifica que haya al menos una tarjeta
    }

    @Test
    public void getCardByIdTest() throws Exception {
        mvc.perform(get("/Card/1").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idCard", is(1))); // Verifica que el ID sea 1
    }

    // Test para obtener una tarjeta por ID no encontrado
    @Test
    public void getCardByIdNotFoundTest() throws Exception {
        mvc.perform(get("/Card/9999").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound()) // Verifica que se devuelva 404
            .andExpect(content().string(containsString("Card not found")));
    }

    // Test para registrar una nueva tarjeta error
    @Test
    public void createCardTest() throws Exception {
        Card newCard = new Card();
        newCard.setNumber(12345678);
        newCard.setType("Credit");
        newCard.setExpirationDate(new Date(System.currentTimeMillis() + 86400000)); // Fecha futura
        newCard.setCvv(123);
        newCard.setExpired(false);

        mvc.perform(post("/Card")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCard)))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Card add correctly")));
    }

    // Test para actualizar una tarjeta existente
    @Test
    public void updateCardTest() throws Exception {
        Card updatedCard = new Card();
        updatedCard.setNumber(87654321);
        updatedCard.setType("Debit");
        updatedCard.setExpirationDate(new Date(System.currentTimeMillis() + 86400000)); // Fecha futura
        updatedCard.setCvv(456);
        updatedCard.setExpired(false);

        mvc.perform(put("/Card/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCard)))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Updated record")));
    }

    // Test para actualizar una tarjeta no encontrada
    @Test
    public void updateCardNotFoundTest() throws Exception {
        Card updatedCard = new Card();
        updatedCard.setNumber(99999999);
        updatedCard.setType("Debit");
        updatedCard.setExpirationDate(new Date(System.currentTimeMillis() + 86400000)); // Fecha futura
        updatedCard.setCvv(789);
        updatedCard.setExpired(true);

        mvc.perform(put("/Card/9999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCard)))
            .andExpect(status().isBadRequest()) // Devuelve 400 si no encuentra el registro
            .andExpect(content().string(containsString("Updated no record, verify your information")));
    }

    @Test
    public void deleteCardTest() throws Exception {
        mvc.perform(delete("/Card/3").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()) // Verifica que el estado sea 204 al eliminar exitosamente
            .andExpect(content().string(containsString("Card deleted successfully")));
    }

    // Test para eliminar una tarjeta no encontrada
    @Test
    public void deleteCardNotFoundTest() throws Exception {
        mvc.perform(delete("/Card/90").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound()) 
            .andExpect(content().string(containsString("Card with ID not found.")));
    }

}