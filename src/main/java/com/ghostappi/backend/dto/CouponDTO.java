package com.ghostappi.backend.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CouponDTO {
    @JsonIgnore
    private Integer idCoupon;
    @NotBlank(message = "The code discount must no be null and containn at least one character")
    @Size(min = 1, max = 10, message = "The name must be almost 1 character and 10 characters at most")
    private String codeDiscount;
    @NotBlank(message = "The description must no be null and contain at least one character")
    @Size(min = 1, max = 255, message = "The name must be almost 1 character and 255 characters at most")
    private String description;
    @NotNull(message = "The init date must no be null")
    private Date initDate;
    @NotNull(message = "The expiration date must no be null")
    @FutureOrPresent(message = "Must be a date in the future")
    private Date expirationDate;
    @NotNull(message = "The discount porcentage must no be null")
    @Min(value = 1, message = "The value must be at least 1.")
    @Max(value = 100, message = "The value must be at most 100.")
    private Integer discountPercentage;
    @NotNull(message = "The status must no be null")
    private Boolean status;
    //@NotNull(message = "The id category must no be null")
    private Integer idCategory;

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
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return the idCategory
     */
    public Integer getIdCategory() {
        return idCategory;
    }
    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getIdCoupon() {
        return idCoupon;
    }
    public void setIdCoupon(Integer idCoupon) {
        this.idCoupon = idCoupon;
    }
  

   
}
