package com.ghostappi.backend.model;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    @JsonProperty("idProduct")
    private Integer idProduct;
    
    @Column(nullable = false, length = 250)
    private String comercialName;
    
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Short stock;

    @Column(nullable = false)
    private Integer servingSize;

    @Column(nullable = false, length = 8)
    private String unitServingSize;

    @Column(nullable = false)
    private Short servings;

    @Column(nullable = false)
    private Short netContent;

    @Column(nullable = false, length = 8)
    private String unitNetContent;

    @Column(nullable = false, length = 50)
    private String presentation;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Date caducity;

    @Column(nullable = false, length = 25)
    private String lote;

    @Column(nullable = false, length = 100)
    private String flavor;

    @Column(nullable = false, length = 255)
    private String productRecomendation;

    @Column(nullable = false, length = 255)
    private String imgProductPath;

    // @Column(nullable = false)
    // private Integer idCategory;

    // @Column(nullable = false)
    // private Integer idBrand;

    // @Column(nullable = false)
    // private Integer idAdministrationVia;

    public Product() {
    }

    public Product(Integer idProduct, String comercialName, BigDecimal price, Short stock, Integer servingSize,
            String unitServingSize, Short servings, Short netContent, String unitNetContent, String presentation,
            String description, Date caducity, String lote, String flavor, String productRecomendation,
            String imgProductPath/* , Integer idCategory, Integer idBrand, Integer idAdministrationVia*/) {
        this.idProduct = idProduct;
        this.comercialName = comercialName;
        this.price = price;
        this.stock = stock;
        this.servingSize = servingSize;
        this.unitServingSize = unitServingSize;
        this.servings = servings;
        this.netContent = netContent;
        this.unitNetContent = unitNetContent;
        this.presentation = presentation;
        this.description = description;
        this.caducity = caducity;
        this.lote = lote;
        this.flavor = flavor;
        this.productRecomendation = productRecomendation;
        this.imgProductPath = imgProductPath;
        // this.idCategory = idCategory;
        // this.idBrand = idBrand;
        // this.idAdministrationVia = idAdministrationVia;
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
    
    public Short getNetContent() {
        return netContent;
    }
    
    public void setNetContent(Short netContent) {
        this.netContent = netContent;
    }
    
    public String getUnitNetContent() {
        return unitNetContent;
    }
    
    public void setUnitNetContent(String unitNetContent) {
        this.unitNetContent = unitNetContent;
    }
    
    public String getPresentation() {
        return presentation;
    }
    
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getCaducity() {
        return caducity;
    }
    
    public void setCaducity(Date caducity) {
        this.caducity = caducity;
    }
    
    public String getLote() {
        return lote;
    }
    
    public void setLote(String lote) {
        this.lote = lote;
    }
    
    public String getFlavor() {
        return flavor;
    }
    
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
    
    public String getProductRecomendation() {
        return productRecomendation;
    }
    
    public void setProductRecomendation(String productRecomendation) {
        this.productRecomendation = productRecomendation;
    }
}
