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
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idIngredient")
    @JsonProperty("idIngredient")
    private Integer idIngredient;
    @NotBlank(message = "The name must no be null and containn at least one character")
    @Size(min = 1, max = 100, message = "The name must be almost 1 character and 100 characters at most")
    @JsonProperty("name")
    private String name;

    public Ingredient() {
    }

    public Ingredient(Integer idIngredient, String name) {
        this.idIngredient = idIngredient;
        this.name = name;
    }

    public Integer getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Integer idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
