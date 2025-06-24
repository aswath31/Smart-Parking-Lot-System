package com.example.parkingfloor;
public class ParkingFloor {
  // DIP


  Map<ParkingSpotType, Map<String, ParkingSpot>> parkingSpots;

    @Id
    private UUID id;

    private String floorName;
    private boolean isUnderMaintenance;

    @ManyToOne
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "parkingFloor", cascade = CascadeType.ALL)
    private List<ParkingSpot> spots = new ArrayList<>();

  public ParkingFloor(String floorName, DisplayBoard displayBoard) {

    this.floorName = floorName;
    this.displayBoard = displayBoard;
    this.isFloorUnderMaintenance = false;
    this.parkingSpots = new HashMap<>();
  }

  public boolean addParkingSpot(ParkingSpotType spotType, String spotName) {
    if (parkingSpots.containsKey(spotType)) {
      parkingSpots.get(spotType).put(spotName, new ParkingSpot(spotName, spotType));
      displayBoard.showAddNewParkingSpot(spotName, spotType);
      return true;
    }
    HashMap<String, ParkingSpot> spotMap = new HashMap<>();
    spotMap.put(spotName, new ParkingSpot(spotName, spotType));
    parkingSpots.put(spotType, spotMap);
    displayBoard.showAddNewParkingSpot(spotName, spotType);
    return false;
  }

  public boolean removeParkingSpot(ParkingSpotType spotType, String spotName) {
    if (parkingSpots.containsKey(spotType)) {
      parkingSpots.get(spotType).remove(spotName);
      return true;
    }
    return false;
  }


  public int getAvailableSpotsCount(ParkingSpotType spotType) {
    if (parkingSpots.containsKey(spotType)) {
      return (int) parkingSpots.get(spotType).values().stream().filter(spot -> !spot.isOccupied()).count();
    }
    return 0;
  }

  public int getTotalAvailableSpotsCount() {
    return parkingSpots.values().stream().mapToInt(spotMap -> (int) spotMap.values().stream().filter(spot -> !spot.isOccupied()).count()).sum();
  }

  public int getOccupiedSpotsCount(ParkingSpotType spotType) {
    if (parkingSpots.containsKey(spotType)) {
      return (int) parkingSpots.get(spotType).values().stream().filter(ParkingSpot::isOccupied).count();
    }
    return 0;
  }

  public int getTotalOccupiedSpotsCount() {
    return parkingSpots.values().stream().mapToInt(spotMap -> (int) spotMap.values().stream().filter(ParkingSpot::isOccupied).count()).sum();
  }


  public synchronized ParkingSpot getAvailableParkingSpotForVehicle(ParkingSpotType spotType) {
    // KISS
    if (isFloorUnderMaintenance) {
      displayBoard.showParkingFloorMaintenanceMessage();
      return null;
    }

    if (getAvailableSpotsCount(spotType) == 0) {
      displayBoard.showParkingFullMessage();
      return null;
    }
    displayBoard.showCountOfAvailableSpots(spotType, getAvailableSpotsCount(spotType));
    displayBoard.showAvailableSpots(parkingSpots.get(spotType), spotType);
    if (parkingSpots.containsKey(spotType)) {
      return parkingSpots.get(spotType).values().stream().filter(spot -> !spot.isOccupied()).findFirst().orElse(null);
    }


    return null;
  }

  public Map<String, ParkingSpot> getOccupiedParkingSpots(ParkingSpotType spotType) {
    if (parkingSpots.containsKey(spotType)) {
      return parkingSpots.get(spotType).values().stream().filter(ParkingSpot::isOccupied).collect(
          Collectors.toMap(ParkingSpot::getSpotName, spot -> spot));
    }
    return null;
  }


  public void enableParkingFloorMaintenance() {
    isFloorUnderMaintenance = true;
    displayBoard.showParkingFloorMaintenanceMessage();
  }

  public String getFloorName() {
    return floorName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }

  public Map<ParkingSpotType, Map<String, ParkingSpot>> getParkingSpots() {
    return parkingSpots;
  }

  public void setParkingSpots(Map<ParkingSpotType, Map<String, ParkingSpot>> parkingSpots) {
    this.parkingSpots = parkingSpots;
  }

  public DisplayBoard getDisplayBoard() {
    return displayBoard;
  }

  public void setDisplayBoard(DisplayBoard displayBoard) {
    this.displayBoard = displayBoard;
  }

  public boolean isFloorUnderMaintenance() {
    return isFloorUnderMaintenance;
  }

  public void setFloorUnderMaintenance(boolean floorUnderMaintenance) {
    isFloorUnderMaintenance = floorUnderMaintenance;
  }
}
