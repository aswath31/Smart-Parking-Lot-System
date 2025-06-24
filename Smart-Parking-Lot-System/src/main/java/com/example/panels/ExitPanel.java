package com.example.panels;
public class ExitPanel {
  String panelId;

  DisplayPanel displayPanel;

  FeeCalculator feeCalculator;

  public ExitPanel(String panelId, DisplayPanel displayPanel, FeeCalculator feeCalculator) {
    this.panelId = panelId;
    this.displayPanel = displayPanel;
    this.feeCalculator = feeCalculator;
}


  public ParkingTicket checkout(ParkingTicket parkingTicket) {
    parkingTicket.setExitTime(new Date());
    parkingTicket.setPaid(true);
    parkingTicket.setAmount(calculateAmount(parkingTicket));
    return parkingTicket;
  }

   public double calculateAmount(ParkingTicket parkingTicket) {
        return feeCalculator.calculateFee(parkingTicket); 
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
