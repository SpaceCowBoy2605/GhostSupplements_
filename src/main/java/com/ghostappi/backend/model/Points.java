package com.ghostappi.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Points {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    
    private int idPoints;

     @NotBlank(message = "The description must no be null and containn at least one character")
    private int accumulatedPoints;
   

    public int getIdPoints() {
        return idPoints;
    }
    public void setIdPoints(int idPoints) {
        this.idPoints = idPoints;
    }
    public int getAccumulatedPoints() {
        return accumulatedPoints;
    }
    public void setAccumulatedPoints(int accumulatedPoints) {
        this.accumulatedPoints = accumulatedPoints;
    }

}
 //@OneToOne  
    //    @JoinColumn(name = "idUser")
    //private User user;