package com.example.parkinglot;
public class ParkingLot {
 

  List<ParkingFloor> parkingFloors;

  EntryPanel entryPanel;

  ExitPanel exitPanel;

    @Id
    private UUID parkingLotId;

    private String parkingLotName;
    private String address;
    private int totalFloors;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<ParkingFloor> floors = new ArrayList<>();


  private final TicketManager ticketManager = new TicketManager();


  public ParkingLot(String parkingLotId, String parkingLotName,
      String address, int totalFloors, EntryPanel entryPanel, ExitPanel exitPanel) {
    this.parkingLotId = parkingLotId;
    this.parkingLotName = parkingLotName;
    this.address = address;
    this.totalFloors = totalFloors;
    this.parkingFloors = new ArrayList<>();
    this.entryPanel = entryPanel;
    this.exitPanel = exitPanel;
  }

  public boolean isParkingLotFull() {
    return parkingFloors.stream().allMatch(floor -> floor.getTotalAvailableSpotsCount() == 0);
  }

  public boolean isParkingLotEmpty() {
    return parkingFloors.stream().allMatch(floor -> floor.getTotalOccupiedSpotsCount() == 0);
  }

  public boolean addParkingFloor(ParkingFloor floor) {
    return parkingFloors.add(floor);
  }

  public boolean removeParkingFloor(ParkingFloor floor) {
    return parkingFloors.remove(floor);
  }

  public ParkingSpotType generateSpotTypeBasedOnVehicleType(VehicleType vehicleType) {
    switch (vehicleType) {
      case MOTORCYCLE:
        return ParkingSpotType.TWO_WHEELER;
      case BICYCLE:
        return ParkingSpotType.TWO_WHEELER;
      case CAR:
        return ParkingSpotType.MEDIUM;
      case TRUCK:
        return ParkingSpotType.LARGE;
      default:
        return null;
    }
  }

  public ParkingFloor computeFloorToBeParkedOn(VehicleType vehicleType) {
    ParkingSpotType parkingSpotType = vehicleType.getRequiredSpotType();

    for (ParkingFloor parkingFloor : parkingFloors) {
      if (!parkingFloor.isFloorUnderMaintenance() && parkingFloor.getAvailableSpotsCount(parkingSpotType) > 0) {
        return parkingFloor;
      }
    }
    return null;
  }

  public void parkVehicle(Vehicle vehicle) {
    ParkingFloor parkingFloor = computeFloorToBeParkedOn(vehicle.getType());
    if (parkingFloor == null) {
        System.out.println("No available parking floor for vehicle type: " + vehicle.getType());
        return;
    }

    ParkingTicket ticket = entryPanel.generateParkingTicket(vehicle, parkingFloor);
    ParkingSpot spot = parkingFloor.getAvailableParkingSpotForVehicle(vehicle.getType().getRequiredSpotType());

    if (spot == null) {
        System.out.println("No available spot for vehicle type: " + vehicle.getType());
        return;
    }

    spot.parkVehicle(vehicle);
    ticket.setSpot(spot); 
    ticketManager.saveTicket(ticket); 

    System.out.println("Vehicle parked at spot: " + spot.getSpotName() +
            " on floor: " + parkingFloor.getFloorName() +
            " with ticket id: " + ticket.getTicketId());
}


  public void exitVehicle(Vehicle vehicle, ParkingTicket parkingTicket) {
    ParkingFloor parkingFloor = parkingTicket.getParkingFloor();
    ParkingSpot spot = parkingTicket.getSpot();
    spot.removeVehicle();
    parkingTicket.setExitTime(java.util.Date.from(java.time.Instant.now()));
    double amount = exitPanel.calculateAmount(parkingTicket);
    parkingTicket.setAmount(amount);
    parkingTicket.setPaid(true);
    System.out.println("Vehicle exited from spot: " + spot.getSpotName() + " on floor: " + parkingFloor.getFloorName() + " with ticket id: " + parkingTicket.getTicketId() + " and amount: " + amount);
  }
}
