package com.manish.ecommerce;

import java.util.HashMap;
import java.util.Map;

public class Order {
    public String getOrderId() {
        return orderId;
    }

    private final String orderId;
    private final Map<String,OrderItem> items;





    public Order(String orderId){
        this.orderId=orderId;
        this.items=new HashMap<>();
    }


    public void addOrder(OrderItem item){

        String id =item.getProductId();

        if(items.containsKey(id)){
            OrderItem existingItem=items.get(id);
            int existingQuantity =existingItem.getQuantity();
            int newQuantity =item.getQuantity()+existingQuantity;

            items.put(id,new OrderItem(id,newQuantity,item.getPriceAtPurchase()));
        }else {
            items.put(id ,new OrderItem(id, item.getQuantity(), item.getPriceAtPurchase()));

        }
    }
    public long subtotal(){
        long total =0;
        for (OrderItem item:items.values()){
             total += item.getQuantity()*item.getPriceAtPurchase();
        }
        return total;

    }
    public long calculateDiscount() {
        long discount=0;
        if(subtotal()>=100000) {
            discount = subtotal() * 10/ 100;

        }
        return discount;
    }
    public long finalAmount(){
     return subtotal()-calculateDiscount();
    }


    public void displayOrder() {
        System.out.println("===== ORDER SUMMARY =====");
        System.out.println("Order ID: " + getOrderId());
        System.out.println("Subtotal: ₩" + subtotal());
        System.out.println("Discount: ₩" + calculateDiscount());
        System.out.println("Final Amount: ₩" + finalAmount());
        System.out.println("=========================");
    }

    @Override
    public String toString() {
        return "\nOrder ID: " + getOrderId() +
                "\nSubtotal: ₩" + subtotal() +
                "\nDiscount: ₩" + calculateDiscount() +
                "\nFinal Amount: ₩" + finalAmount() +
                "\n-------------------------";
    }






}
