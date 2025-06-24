package com.example.repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);
}