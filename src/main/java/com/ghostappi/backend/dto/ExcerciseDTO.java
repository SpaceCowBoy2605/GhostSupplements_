package com.ghostappi.backend.dto;

public class ExcerciseDTO {

    private Integer idExercise;
    private String name;
    private String difficulty;
    private Byte reps;
    private Byte sets;

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

    public Integer getIdExercise() {
        return idExercise;
    }

    public void setIdExercise(Integer idExercise) {
        this.idExercise = idExercise;
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
