package com.manish.ecommerce;

import java.util.List;
import java.util.Optional;

public interface Repository <T,ID>{
    void save(T items);  //Save one object
    Optional<T> findById(ID id);  //Find one object, which might not exist
    List<T> findAll();   //Retrieve all objects
}
