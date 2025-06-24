
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // 🛠️ Set up display boards and panels
    DisplayBoard entryBoard = new DisplayBoard(UUID.randomUUID().toString(), "Main Entrance");
    DisplayBoard exitBoard = new DisplayBoard(UUID.randomUUID().toString(), "Main Exit");
    FeeCalculator feeCalculator = new HourlyFeeCalculator();

    EntryPanel entryPanel = new EntryPanel(UUID.randomUUID().toString(), entryBoard);
    ExitPanel exitPanel = new ExitPanel(UUID.randomUUID().toString(), exitBoard, feeCalculator);

    // 🛠️ Set up parking lot
    ParkingLot parkingLot = new ParkingLot(
        UUID.randomUUID().toString(),
        "Smart Parking Lot",
        "City Center", 2, entryPanel, exitPanel);

    // 🛠️ Add floors and spots
    ParkingFloor floor1 = new ParkingFloor("Ground Floor", new DisplayBoard(UUID.randomUUID().toString(), "Ground"));
    ParkingFloor floor2 = new ParkingFloor("First Floor", new DisplayBoard(UUID.randomUUID().toString(), "First"));

    parkingLot.addParkingFloor(floor1);
    parkingLot.addParkingFloor(floor2);

    // Add some spots (real systems would take this from admin input or DB)
    floor1.addParkingSpot(ParkingSpotType.TWO_WHEELER, "T1");
    floor1.addParkingSpot(ParkingSpotType.MEDIUM, "M1");
    floor1.addParkingSpot(ParkingSpotType.LARGE, "L1");

    floor2.addParkingSpot(ParkingSpotType.TWO_WHEELER, "T2");
    floor2.addParkingSpot(ParkingSpotType.MEDIUM, "M2");
    floor2.addParkingSpot(ParkingSpotType.LARGE, "L2");

    System.out.println("Welcome to the Smart Parking Lot 🚗");

    // 🔁 Take user input to park vehicles
    while (true) {
      System.out.println("\nChoose an option:");
      System.out.println("1. Park a Vehicle");
      System.out.println("0. Exit System");
      int choice = scanner.nextInt();
      scanner.nextLine(); // consume newline

      if (choice == 1) {
        try {
          System.out.println("Enter vehicle registration number:");
          String regNumber = scanner.nextLine();

          System.out.println("Enter vehicle color (RED, BLUE, GREEN, etc.):");
          String colorInput = scanner.nextLine().toUpperCase();

          System.out.println("Enter vehicle type (CAR, TRUCK, MOTORCYCLE, etc.):");
          String typeInput = scanner.nextLine().toUpperCase();

          Vehicle vehicle = new Vehicle(regNumber,
              VehicleColor.valueOf(colorInput),
              VehicleType.valueOf(typeInput));

          parkingLot.parkVehicle(vehicle);
        } catch (Exception e) {
          System.out.println("❌ Invalid input. Try again.");
        }
      } else if (choice == 0) {
        System.out.println("👋 Exiting... Thank you!");
        break;
      } else {
        System.out.println("❌ Invalid choice.");
      }
    }

    scanner.close();
  }
}