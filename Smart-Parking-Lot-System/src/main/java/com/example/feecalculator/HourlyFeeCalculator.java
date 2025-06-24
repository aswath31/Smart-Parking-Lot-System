package com.example.feecalculator;

public class HourlyFeeCalculator implements FeeCalculator {
    private static final double RATE_PER_HOUR = 10;

    @Override
    public double calculateFee(ParkingTicket ticket) {
        long millis = ticket.getExitTime().getTime() - ticket.getEntryTime().getTime();
        long hours = (long) Math.ceil(millis / 3600000.0);
        return hours * RATE_PER_HOUR;
    }
}