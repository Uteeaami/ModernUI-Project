# ModernUI-Project

A simple JavaFX-based media player application, that was done to University of Turku's Modern User Interfaces course.
Built using Maven and JavaFX 23.

## Requirements

- Java 9 or later
- Maven

## Getting Started

### Clone the Repository

```
git clone https://github.com/Uteeaami/ModernUI-Project.git
cd GuiProject
```

### Running the Application

#### Using Maven Wrapper

```
./mvnw clean javafx:run  # macOS/Linux
mvnw.cmd clean javafx:run # Windows
```

#### Using Installed Maven

```
mvn clean javafx:run
```

### Building the Application

To compile the project:

```
mvn clean compile
```

To package it into a JAR file:

```
mvn clean package
```

The built JAR file will be located in the `target/` directory.
