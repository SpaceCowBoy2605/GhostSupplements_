package com.ghostappi.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Reward {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idReward;

    @Column(name = "idProduct", nullable = false)
    private Integer productId;

    //@NotBlank(message = "The description must no be null and containn at least one character")
    private int goalPoints;

    //@NotBlank(message = "The description must no be null and containn at least one character")
    private String description;


 
    public Reward() {
    }

    public int getIdReward() {
        return idReward;
    }

    public void setIdReward(int idReward) {
        this.idReward = idReward;
    }

    public int getGoalPoints() {
        return goalPoints;
    }

    public void setGoalPoints(int goalPoints) {
        this.goalPoints = goalPoints;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("idProduct")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Reward [idReward=" + idReward + ", goalPoints=" + goalPoints + ", description=" + description
                + ", productId=" + productId + "]";
    }

}
    