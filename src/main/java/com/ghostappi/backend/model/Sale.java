package com.ghostappi.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSale;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;

    @Column(nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal total;

    @Column(nullable = false, length = 25)
    @NotBlank(message = "Payment method must not be blank")
    @Size(max = 25, message = "Payment method must be at most 25 characters")
    private String paymentMethod;

    @Column(nullable = false)
    @NotNull
    private Integer generatedPoints;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    @NotNull
    private User user;

//    @ManyToOne
//    @JoinColumn(name="idCoupon", nullable = false)
//    @NotNull
//    private Coupon coupon;

    // Getters y Setters

    public Integer getIdSale() {
        return idSale;
    }

    public void setIdSale(Integer idSale) {
        this.idSale = idSale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getGeneratedPoints() {
        return generatedPoints;
    }

    public void setGeneratedPoints(Integer generatedPoints) {
        this.generatedPoints = generatedPoints;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Integer getIdCoupon() {
//        return idCoupon;
//    }

//    public void setIdCoupon(Integer idCoupon) {
//        this.idCoupon = idCoupon;
//    }
}

