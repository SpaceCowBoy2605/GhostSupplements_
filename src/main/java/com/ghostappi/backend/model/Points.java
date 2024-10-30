package com.ghostappi.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Points {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    private int idPoints;

    private int accumulatedPoints;
   
    @Column(name = "idUser", nullable = false)
    private Integer userId;

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

    @JsonProperty("idUser") 
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "Points [idPoints=" + idPoints + ", accumulatedPoints=" + accumulatedPoints + ", userId=" + userId + "]";
    }

}