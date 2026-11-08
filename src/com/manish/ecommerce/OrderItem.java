package com.manish.ecommerce;

public class OrderItem {


    private final String productId;
    private final int quantity;
    private final long priceAtPurchase;

    public OrderItem(String productId, int quantity, long priceAtPurchase) {
        if(quantity<=0){
            throw new IllegalArgumentException("quantity should be greater than 0");
        }
        if(priceAtPurchase<0){
            throw new IllegalArgumentException("price cannot be negative");

        }
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;

    }
    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getPriceAtPurchase() {
        return priceAtPurchase;
    }





}
