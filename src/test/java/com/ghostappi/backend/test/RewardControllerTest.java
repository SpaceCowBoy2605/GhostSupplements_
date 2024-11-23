package com.ghostappi.backend.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostappi.backend.model.Reward;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws Exception {
        assertThat(mvc).isNotNull();
    }

    @Test
    public void getRewardByIdTest() throws Exception {
        mvc.perform(get("/rewards/1").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idReward", is(1))); // Verifica que el ID sea 1
    }

    @Test
    public void getCardByIdNotFoundTest() throws Exception {
        mvc.perform(get("/rewards/9999").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound()) // Verifica que se devuelva 404
            .andExpect(content().string(containsString("Reward not found")));
    }

    @Test
    public void updateRewardNotFoundTest() throws Exception {
        Reward updatedReward = new Reward();
        updatedReward.setGoalPoints(999);
        updatedReward.setDescription("Attempting to update a non-existent reward");

        mvc.perform(put("/rewards/9999")  // Supone que el ID del reward no existente es 9999
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedReward)))
            .andExpect(status().isNotFound())  // Devuelve 404 si no encuentra el reward
            .andExpect(content().string(containsString("information not found")));  // Verifica que el mensaje de error sea correcto
    }


    @Test
    public void deleteRewardNotFoundTest() throws Exception {
        mvc.perform(delete("/rewards/90").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound()) // Verifica que se devuelva 404
            .andExpect(content().string(containsString("Reward with ID not found."))); 
    }


}
