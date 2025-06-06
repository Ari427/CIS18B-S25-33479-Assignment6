package TicketReservationSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TicketPool ticketPool = new TicketPool(5);

        // Create a list of customers
        List<Runnable> customers = new ArrayList<>();
        customers.add(new Customer("Mark", ticketPool));
        customers.add(new Customer("Eve", ticketPool));
        customers.add(new Customer("Nolan", ticketPool));
        customers.add(new Customer("Debbie", ticketPool));
        customers.add(new Customer("Olliver", ticketPool));
        customers.add(new Customer("Alan", ticketPool));

        // Shuffle the list to randomize the order
        Collections.shuffle(customers);

        // Start each customer as a virtual thread
        for (Runnable customer : customers) {
            Thread.startVirtualThread(customer);
        }

        // Stalling to allow all threads to finish
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Reservation system finished.");
    }
}
