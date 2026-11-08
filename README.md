# Java E-Commerce CLI

A console-based e-commerce application built while practicing Java backend fundamentals. It models a simple shopping workflow: managing products, maintaining a cart, creating orders, applying a discount, and processing queued orders.

## Features

- Product listing, creation, and removal
- Add products to a cart and update cart quantities
- Create orders containing order items
- Calculate order subtotals
- Apply a 10% discount when the subtotal reaches **₩100,000**
- Process pending orders using a FIFO queue
- Load and save product data using CSV file handling

## Technology

- Java
- Object-oriented programming and encapsulation
- Collections: `HashMap`, `List`, `Queue`, and `ArrayDeque`
- Generics and `Optional`
- File I/O and CSV persistence
- Git and GitHub

## Project structure

```text
.
├── product.csv
├── .gitignore
├── README.md
└── src/com/manish/ecommerce/
    ├── Main.java
    ├── Product.java
    ├── ProductRepository.java
    ├── Repository.java
    ├── Cart.java
    ├── Order.java
    ├── OrderItem.java
    ├── OrderRepository.java
    └── FileStore.java
```

## Run locally

### Requirements

- JDK 17 or later (use a JDK compatible with the source code in this repository)
- Terminal or IntelliJ IDEA

### Clone and compile

```bash
git clone https://github.com/Manishr767/Java-Ecommerce-CLI.git
cd Java-Ecommerce-CLI
javac -d out src/com/manish/ecommerce/*.java
```

### Run

```bash
java -cp out com.manish.ecommerce.Main
```

You can also open the project in IntelliJ IDEA and run the application entry point.

## Data persistence

Product records are stored in `product.csv`. Product data is loaded when the application starts and saved when product data changes. Orders and the processing queue are currently held in memory, so they are not retained after the application closes.

## Current scope and limitations

This is a learning project, not a production e-commerce platform. It does not include user accounts, online payments, a web interface, or database-backed order history.

## Planned improvements

- Add automated tests for cart and checkout behavior
- Improve validation and handling of invalid console input
- Persist order history
- Add relational database integration
- Build REST endpoints with Spring Boot

## Author

**Manish Rauniyar** · [GitHub](https://github.com/Manishr767)
