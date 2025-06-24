package com.example.vehicle;


public class Vehicle {
   

   @Id
    private UUID id;

    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    private VehicleColor color;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<ParkingTicket> tickets = new ArrayList<>();

    private String vehicleRegistrationNumber;

  public Vehicle(String vehicleRegistrationNumber, VehicleColor color, VehicleType type) {
    this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    this.color = color;
    this.type = type;
  }

  public String getVehicleRegistrationNumber() {
    return vehicleRegistrationNumber;
  }

  public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
    this.vehicleRegistrationNumber = vehicleRegistrationNumber;
  }

  public VehicleColor getColor() {
    return color;
  }

  public void setColor(VehicleColor color) {
    this.color = color;
  }

  public VehicleType getType() {
    return type;
  }

  public void setType(VehicleType type) {
    this.type = type;
  }
}
