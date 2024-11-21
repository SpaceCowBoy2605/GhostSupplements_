package com.ghostappi.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ghostappi.backend.model.Coupon;
import com.ghostappi.backend.repository.CouponRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CouponService {
    @Autowired
    private CouponRepository repo;

	public List<Coupon> getActiveCoupons() {
        return repo.findActiveCoupons();
    }
	public List<Coupon> getInactiveCoupons() {
        return repo.findExpiredCoupons();
    }
    public List<Coupon> getAll() {
		return repo.findAll();
	}

	public Coupon save(Coupon coupon) {
		return repo.save(coupon);
	}

	public Coupon getByidCoupon(Integer idCoupon) {
		return repo.findById(idCoupon)
				.orElseThrow(() -> new NoSuchElementException("The requested item is not registered"));
	}

	/*public void delete(Integer idCoupon) {
		if (!repo.existsById(idCoupon)) {
			throw new NoSuchElementException("The requested item is not registered");
		}
		repo.deleteById(idCoupon);
	} */

}

