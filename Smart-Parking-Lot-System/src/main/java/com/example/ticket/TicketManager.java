package com.example.ticket;

public class TicketManager {
    private final Map<String, ParkingTicket> ticketMap = new HashMap<>();

    public void saveTicket(ParkingTicket ticket) {
        ticketMap.put(ticket.getTicketId(), ticket);
    }

    public ParkingTicket getTicketById(String ticketId) {
        return ticketMap.get(ticketId);
    }

    public boolean containsTicket(String ticketId) {
        return ticketMap.containsKey(ticketId);
    }

    public void removeTicket(String ticketId) {
        ticketMap.remove(ticketId);
    }
}