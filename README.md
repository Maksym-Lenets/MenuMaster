# MenuMaster - Restaurant Ordering System

MenuMaster is a backend application for a restaurant ordering system. It provides REST APIs for ordering lunches and
drinks, with support for various cuisines, meal customization, and extensibility.

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [API Documentation](#api-documentation)
- [Database](#database)

## Getting Started

These instructions will help you get the project up and running on your local machine.

### Prerequisites

Before you begin, make sure you have the following software installed:

- [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [H2 Database](https://www.h2database.com/html/download.html) (for local development)
- [Git](https://git-scm.com/downloads) (optional)

### Installation

1. Clone the repository (if you haven't already): git clone https://github.com/yourusername/MenuMaster.git
2. Build and run the application using Maven: mvn spring-boot:run

The application should now be running locally at http://localhost:8080.

### API Documentation

The API documentation can be found at: http://localhost:8080/swagger-ui/index.html.
This documentation provides details about the available endpoints, request parameters, and expected responses.

### Database

The application uses H2 Database for local development. You can access the H2 console
at: http://localhost:8080/h2-console

* JDBC URL: jdbc:h2:mem:testdb
* Username: sa
* Password: password