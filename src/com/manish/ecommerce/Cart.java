package com.manish.ecommerce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cart {
    private HashMap<String ,Integer> cart = new HashMap<>();
    private final ProductRepository repo ;
    public Cart(ProductRepository repo){
        this.repo=repo;
    }

    public void addToCart(String productId,int quantity){
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero");
            return;
        }
        int oldQuantity =0;
        Optional<Product> resultProduct = repo.findById(productId);
        if(resultProduct.isPresent()){
            Product product=resultProduct.get();
            if(cart.containsKey(productId)){
                oldQuantity=cart.get(productId);
            }
            int newQuantity = oldQuantity+quantity;
            if(product.getAvailableStock()>=newQuantity){

                cart.put(productId, newQuantity);

            }else {
                System.out.println("Not enough stock available");
            }


        }else {
            System.out.println("Product not available");
        }

    }
    public void viewCart(){
        long subtotal =0;
        long total=0;
        if (cart.isEmpty()){
            System.out.println("cart is empty");
            return;
        }

        for(Map.Entry<String ,Integer> entry: cart.entrySet()){

            Optional<Product> product =repo.findById(entry.getKey());
            if(product.isPresent()) {
                Product foundProduct = product.get();

                subtotal = entry.getValue() * foundProduct.getPrice();
                total += subtotal;
                System.out.printf(
                        "Product: %s | ID: %s | Price: ₩%d | Quantity: %d | Subtotal: ₩%d%n",
                        foundProduct.getName(),
                        entry.getKey(),
                        foundProduct.getPrice(),
                        entry.getValue(),
                        subtotal
                );
            }else {
                System.out.println("Product no longer exists: " + entry.getKey());
            }

        }
        System.out.println("Total cart price: "+total+"₩");
    }
//    public void removeFromCart(String productId , int quantity) {
//       if(quantity<=0){
//           throw new IllegalArgumentException("the quantity is must be greater than 0");
//
//       }
//       if(cart.containsKey(productId)){
//           int currentQuantity=cart.get(productId);
//
//           if(quantity<=currentQuantity){
//               int remainingQuantity = currentQuantity-quantity;
//           if(remainingQuantity==0){
//               cart.remove(productId);
//           }else {
//               cart.put(productId,remainingQuantity);
//           }
//           }else {
//               System.out.println("the stock is not as the quantity");
//           }
//
//
//
//       }
public void removeFromCart(String productId, int quantity) {

    if (quantity <= 0) {
        throw new IllegalArgumentException(
                "The quantity must be greater than 0"
        );
    }

    if (cart.containsKey(productId)) {

        int currentQuantity = cart.get(productId);

        if (quantity <= currentQuantity) {

            int remainingQuantity = currentQuantity - quantity;

            if (remainingQuantity == 0) {
                cart.remove(productId);
                System.out.println("Product removed from cart: " + productId);
            } else {
                cart.put(productId, remainingQuantity);
                System.out.println(
                        "Updated quantity for " + productId
                                + ": " + remainingQuantity
                );
            }

        } else {
            System.out.println(
                    "Cannot remove more items than are in the cart."
            );
        }

    } else {
        System.out.println("Product is not in the cart: " + productId);
    }
}
    public Order createOrder(String orderId) {
        if(cart.isEmpty()){
            System.out.println("cart is empty");
            return null;

        }
        Order order = new Order(orderId);
        for (Map.Entry<String,Integer> c :cart.entrySet()){
            Optional<Product> p =repo.findById(c.getKey());
            if(p.isPresent()){
                Product product =p.get();
                OrderItem orderItem=new OrderItem(product.getProductId(),c.getValue(),product.getPrice());
                product.reduceItems(c.getValue());
                order.addOrder(orderItem);


            }
        }
        cart.clear();
        return order;
    }

    }







