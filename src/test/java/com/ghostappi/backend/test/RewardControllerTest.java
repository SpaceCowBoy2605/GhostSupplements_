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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostappi.backend.controller.RewardController;
import com.ghostappi.backend.model.Reward;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RewardController rewardController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws Exception {
        assertThat(mvc).isNotNull();
    }

     @Test
    public void getAllRewardsTest() throws Exception {
        mvc.perform(get("/Reward").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(greaterThan(0)))); // Verifica que haya al menos una tarjeta
    }

    @Test
    public void getRewardByIdTest() throws Exception {
        mvc.perform(get("/Reward/1").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idReward", is(1))); // Verifica que el ID sea 1
    }

    @Test
    public void getCardByIdNotFoundTest() throws Exception {
        mvc.perform(get("/Reward/9999").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound()) // Verifica que se devuelva 404
            .andExpect(content().string(containsString("Reward not found")));
    }

    @Test
    public void createRewardTest() throws Exception {
        Reward newReward = new Reward();
        newReward.setGoalPoints(100);
        newReward.setDescription("New reward for achieving 100 points");

        mvc.perform(post("/Reward")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newReward)))
            .andExpect(status().isOk())  // Verifica que el estado sea 200 (OK)
            .andExpect(content().string(containsString("rEWARD add correctly")));  // Verifica que el mensaje de éxito sea correcto
    }

    @Test
    public void updateRewardTest() throws Exception {
        Reward updatedReward = new Reward();
        updatedReward.setGoalPoints(150);
        updatedReward.setDescription("Updated reward for achieving 150 points");

        mvc.perform(put("/Reward/1")  // Supone que el ID del reward a actualizar es 2
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedReward)))
            .andExpect(status().isOk())  // Verifica que el estado sea 200 (OK)
            .andExpect(content().string(containsString("Updated record")));  // Verifica que el mensaje de éxito sea correcto
    }

    @Test
    public void updateRewardNotFoundTest() throws Exception {
        Reward updatedReward = new Reward();
        updatedReward.setGoalPoints(999);
        updatedReward.setDescription("Attempting to update a non-existent reward");

        mvc.perform(put("/Reward/9999")  // Supone que el ID del reward no existente es 9999
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedReward)))
            .andExpect(status().isNotFound())  // Devuelve 404 si no encuentra el reward
            .andExpect(content().string(containsString("information not found")));  // Verifica que el mensaje de error sea correcto
    }

    @Test
    public void deleteRewardTest() throws Exception {
        mvc.perform(delete("/Reward/2").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent()) 
            .andExpect(content().string(containsString("Reward deleted successfully"))); 
    }

    @Test
    public void deleteRewardNotFoundTest() throws Exception {
        mvc.perform(delete("/Reward/90").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound()) // Verifica que se devuelva 404
            .andExpect(content().string(containsString("Reward with ID not found."))); 
    }


}
