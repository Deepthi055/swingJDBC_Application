# 🍔 Swing JDBC Burger Ordering App  

A Java-based **Burger Ordering Application** built using **Swing** for UI and **JDBC** for database connectivity. This project allows users to browse burgers, add items to their cart, and place orders seamlessly.  

## 🚀 Features  
- 📌 **User-friendly GUI** with Swing  
- 🛒 **Shopping cart** functionality  
- 🗄️ **JDBC-based database** connectivity  
- 🔐 **Secure user authentication**  
- 📊 **Order summary & receipt generation**  

## 🏗️ Tech Stack  
- **Java Swing** - For GUI components  
- **JDBC** - For database interactions  
- **MySQL** - Backend database  
- **Git & GitHub** - Version control  

## 📂 Project Structure  


## 🛠️ Setup Instructions  

### 1️⃣ Clone the Repository  
```sh
git clone https://github.com/Deepthi085/swingJDBC.git
cd swingJDBC

DB_URL=jdbc:mysql://localhost:3306/burgerdb
DB_USER=root
DB_PASSWORD=yourpassword

Compile and run using:
javac -cp .;mysql-connector-java-8.0.33.jar SwingJDBC/BurgerOrderingApp.java
java -cp .;mysql-connector-java-8.0.33.jar SwingJDBC.BurgerOrderingApp
