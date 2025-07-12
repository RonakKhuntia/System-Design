package strategy.impl.payment;

import model.Ticket;
import strategy.PaymentStrategy;

public class UpiPayment implements PaymentStrategy {

    @Override
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Paid Rs." + amount + " for ticket " + ticket.getTicketId() + " via Upi.");
        return true;
    }
}
