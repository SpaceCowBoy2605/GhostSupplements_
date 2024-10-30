package com.ghostappi.backend.dto;

import java.math.BigDecimal;
import java.util.List;

import com.ghostappi.backend.model.AdministrationVia;
import com.ghostappi.backend.model.Brand;
import com.ghostappi.backend.model.Category;
import com.ghostappi.backend.model.Ingredient;
import com.ghostappi.backend.model.NutrientProduct;

public class ProductDTO {
    private Integer idProduct;
    private String comercialName;
    private BigDecimal price;
    private Short stock;
    private Integer servingSize;
    private String unitServingSize;
    private Short servings;
    private Brand brand;
    private Category category;
    private List<Ingredient> ingredients;
    private List<NutrientProduct> nutrientProducts;
    private AdministrationVia administrationVia;

    // Getters and Setters
    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getComercialName() {
        return comercialName;
    }

    public void setComercialName(String comercialName) {
        this.comercialName = comercialName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Short getStock() {
        return stock;
    }

    public void setStock(Short stock) {
        this.stock = stock;
    }

    public Integer getServingSize() {
        return servingSize;
    }

    public void setServingSize(Integer servingSize) {
        this.servingSize = servingSize;
    }

    public String getUnitServingSize() {
        return unitServingSize;
    }

    public void setUnitServingSize(String unitServingSize) {
        this.unitServingSize = unitServingSize;
    }

    public Short getServings() {
        return servings;
    }

    public void setServings(Short servings) {
        this.servings = servings;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<NutrientProduct> getNutrientProducts() {
        return nutrientProducts;
    }

    public void setNutrientProducts(List<NutrientProduct> nutrientProducts) {
        this.nutrientProducts = nutrientProducts;
    }

    public AdministrationVia getAdministrationVia() {
        return administrationVia;
    }

    public void setAdministrationVia(AdministrationVia administrationVia) {
        this.administrationVia = administrationVia;
    }
}
