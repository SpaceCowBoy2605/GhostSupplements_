package com.ghostappi.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.Reward;
	import com.ghostappi.backend.repository.RewardRepository;

	import jakarta.transaction.Transactional;

    @Service
    @Transactional
    public class RewardService {
    @Autowired
    private RewardRepository rewardpor;

    public Page<Reward> getAll(Pageable pageable){
        return rewardpor.findAll(pageable);
    }

    public void save(Reward rew){
      rewardpor.save(rew);
    }

    public Reward getIdReward(Integer IdReward){
      return rewardpor.findById(IdReward).get();
    }

    public void delete(Integer IdReward){
      rewardpor.deleteById(IdReward);
    }
}
