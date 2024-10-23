package com.ghostappi.backend.repository;

import org.springframework.data.jpa.repository. JpaRepository;
import org.springframework.stereotype.Repository;

import com.ghostappi.backend.model.TrainingRoutine;
import com.ghostappi.backend.model.TrainingRoutinePK;

@Repository
public interface TrainingRoutineRepository  extends JpaRepository<TrainingRoutine, TrainingRoutinePK> {

}
