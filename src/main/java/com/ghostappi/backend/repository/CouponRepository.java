package com.ghostappi.backend.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ghostappi.backend.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Query(value = "SELECT * FROM coupon WHERE discount_percentage = :discountPercentage", nativeQuery = true)
    List<Coupon> getCouponsByDiscountPercentage(@Param("discountPercentage") Integer discountPercentage);

    @Query(value = "SELECT * FROM coupon WHERE expiration_date > :currentDate", nativeQuery = true)
    List<Coupon> getValidCoupons(@Param("currentDate") Date currentDate);

    @Query("SELECT c FROM Coupon c WHERE c.expirationDate > CURRENT_DATE AND c.status = true")
    List<Coupon> findActiveCoupons();

    @Query("SELECT c FROM Coupon c WHERE c.expirationDate < CURRENT_DATE AND c.status = true")
    List<Coupon> findExpiredCoupons();
}
