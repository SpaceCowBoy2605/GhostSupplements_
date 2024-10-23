package com.ghostappi.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "trainigroutine")
@IdClass(TrainingRoutinePK.class)
//@JsonIgnoreProperties({"user"})
public class TrainingRoutine {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
    
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idExcercise", referencedColumnName = "idExcercise")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Excercise excercise;

    @Column(name = "reps")
    private Byte reps;

    @Column(name = "sets")
    private Byte sets;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Excercise getExcercise() {
        return excercise;
    }

    public void setExcercise(Excercise excercise) {
        this.excercise = excercise;
    }

    public Byte getReps() {
        return reps;
    }

    public void setReps(Byte reps) {
        this.reps = reps;
    }

    public Byte getSets() {
        return sets;
    }

    public void setSets(Byte sets) {
        this.sets = sets;
    }

    

}
