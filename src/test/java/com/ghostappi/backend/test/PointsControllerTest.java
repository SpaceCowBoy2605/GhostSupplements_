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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ghostappi.backend.controller.PointsController;
import com.ghostappi.backend.model.Points;


@SpringBootTest
@AutoConfigureMockMvc
public class PointsControllerTest {

     @Autowired
    private MockMvc mvc;

    // @Autowired
    //  private PointsController pointsController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws Exception {
        assertThat(mvc).isNotNull();
    }

      @Test
    public void getPointsByIdTest() throws Exception {
        mvc.perform(get("/points/2").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idPoints", is(2))); // Verifica que el ID sea 1
    }

    @Test
    public void getPointsByIdNotFoundTest() throws Exception {
        mvc.perform(get("/points/9999").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound()) // Verifica que se devuelva 404
            .andExpect(content().string(containsString("Points not found")));
    }

        @Test
    public void createPointsTest() throws Exception {
        Points newPoints = new Points();
        newPoints.setAccumulatedPoints(500); 
        newPoints.setUserId(1); 

        mvc.perform(post("/points")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPoints)))
            .andExpect(status().isOk())  // Verifica que el estado sea 200 OK
            .andExpect(content().string(containsString("Points added correctly")));  // Verifica que el mensaje de respuesta contenga lo esperado
    }

        @Test
    public void updatePointsNotFoundTest() throws Exception {
        Points updatedPoints = new Points();
        updatedPoints.setAccumulatedPoints(9999);  

        mvc.perform(put("/points/9999")  
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPoints)))
            .andExpect(status().isBadRequest()) 
            .andExpect(content().string(containsString("Updated no record, verify data")));  
    }

        @Test
    public void deletePointsTest() throws Exception {
        mvc.perform(delete("/points/3").accept(MediaType.APPLICATION_JSON))  
            .andExpect(status().isOk())  
            .andExpect(content().string(containsString("Points deleted successfully")));  
    }

        @Test
    public void deletePointsNotFoundTest() throws Exception {
        mvc.perform(delete("/points/90").accept(MediaType.APPLICATION_JSON)) 
            .andExpect(status().isNotFound())  
            .andExpect(content().string(containsString("The requested resource was not registered.")));  
    }



}
