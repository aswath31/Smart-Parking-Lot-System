package com.example.repository;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, UUID> {
    List<ParkingTicket> findByVehicleAndIsPaidFalse(Vehicle vehicle);
}