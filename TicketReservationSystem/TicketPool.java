package TicketReservationSystem;
public class TicketPool {
    private int availableTickets;

    public TicketPool(int totalTickets) {
        this.availableTickets = totalTickets;
    }

    public synchronized Ticket reserveTicket(String customerName) {
        if (availableTickets > 0) {
            availableTickets--;
            System.out.println(customerName + " reserved a ticket. Tickets left: " + availableTickets);
            return new Ticket(availableTickets + 1, "Concert"); // +1 to give proper ticket number
        } else {
            System.out.println(customerName + " tried to reserve but no tickets are left.");
            return null;
        }
    }

    public synchronized int getAvailableTickets() {
        return availableTickets;
    }
}