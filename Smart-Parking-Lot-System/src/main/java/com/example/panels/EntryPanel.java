
package com.example.panels;

public class EntryPanel {
  String panelId;

  DisplayPanel displayPanel;

  public EntryPanel(String panelId, DisplayPanel displayPanel) {
    this.panelId = panelId;
    this.displayPanel = displayPanel;
  }

  public ParkingTicket generateParkingTicket(Vehicle vehicle, ParkingFloor floor) {
    ParkingSpotType spotType = vehicle.getType().getRequiredSpotType();

    return new ParkingTicket(UUID.randomUUID().toString(), vehicle, null, new Date(), floor);
  }

 


  public String getPanelId() {
    return panelId;
  }

  public void setPanelId(String panelId) {
    this.panelId = panelId;
  }

  public DisplayPanel getDisplayPanel() {
    return displayPanel;
  }

  public void setDisplayPanel(DisplayPanel displayPanel) {
    this.displayPanel = displayPanel;
  }
}
