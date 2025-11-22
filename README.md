# library-management-system

# Installation
mvn compile
mvn exec:java -Dexec.mainClass="com.example.Main"

CREATE TABLE Library (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(50),
    isbn VARCHAR(13),
    availible INT)
     ;
