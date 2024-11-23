package com.ghostappi.backend.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.containsString;

import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostappi.backend.model.Excercise;


@SpringBootTest
@AutoConfigureMockMvc
public class ExcerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllExcercisesWithPagination() throws Exception {
        mockMvc.perform(get("/excercises")
                .param("page", "0")
                .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(5)));
    }

    @Test
    public void testGetAllExcercisesWithDifferentPageSize() throws Exception {
        mockMvc.perform(get("/excercises")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(10))); // Assuming there are at least 10 exercises in the database
    }

    @Test
    public void testGetAllExcercisesWithInvalidSize() throws Exception {
        mockMvc.perform(get("/excercises")
                .param("page", "0")
                .param("size", "-1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateExcercise() throws Exception {
        Excercise newExcercise = new Excercise();
        newExcercise.setName("Free squat");
        newExcercise.setDifficulty("Medium");

        mockMvc.perform(post("/excercises")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(newExcercise)))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Excercise saved")));
    }
}