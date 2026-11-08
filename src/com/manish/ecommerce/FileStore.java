package com.manish.ecommerce;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileStore {
    private static final String FILE_PATH ="product.csv";
    public void createFile(){
        File file = new File(FILE_PATH);
        try {
            if(file.createNewFile()){
                System.out.println("file is created");
            }else{
                System.out.println("file already exists");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: "+ e.getMessage());
        }
    }
    public void writeProduct(List<Product> products)  {
        try (FileWriter writer =new FileWriter(FILE_PATH)){

           String data = products.stream().map(Product::toFileLine).collect(Collectors.joining("\n", "", "\n"));
            writer.write(data);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public List<Product> loadProducts() {
        File file =new File(FILE_PATH);
        if(!file.exists()){
            System.out.println("the file is not found ");
            return new ArrayList<>();
        }
        List<Product> products = new ArrayList<>();
        try(BufferedReader reader =new BufferedReader(new FileReader(FILE_PATH))) {
            String line ;
            while ((line =reader.readLine())!=null){
                if(!line.isEmpty()){
                    String[] parts =line.split(",");
                    Product p = new Product(parts[1],parts[0],Long.parseLong(parts[2]),Integer.parseInt(parts[3]));
                   products.add(p);

                }


            }

        } catch (IOException e) {
            System.out.println("error ReaderLoad : "+e.getMessage());

        }
        return products;

    }
}
