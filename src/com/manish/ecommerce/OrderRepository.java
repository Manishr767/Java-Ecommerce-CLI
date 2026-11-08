package com.manish.ecommerce;

import java.util.*;
import java.util.stream.Collectors;

public class OrderRepository implements Repository<Order,String>{
    private final HashMap<String,Order> orders=new HashMap<>();
    private final Queue<Order> pendingOrders = new ArrayDeque<>();


    @Override
    public void save(Order items) {
        if(orders.containsKey(items.getOrderId())){
            throw new IllegalArgumentException("OrderId is exist");
        }else {
            orders.put(items.getOrderId(), items);
        }

    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<Order> findAll() {
        return orders.values().stream().sorted(Comparator.comparing(Order::getOrderId)).collect(Collectors.toUnmodifiableList());
    }
    public void addToQueue(Order order) {
        pendingOrders.offer(order);
    }
    public Order processNextOrder() {
        return pendingOrders.poll();
    }
}
