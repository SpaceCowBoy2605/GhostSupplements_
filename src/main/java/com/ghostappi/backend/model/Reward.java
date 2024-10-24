package com.ghostappi.backend.model;

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

    @NotBlank(message = "The description must no be null and containn at least one character")
    private int goalPoints;

    @NotBlank(message = "The description must no be null and containn at least one character")
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


}
   //@OneToOne
    //@JoinColumn(name = "idProduct")
    //private Product product;