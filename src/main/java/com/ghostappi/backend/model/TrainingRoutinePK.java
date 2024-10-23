package com.ghostappi.backend.model;

import java.io.Serializable;
import java.util.Objects;


public class TrainingRoutinePK implements Serializable {

    private User user;
    private Excercise excercise;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainingRoutinePK trainingRoutinePK)) return false;
        return user.getIdUser() == trainingRoutinePK.user.getIdUser() && Objects.equals(excercise, trainingRoutinePK.excercise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getIdUser(), excercise);
    }

    public User getUser() {
        return user;
    }

    // public void setUser(User user) {
    //     this.user = user;
    // }

    public Excercise getExcercise() {
        return excercise;
    }

    // public void setExcercise(Excercise excercise) {
    //     this.excercise = excercise;
    // }
    

}
