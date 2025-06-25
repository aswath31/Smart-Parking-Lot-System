#  Smart Parking Lot Management System

A Spring Boot-based backend system that manages vehicle entry/exit, parking spot allocation, real-time availability, and fee calculation for a multi-floor smart parking lot.

---

## Features

-  **Vehicle Entry & Exit**
-  **Automatic Parking Spot Allocation** (based on vehicle size)
-  **Entry & Exit Time Tracking**
-  **Parking Fee Calculation** (configurable)
-  **Multi-Floor Parking Lot Support**
-  **Real-Time Spot Availability Updates**
-  **JPA + Hibernate Integration with RDBMS**
-  **Strategy Pattern for Fee Calculation**

---

##  Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Hibernate**
- **MySQL / PostgreSQL**
- **Lombok**
- **Maven**

---

##  Domain Model Overview

- `ParkingLot`
- `ParkingFloor`
- `ParkingSpot`
- `Vehicle`
- `ParkingTicket`

Relational mappings include:
- One-to-Many: ParkingLot → ParkingFloor → ParkingSpot
- Many-to-One: ParkingSpot → Vehicle
- One-to-Many: Vehicle → ParkingTicket

---

##  Enum Definitions

- `VehicleType` — CAR, TRUCK, MOTORCYCLE, etc.  
- `ParkingSpotType` — TWO_WHEELER, MEDIUM, LARGE  
- `VehicleColor` — RED, BLUE, WHITE, etc.

---

##  Flow Diagram

1. **Vehicle Enters**
2. System allocates a free spot based on type
3. `ParkingTicket` is generated
4. On exit, fee is calculated via `FeeCalculator` strategy
5. Spot is freed and ticket marked as paid

---

## Getting Started

### 1. Clone the Repo

```bash
git clone https://github.com/yourusername/smart-parking-lot.git
cd smart-parking-lot

### 2. Configure the Database
Update src/main/resources/application.properties:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/parkingdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

###3 Build and Run
./mvnw spring-boot:run

