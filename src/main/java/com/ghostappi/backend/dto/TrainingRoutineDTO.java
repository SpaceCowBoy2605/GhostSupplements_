package com.ghostappi.backend.dto;

import java.util.List;

public class TrainingRoutineDTO {
private Integer userId;
    private List<ExcerciseDTO> exercises;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ExcerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExcerciseDTO> exercises) {
        this.exercises = exercises;
    }
}
