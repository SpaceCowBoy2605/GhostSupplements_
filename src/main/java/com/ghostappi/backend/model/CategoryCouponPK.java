package com.ghostappi.backend.model;

import java.io.Serializable;
import java.util.Objects;

public class CategoryCouponPK implements Serializable {
    private Coupon idCoupon;
    private Category idCategory;
    @Override
   public boolean equals(Object o) {
        if (this == o) 
        return true;
        if (!(o instanceof CategoryCouponPK categoryCouponPK))
            return false;
        return idCategory.getIdCategory() == categoryCouponPK.idCategory.getIdCategory() && Objects.equals(idCoupon, categoryCouponPK.idCoupon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCoupon, idCategory);
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public Coupon getIdCoupon() {
        return idCoupon;
    }   
}

