package com.ghostappi.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;

//import com.fasterxml.jackson.annotation.JsonProperty;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCoupon;
    @NotBlank
    @Size(min = 1, max = 10)
    private String codeDiscount;
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;
    private Date initDate;
    private Date expirationDate;
    @Min(1)
    @Max(100)
    private Integer discountPercentage;
    //@Id
    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "idCategory", referencedColumnName = "idCategory")
    //@JsonProperty("idCategory")
    //private Category idCategory
    private Integer idCategory;

    /**
     * @return the idCoupon
     */
    public Integer getIdCoupon() {
        return idCoupon;
    }

    /**
     * @param idCoupon the idCoupon to set
     */
    public void setIdCoupon(Integer idCoupon) {
        this.idCoupon = idCoupon;
    }

    /**
     * @return the codeDiscount
     */
    public String getCodeDiscount() {
        return codeDiscount;
    }

    /**
     * @param codeDiscount the codeDiscount to set
     */
    public void setCodeDiscount(String codeDiscount) {
        this.codeDiscount = codeDiscount;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the initDate
     */
    public Date getInitDate() {
        return initDate;
    }

    /**
     * @param initDate the initDate to set
     */
    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    /**
     * @return the expirationDate
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @return the discountPercentage
     */
    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * @param discountPercentage the discountPercentage to set
     */
    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    /**
     * @return the idCategory
     */
    public Integer getIdCategory() {
        return idCategory;
    }

    /**
     * @param idCategory the idCategory to set
     */
    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }
}

    