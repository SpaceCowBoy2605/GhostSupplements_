package com.ghostappi.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.ghostappi.backend.model.TrainingRoutine;
import com.ghostappi.backend.repository.TrainingRoutineRepository;
import com.ghostappi.backend.dto.TrainingRoutineDTO;
import com.ghostappi.backend.dto.ExcerciseDTO;

@Service
@Transactional
public class TrainingRoutineService {
    @Autowired
    private TrainingRoutineRepository repository;

    public List<TrainingRoutineDTO> getAll() {
        List<TrainingRoutine> trainingRoutines = repository.findAll();
        return trainingRoutines.stream()
            .collect(Collectors.groupingBy(routine -> routine.getUser().getIdUser()))
            .entrySet().stream()
            .map(entry -> {
                TrainingRoutineDTO dto = new TrainingRoutineDTO();
                dto.setUserId(entry.getKey());
                List<ExcerciseDTO> exercises = entry.getValue().stream()
                    .map(routine -> {
                        ExcerciseDTO exerciseDTO = new ExcerciseDTO();
                        exerciseDTO.setIdExercise(routine.getExcercise().getIdExcercise());
                        exerciseDTO.setName(routine.getExcercise().getName());
                        exerciseDTO.setDifficulty(routine.getExcercise().getDifficulty());
                        exerciseDTO.setReps(routine.getReps());
                        exerciseDTO.setSets(routine.getSets());
                        return exerciseDTO;
                    })
                    .collect(Collectors.toList());
                dto.setExercises(exercises);
                return dto;
            })
            .collect(Collectors.toList());
    }

    public void save(TrainingRoutine trainingRoutine) {
        repository.save(trainingRoutine);
    }
}
