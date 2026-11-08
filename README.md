# Java E-Commerce CLI

A console-based E-Commerce application developed using Java. This project demonstrates object-oriented programming, Java Collections, repository design, file handling, shopping cart management, and basic order processing.

## Features

- **Product Management:** Add, view, and remove products.
- **Shopping Cart:** Add products, view cart contents, and update quantities by removing items.
- **Order Management:** Create orders and calculate subtotals.
- **Discount System:** Automatically applies a 10% discount when the subtotal reaches ₩100,000.
- **Order Processing:** Processes pending orders using a FIFO queue.
- **File Persistence:** Saves and loads product information using CSV files.
- **Repository Pattern:** Uses repositories to manage product and order data.

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework
  - HashMap
  - ArrayList / List
  - Queue / ArrayDeque
  - Optional
- Java Generics
- Java File I/O
- Git and GitHub

## Project Structure

```text
E-commerce/
│
├── src/
│   └── com/
│       └── manish/
│           └── ecommerce/
│               ├── Main.java
│               ├── Product.java
│               ├── ProductRepository.java
│               ├── Repository.java
│               ├── Cart.java
│               ├── Order.java
│               ├── OrderItem.java
│               ├── OrderRepository.java
│               └── FileStore.java
│
├── product.csv
├── .gitignore
└── README.md
```

## Application Menu

The application provides the following console operations:

1. View Products
2. Add New Product
3. Add Product to Cart
4. View Cart
5. Remove Product
6. Remove Product from Cart
7. Checkout
8. Process Next Order
9. Exit

## How to Run

### Requirements

- Java JDK 17 or later
- IntelliJ IDEA or another Java IDE

### Run using Terminal

Clone the repository:

```bash
git clone https://github.com/Manishr767/Java-Ecommerce-CLI.git
```

Navigate into the project:

```bash
cd Java-Ecommerce-CLI
```

Compile the Java source files:

```bash
javac -d out src/com/manish/ecommerce/*.java
```

Run the application:

```bash
java -cp out com.manish.ecommerce.Main
```

Alternatively, open the project in IntelliJ IDEA and run `Main.java`.

## Data Storage

Product information is stored in `product.csv`.

The application uses CSV file handling to load product information when it starts and save product changes.

Product data is stored using these fields:

- Product ID
- Product Name
- Price
- Available Stock

## Current Limitations

- Product information is persisted using CSV files.
- Order history and pending orders are stored in memory and are not preserved after the application closes.
- The application currently uses a console-based interface.
- No user authentication or online payment functionality is implemented.

## Future Improvements

- Implement database integration using MySQL or PostgreSQL.
- Build REST APIs using Spring Boot.
- Add user authentication and authorization.
- Persist order history.
- Develop a web-based frontend.
- Add unit tests using JUnit.

## Learning Objectives

This project was developed as a practical exercise to strengthen my Java programming skills and understand how different components of an application work together.

Key learning areas include:

- Designing classes and relationships.
- Applying encapsulation and object-oriented principles.
- Working with collections and generics.
- Separating application responsibilities using repositories.
- Managing shopping carts and orders.
- Reading and writing files.
- Using Git and GitHub for version control.

## Author

**Manish Rauniyar**

GitHub: [@Manishr767](https://github.com/Manishr767)

---

*Built as a Java learning project and a foundation for future backend development with Spring Boot.*
