package com.ghostappi.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

	import com.ghostappi.backend.model.Reward;
	import com.ghostappi.backend.repository.RewardRepository;

import jakarta.transaction.Transactional;

    @Service
    @Transactional
    public class RewardService {
    @Autowired
    private RewardRepository repor;

    public List<Reward> getAll(){
        return repor.findAll();
    }

    public void save(Reward rew){
      repor.save(rew);
    }

    public Reward getIdReward(Integer IdReward){
      return repor.findById(IdReward).get();
    }

    public void delete(Integer IdReward){
      repor.deleteById(IdReward);
    }
}
