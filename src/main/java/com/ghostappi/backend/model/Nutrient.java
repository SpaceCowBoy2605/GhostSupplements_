package com.ghostappi.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "nutrient")
public class Nutrient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNutrient")
    @JsonProperty("idNutrient")
    private Integer idNutrient;
    @NotBlank(message = "The name must no be null and containn at least one character")
    @Size(min = 1, max = 100, message = "The name must be almost 1 character and 100 characters at most")
    @JsonProperty("name")
    private String name;

    public Nutrient() {
    }

    public Nutrient(Integer idNutrient, String name) {
        this.idNutrient = idNutrient;
        this.name = name;
    }

    public Integer getIdNutrient() {
        return idNutrient;
    }

    public void setIdNutrient(Integer idNutrient) {
        this.idNutrient = idNutrient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
