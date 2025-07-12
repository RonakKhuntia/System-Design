package strategy.impl.payment;

import model.Ticket;
import strategy.PaymentStrategy;

public class CashPayment implements PaymentStrategy {

    @Override
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Paid Rs." + amount + " for ticket " + ticket.getTicketId() + " via Cash.");
        return true;
    }
}
