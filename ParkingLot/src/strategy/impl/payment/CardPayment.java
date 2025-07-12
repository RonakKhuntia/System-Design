package strategy.impl.payment;

import model.Ticket;
import strategy.PaymentStrategy;

public class CardPayment implements PaymentStrategy {

    @Override
    public boolean processPayment(Ticket ticket, double amount) {
        System.out.println("Paid Rs." + amount + " for ticket " + ticket.getTicketId() + " via Card.");
        return true;
    }
}
