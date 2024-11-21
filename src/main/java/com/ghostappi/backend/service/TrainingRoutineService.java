package com.ghostappi.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import com.ghostappi.backend.model.Excercise;
import com.ghostappi.backend.model.TrainingRoutine;
import com.ghostappi.backend.repository.ExcerciseRepository;
import com.ghostappi.backend.repository.TrainingRoutineRepository;
import com.ghostappi.backend.repository.UserRepository;
import com.ghostappi.backend.dto.TrainingRoutineDTO;
import com.ghostappi.backend.dto.TrainingRoutineRequestDTO;
import com.ghostappi.backend.dto.ExcerciseDTO;

@Service
@Transactional
public class TrainingRoutineService {
    @Autowired
    private TrainingRoutineRepository repository;

        @Autowired
    private TrainingRoutineRepository trainingRoutineRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExcerciseRepository excerciseRepository;

    
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

    public void save(TrainingRoutineRequestDTO requestDTO) {
        com.ghostappi.backend.model.User user = userRepository.findById(requestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Excercise excercise = excerciseRepository.findById(requestDTO.getExerciseId()).orElseThrow(() -> new RuntimeException("Exercise not found"));

        TrainingRoutine trainingRoutine = new TrainingRoutine();
        trainingRoutine.setUser(user);
        trainingRoutine.setExcercise(excercise);
        trainingRoutine.setReps(requestDTO.getReps());
        trainingRoutine.setSets(requestDTO.getSets());

        trainingRoutineRepository.save(trainingRoutine);
    }

    public List<TrainingRoutineDTO> getByUserId(Integer userId) {
        List<TrainingRoutine> trainingRoutines = repository.findAll();
        return trainingRoutines.stream()
            .filter(routine -> routine.getUser().getIdUser().equals(userId))
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
}
