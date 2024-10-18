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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    @JsonProperty("idProduct")
    private Integer idProduct;

    @JsonProperty("comercialName")
    @Column(nullable = false, length = 250)
    @NotBlank(message = "The comercial name must no be null and containt at least one character")
    @Size(min = 1, max = 255, message = "The trade name must be greater than 1 character and less than 100 characters.")
    private String comercialName;

    @JsonProperty("price")
    @Column(nullable = false)
    private BigDecimal price;

    @JsonProperty("stock")
    @Column(nullable = false)
    private Short stock;

    @JsonProperty("servingSize")
    @Column(nullable = false)
    private Integer servingSize;

    @JsonProperty("unitServingSize")
    @Column(nullable = false, length = 8)
    private String unitServingSize;

    @JsonProperty("servings")
    @Column(nullable = false)
    private Short servings;

    @JsonProperty("netContent")
    @Column(nullable = false)
    private Short netContent;

    @JsonProperty("unitNetContent")
    @Column(nullable = false, length = 8)
    private String unitNetContent;

    @JsonProperty("presentation")
    @Column(nullable = false, length = 50)
    private String presentation;

    @JsonProperty("description")
    @Column(nullable = false, length = 500)
    private String description;

    @JsonProperty("caducity")
    @Column(nullable = false)
    private Date caducity;

    @JsonProperty("lote")
    @Column(nullable = false, length = 25)
    private String lote;

    @JsonProperty("flavor")
    @Column(nullable = false, length = 100)
    private String flavor;

    @JsonProperty("productRecomendation")
    @Column(nullable = false, length = 255)
    private String productRecomendation;

    @JsonProperty("imgProductPath")
    @Column(nullable = false, length = 255)
    private String imgProductPath;

    @JsonProperty("idCategory")
    @Column(nullable = false)
    private Integer idCategory;

    @JsonProperty("idBrand")
    @Column(nullable = false)
    private Integer idBrand;

    @JsonProperty("idAdministrationVia")
    @Column(nullable = false)
    private Integer idAdministrationVia;

    public Product() {
    }

    public Product(Integer idProduct, String comercialName, BigDecimal price, Short stock, Integer servingSize,
            String unitServingSize, Short servings, Short netContent, String unitNetContent, String presentation,
            String description, Date caducity, String lote, String flavor, String productRecomendation,
            String imgProductPath, Integer idCategory, Integer idBrand, Integer idAdministrationVia) {
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
        this.idCategory = idCategory;
        this.idBrand = idBrand;
        this.idAdministrationVia = idAdministrationVia;
    }

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

    public String getImgProductPath() {
        return imgProductPath;
    }

    public void setImgProductPath(String imgProductPath) {
        this.imgProductPath = imgProductPath;
    }

}
