
package com.manish.ecommerce;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

     static void main() {

        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();
        FileStore fileStore = new FileStore();
        Cart cart = new Cart(productRepository);

        Scanner scanner = new Scanner(System.in);

        loadProduct(productRepository, fileStore);

        boolean running = true;
        int orderCounter = 1;

        while (running) {

            displayMenu();

            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    viewProducts(productRepository);
                    break;

                case "2":
                    addNewProduct(scanner, productRepository, fileStore);
                    break;

                case "3":
                    addProductCart(scanner, cart);
                    break;

                case "4":
                    cart.viewCart();
                    break;

                case "5":
                    removeProduct(scanner, productRepository, fileStore);
                    break;

                case "6":
                    removeProductCart(scanner, cart);
                    break;

                case "7": {
                    String orderId = String.format("ORD%03d", orderCounter);

                    Order order = cart.createOrder(orderId);

                    orderRepository.save(order);
                    orderRepository.addToQueue(order);

                    fileStore.writeProduct(productRepository.findAll());

                    order.displayOrder();

                    orderCounter++;
                    break;
                }

                case "8": {
                    Order order = orderRepository.processNextOrder();

                    if (order == null) {
                        System.out.println("No pending orders.");
                    } else {
                        System.out.println("\n===== Order Processed =====");
                        order.displayOrder();
                    }
                    break;
                }

                case "9":
                    fileStore.writeProduct(productRepository.findAll());
                    running = false;
                    System.out.println("Thank you for using our store!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void loadProduct(
            ProductRepository productRepository,
            FileStore fileStore) {

        List<Product> products = fileStore.loadProducts();

        if (products.isEmpty()) {
            System.out.println("There are no products in the store.");
        } else {
            products.forEach(productRepository::save);
            System.out.println("Products loaded successfully.");
        }
    }

    private static void displayMenu() {

        System.out.println("\n===== E-Commerce Store =====");
        System.out.println("1. View Products");
        System.out.println("2. Add New Product");
        System.out.println("3. Add Product to Cart");
        System.out.println("4. View Cart");
        System.out.println("5. Remove Product");
        System.out.println("6. Remove Product from Cart");
        System.out.println("7. Checkout");
        System.out.println("8. Process Next Order");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }

    private static void viewProducts(ProductRepository productRepository) {

        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            System.out.println("There are no products in the store.");
        } else {
            System.out.println("\n===== Available Products =====");

            products.forEach(System.out::println);
        }
    }

    private static void addNewProduct(
            Scanner scanner,
            ProductRepository productRepository,
            FileStore fileStore) {

        System.out.println("\n===== Add New Product =====");

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine().trim();

        System.out.print("Enter Product Price: ");
        long price = Long.parseLong(scanner.nextLine().trim());

        System.out.print("Enter Product Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        Product product = new Product(name, id, price, quantity);

        productRepository.save(product);

        fileStore.writeProduct(productRepository.findAll());

        System.out.println("Product added successfully.");
    }

    private static void addProductCart(Scanner scanner, Cart cart) {

        System.out.println("\n===== Add Product to Cart =====");

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine().trim();

        System.out.print("Enter Product Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        cart.addToCart(productId, quantity);
    }

    private static void removeProductCart(Scanner scanner, Cart cart) {

        System.out.println("\n===== Remove Product from Cart =====");

        System.out.print("Enter Product ID: ");
        String productId = scanner.nextLine().trim();

        System.out.print("Enter Product Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        cart.removeFromCart(productId, quantity);
    }

    private static void removeProduct(
            Scanner scanner,
            ProductRepository productRepository,
            FileStore fileStore) {

        System.out.println("\n===== Remove Product =====");

        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine().trim();

        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {

            productRepository.remove(product.get());

            fileStore.writeProduct(productRepository.findAll());

            System.out.println("Product removed successfully.");

        } else {
            System.out.println("Product not found.");
        }
    }
}
