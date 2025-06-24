package com.example.repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, UUID> {
    List<ParkingSpot> findBySpotTypeAndIsOccupiedFalse(ParkingSpotType type);
}