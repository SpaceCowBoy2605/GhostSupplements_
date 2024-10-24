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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghostappi.backend.controller.PointsController;
import com.ghostappi.backend.model.Points;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PointsControllerTest {

     @Autowired
    private MockMvc mvc;

    @Autowired
     private PointsController pointsController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws Exception {
        assertThat(mvc).isNotNull();
    }

        @Test
    public void getAllPointsTest() throws Exception {
        mvc.perform(get("/Points").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(greaterThan(0)))); // Verifica que haya al menos una tarjeta
    }

      @Test
    public void getPointsByIdTest() throws Exception {
        mvc.perform(get("/Points/1").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idPoints", is(1))); // Verifica que el ID sea 1
    }

    @Test
    public void getPointsByIdNotFoundTest() throws Exception {
        mvc.perform(get("/Points/9999").accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound()) // Verifica que se devuelva 404
            .andExpect(content().string(containsString("Points not found")));
    }

        @Test
    public void createPointsTest() throws Exception {
        Points newPoints = new Points();
        newPoints.setAccumulatedPoints(500);  // Establece los puntos acumulados

        mvc.perform(post("/Points")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPoints)))
            .andExpect(status().isOk())  // Verifica que el estado sea 200 OK
            .andExpect(content().string(containsString("Points added correctly")));  // Verifica que el mensaje de respuesta contenga lo esperado
    }

        @Test
    public void updatePointsTest() throws Exception {
        Points updatedPoints = new Points();
        updatedPoints.setAccumulatedPoints(1000);  // Actualiza los puntos acumulados

        mvc.perform(put("/Points/1")  // Asumiendo que el ID del recurso a actualizar es 1
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPoints)))
            .andExpect(status().isOk())  // Verifica que el estado sea 200 OK
            .andExpect(content().string(containsString("Updated record")));  // Verifica que el mensaje de respuesta contenga lo esperado
    }

        @Test
    public void updatePointsNotFoundTest() throws Exception {
        Points updatedPoints = new Points();
        updatedPoints.setAccumulatedPoints(9999);  // Nuevos puntos acumulados para la actualización

        mvc.perform(put("/Points/9999")  // Intento de actualizar un recurso inexistente con ID 9999
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPoints)))
            .andExpect(status().isBadRequest())  // Verifica que devuelva un 400 Bad Request si no encuentra el registro
            .andExpect(content().string(containsString("Updated no record, verify data")));  // Verifica que el mensaje de error sea el esperado
    }

        @Test
    public void deletePointsTest() throws Exception {
        mvc.perform(delete("/Points/3").accept(MediaType.APPLICATION_JSON))  // Elimina el recurso con ID 3
            .andExpect(status().isOk())  // Verifica que el estado sea 200 o 204 al eliminar exitosamente
            .andExpect(content().string(containsString("Points deleted successfully")));  // Verifica el mensaje de éxito
    }

        @Test
    public void deletePointsNotFoundTest() throws Exception {
        mvc.perform(delete("/Points/90").accept(MediaType.APPLICATION_JSON))  // Intenta eliminar el recurso con ID 90
            .andExpect(status().isNotFound())  // Verifica que la respuesta tenga un estado 404 Not Found
            .andExpect(content().string(containsString("The requested resource was not registered.")));  // Verifica que el mensaje de error sea adecuado
    }



}
