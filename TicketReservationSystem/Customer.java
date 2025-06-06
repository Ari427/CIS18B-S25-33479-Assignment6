package TicketReservationSystem;
public class Customer implements Runnable {
    private final String name;
    private final TicketPool ticketPool;

    public Customer(String name, TicketPool ticketPool) {
        this.name = name;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        synchronized (ticketPool) {
            while (ticketPool.getAvailableTickets() <= 0) {
                try {
                    System.out.println(name + " is waiting for tickets to be available...");
                    ticketPool.wait(); // Wait until notified
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            Ticket ticket = ticketPool.reserveTicket(name);
            if (ticket != null) {
                System.out.println(name + " got ticket for: " + ticket.getEvent());
            }

            ticketPool.notifyAll(); // Notify other waiting customers
        }
    }
}
