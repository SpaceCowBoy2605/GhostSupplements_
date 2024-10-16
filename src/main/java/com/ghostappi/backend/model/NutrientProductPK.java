package com.ghostappi.backend.model;

import java.io.Serializable;
import java.util.Objects;

public class NutrientProductPK implements Serializable{
    private Nutrient nutrient;
    private Product product;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NutrientProductPK nutrientProduct)) return false;
        return product.getIdProduct() == nutrientProduct.product.getIdProduct() && Objects.equals(nutrient, nutrientProduct.nutrient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nutrient, product);
    }   

    public Product getProduct() {
        return product;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }
    
}
