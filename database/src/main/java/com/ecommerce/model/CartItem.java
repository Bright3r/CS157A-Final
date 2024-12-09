package com.ecommerce.model;

public class CartItem {

    private Product product;
    private Integer quantityOrdered;

    public CartItem() {}

    public CartItem(Product product, Integer quantityOrdered) {
        this.product = product;
        this.quantityOrdered = quantityOrdered;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
    
    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                "quantityOrdered=" + quantityOrdered +
               '}';
    }
}
