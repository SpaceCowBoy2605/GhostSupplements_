package com.ghostappi.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "nutrientproduct")
@IdClass(NutrientProductPK.class)

public class NutrientProduct {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idproduct")
    @JsonBackReference
    private Product product;
    

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idNutrient" , referencedColumnName = "idNutrient")
    private Nutrient nutrient;
    
    @Column(name = "quantity")
    private double quantity;

    @Column(name = "daily_value_percentage")
    private Short percentage;

    public Product getProduct(){
        return product;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public Nutrient getNutrient(){
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient){
        this.nutrient = nutrient;
    }

    public double getQuantity(){
        return quantity;
    }

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    public Short getPercentage(){
        return percentage;
    }

    public void setPercentage( Short percentage){
        this.percentage = percentage;
    }

}
