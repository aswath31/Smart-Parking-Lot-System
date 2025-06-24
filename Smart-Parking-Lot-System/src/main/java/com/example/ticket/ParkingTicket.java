package com.example.ticket;
public class ParkingTicket {
    @Id
    private UUID ticketId;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private ParkingSpot spot;

    @ManyToOne
    private ParkingFloor floor;

    private Timestamp entryTime;
    private Timestamp exitTime;

    private double amount;
    private boolean isPaid;

  public ParkingTicket(String ticketId, Vehicle vehicle, ParkingSpot spot, Date entryTime, ParkingFloor floor) {
    this.ticketId = ticketId;
    this.vehicle = vehicle;
    this.spot = spot;
    this.entryTime = entryTime;
    this.parkingFloor = floor;
    this.isPaid = false;
  }

  public String getTicketId() {
    return ticketId;
  }

  public void setTicketId(String ticketId) {
    this.ticketId = ticketId;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public ParkingSpot getSpot() {
    return spot;
  }

  public void setSpot(ParkingSpot spot) {
    this.spot = spot;
  }

  public Date getEntryTime() {
    return entryTime;
  }

  public void setEntryTime(Date entryTime) {
    this.entryTime = entryTime;
  }

  public Date getExitTime() {
    return exitTime;
  }

  public void setExitTime(Date exitTime) {
    this.exitTime = exitTime;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public ParkingFloor getParkingFloor() {
    return parkingFloor;
  }

  public void setParkingFloor(ParkingFloor parkingFloor) {
    this.parkingFloor = parkingFloor;
  }

  public boolean isPaid() {
    return isPaid;
  }

  public void setPaid(boolean paid) {
    isPaid = paid;
  }
}
