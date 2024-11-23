package com.ghostappi.backend.test;


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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CardControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    void contextLoads() throws Exception {
        assertThat(mvc).isNotNull();
    }


    @Test
    public void getAllCardsTest() throws Exception {
        mvc.perform(get("/cards").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(greaterThan(0)))); // Verifica que haya al menos una tarjeta
    }

    @Test
    public void getCardByIdTest() throws Exception {
        mvc.perform(get("/cards/1").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idCard", is(1))); // Verifica que el ID sea 1
    }

    // Test para obtener una tarjeta por ID no encontrado
    @Test
    public void getCardByIdNotFoundTest() throws Exception {
        mvc.perform(get("/cards/9999").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound()) // Verifica que se devuelva 404
            .andExpect(content().string(containsString("Card not found")));
    }

    // // Test para registrar una nueva tarjeta error
    // @Test
    // public void createCardTest() throws Exception {
    //     CardDTO newCard = new CardDTO();
    //     newCard.setNumber(1234567);
    //     newCard.setType("Credit");
    //     newCard.setExpirationDate(new Date(System.currentTimeMillis() + 86400000)); 
    //     newCard.setCvv(123);
    //     newCard.setExpired(false);
    //     newCard.setWalletId(1);

    //     mvc.perform(post("/Card")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(objectMapper.writeValueAsString(newCard)))
    //         .andExpect(status().isOk())
    //         .andExpect(content().string(containsString("Card saved successfully")));
    // }

    // Test para actualizar una tarjeta existente
    // @Test
    // public void updateCardTest() throws Exception {
    //     Card updatedCard = new Card();
    //     updatedCard.setNumber(87654321);
    //     updatedCard.setType("Debit");
    //     updatedCard.setExpirationDate(new Date(System.currentTimeMillis() + 86400000)); // Fecha futura
    //     updatedCard.setCvv(456);
    //     updatedCard.setExpired(false);

    //     mvc.perform(put("/cards/2")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(objectMapper.writeValueAsString(updatedCard)))
    //         .andExpect(status().isOk())
    //         .andExpect(content().string(containsString("Updated record")));
    // }

    // // Test para actualizar una tarjeta no encontrada
    // @Test
    // public void updateCardNotFoundTest() throws Exception {
    //     Card updatedCard = new Card();
    //     updatedCard.setNumber(99999999);
    //     updatedCard.setType("Debit");
    //     updatedCard.setExpirationDate(new Date(System.currentTimeMillis() + 86400000)); // Fecha futura
    //     updatedCard.setCvv(789);
    //     updatedCard.setExpired(true);
    //     updatedCard.setIdCard(1);

    //     mvc.perform(put("/cards/9999")
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(objectMapper.writeValueAsString(updatedCard)))
    //         .andExpect(status().isNotFound()) // Devuelve 400 si no encuentra el registro
    //         .andExpect(content().string(containsString("Card not found")));
    // }

    @Test
    public void deleteCardTest() throws Exception {
        mvc.perform(delete("/cards/3").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()) // Verifica que el estado sea 204 al eliminar exitosamente
            .andExpect(content().string(containsString("Card deleted successfully")));
    }

    // Test para eliminar una tarjeta no encontrada
    @Test
    public void deleteCardNotFoundTest() throws Exception {
        mvc.perform(delete("/cards/90").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound()) 
            .andExpect(content().string(containsString("Card with ID not found.")));
    }

}
