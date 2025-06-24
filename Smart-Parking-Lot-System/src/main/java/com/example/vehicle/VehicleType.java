
package com.example.vehicle;


public enum VehicleType {
  CAR(ParkingSpotType.MEDIUM),
  TRUCK(ParkingSpotType.LARGE),
  BUS(ParkingSpotType.LARGE),
  MOTORCYCLE(ParkingSpotType.TWO_WHEELER),
  BICYCLE(ParkingSpotType.TWO_WHEELER);

  private final ParkingSpotType requiredSpotType;

  VehicleType(ParkingSpotType spotType) {
    this.requiredSpotType = spotType;
  }

  public ParkingSpotType getRequiredSpotType() {
    return this.requiredSpotType;
  }
}
