package com.manish.ecommerce;

public class Product {
    private final String name;
    private final String productId;
    private long price ;
    private int availableStock;

    public Product(String name, String productId, long price, int availableStock) {

        this.name = name;
        this.productId = productId;
        setPrice(price);
        setAvailableStock(availableStock);
    }
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public int getAvailableStock() {
        return availableStock;
    }


    //setter
    public void setPrice(long price) {
        if (price >=0){
            this.price = price;
        }else{
            throw new IllegalArgumentException("you cannot set negative value");
        }
    }

    public void setAvailableStock(int availableStock) {

        if (availableStock >=0){
            this.availableStock = availableStock;
        }else{
           throw new IllegalArgumentException("you cannot set negative value");
        }


    }
    public void reduceItems(int quantity){
        if (quantity > 0 && availableStock>=quantity){
            availableStock-=quantity;


        }else{
            throw new IllegalArgumentException("you cannot set negative value");

        }


    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", productId='" + productId + '\'' +
                ", price=" + price +
                ", availableStock=" + availableStock +
                '}';
    }
    public String toFileLine() {
        return productId + "," + name + "," + price + "," + availableStock;
    }



}
