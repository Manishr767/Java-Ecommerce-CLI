package com.manish.ecommerce;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository implements Repository<Product,String>{
   private HashMap<String,Product> productHashMap = new HashMap<>();
    @Override
    public void save(Product items) {
        if (productHashMap.containsKey(items.getProductId())) {
            throw new IllegalArgumentException("Product ID is exist");
        } else {
            productHashMap.put(items.getProductId(), items);

        }
    }

    @Override
    public Optional<Product> findById(String id) {

      return Optional.ofNullable(productHashMap.get(id));
    }

    @Override
    public List<Product> findAll() {

      return   productHashMap.values().stream().sorted(Comparator.comparing(Product::getProductId)).collect(Collectors.toUnmodifiableList());


    }
    public void remove(Product items) {
        productHashMap.remove(items.getProductId());

    }

}
