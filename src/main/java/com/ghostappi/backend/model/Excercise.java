package com.ghostappi.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "excercise")
public class Excercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExcercise")
    @JsonProperty("idExcercise")
    private Integer idExcercise;

    @Column(nullable = false, length = 100, name = "name")
    @JsonProperty("name")
    @NotBlank(message = "The name must no be null and containt at least one character")
    private String name;

    @Column(nullable = false ,length = 50, name ="difficulty")
    @JsonProperty("difficulty")
    @NotBlank(message = "The difficulty must no be null and containt at least one character")
    private String difficulty;

    public Excercise(){

    }

    public Excercise(Integer idExcercise, String name, String difficulty) {
        this.idExcercise = idExcercise;
        this.name = name;
        this.difficulty = difficulty;
    }

    public Integer getIdExcercise() {
        return idExcercise;
    }

    public void setIdExcercise(Integer idExcercise) {
        this.idExcercise = idExcercise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

}
