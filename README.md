# JDBC and DAO Study Project

## Introduction
This project is designed to provide a comprehensive study of Java Database Connectivity (JDBC) and the Data Access Object (DAO) design pattern. It aims to offer a practical understanding of how to connect Java applications to a database, perform CRUD (Create, Read, Update, Delete) operations, and implement the DAO pattern for better code organization and maintainability.

## Project Structure
- **src**: Contains the main Java source code.
  - **application**: Package for main Program classes.
  - **model.dao**: Package for DAO-related classes and interfaces.
  - **model.db**: Package for database classes.
  - **model.entities**: Package for entities classes.

## Technologies Used
- **Java**: Core programming language.
- **JDBC**: Java Database Connectivity for interacting with the database.
- **MySQL**: Example database used for this project (any other RDBMS can be used).

## Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL Server (or another database)

### Installation
1. **Clone the repository**:
   ```sh
   git clone git@github.com:leonardoraupp/demo-dao-jdbc.git
   cd demo-dao-jdbc
   ```

2. **Configure the Database**:
   - Create a MySQL database and update the database configuration in `src/main/resources/db.properties`.

## Usage
### JDBC Examples
- **Connection**: Establishing a connection to the database.
- **Statement**: Executing SQL queries using `Statement` and `PreparedStatement`.
- **ResultSet**: Reading data from the database.

### DAO Implementation
- **SellerDAO**: Data Access Object for `Seller` entity.
  - **Seller**: JavaBean representing the seller entity.
  - **SellerDAOImpl**: Implementation of `SellerDAO` interface with methods for CRUD operations.
 
- **DepartmentDAO**: Data Access Object for `Department` entity.
  - **Department**: JavaBean representing the department entity.
  - **DepartmentDAOImpl**: Implementation of `DepartmentDAO` interface with methods for CRUD operations.

## Contributing
Contributions are welcome! Please fork this repository and submit pull requests for any enhancements, bug fixes, or new features.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
