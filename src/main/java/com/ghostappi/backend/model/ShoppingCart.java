package com.ghostappi.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import jakarta.persistence.Id;


@Document(collection = "ShoppingCart")
public class ShoppingCart {
    @Id
    @Field("idShoppingCart")
    private Integer idShoppingCart;
    @Field("quantity")
    private Integer quantity;
    @Field("total")
    private Integer total;
    @Field("idCustomer")
    private Integer idCustomer;

    

    /**
     * @return the Id_shoppingCart
     */
    public Integer getIdShoppingCart() {
        return idShoppingCart;
    }

    /**
     * @param Id_shoppingCart the Id_shoppingCart to set
     */
    public void setIdShoppingCart(Integer idShoppingCart) {
        this.idShoppingCart = idShoppingCart;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the Id_Customer
     */
    public Integer getIdCustomer() {
        return idCustomer;
    }

    /**
     * @param Id_Customer the Id_Customer to set
     */
    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    

 
}

