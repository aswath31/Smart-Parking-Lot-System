package com.example.parkingspot;
public class ParkingSpot {

    @Id
    private UUID id;

    private String spotName;

    @Enumerated(EnumType.STRING)
    private ParkingSpotType spotType;

    private boolean isOccupied;

    @ManyToOne
    private ParkingFloor parkingFloor;

    @ManyToOne(optional = true)
    private Vehicle vehicle;


  public ParkingSpot(Vehicle vehicle, String spotName, boolean isOccupied, ParkingSpotType spotType) {
    this.vehicle = vehicle;
    this.spotName = spotName;
    this.isOccupied = isOccupied;
    this.spotType = spotType;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public String getSpotName() {
    return spotName;
  }

  public void setSpotName(String spotName) {
    this.spotName = spotName;
  }

  public boolean isOccupied() {
    return isOccupied;
  }

  public void setOccupied(boolean occupied) {
    isOccupied = occupied;
  }

  public ParkingSpotType getSpotType() {
    return spotType;
  }

  public void setSpotType(ParkingSpotType spotType) {
    this.spotType = spotType;
  }

  Vehicle vehicle;

  String spotName;

  boolean isOccupied;

  ParkingSpotType spotType;

  public ParkingSpot(String spotName, ParkingSpotType spotType) {
    this.spotName = spotName;
    this.spotType = spotType;
    this.vehicle = null;
    this.isOccupied = false;
  }

  public boolean parkVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
    this.isOccupied = true;
    return true;
  }

  public boolean removeVehicle() {
    this.vehicle = null;
    this.isOccupied = false;
    return true;
  }
}
