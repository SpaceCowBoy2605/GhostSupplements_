package com.ghostappi.backend.dto;

public class TrainingRoutineRequestDTO {
    private Integer userId;
    private Integer exerciseId;
    private Byte reps;
    private Byte sets;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
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
