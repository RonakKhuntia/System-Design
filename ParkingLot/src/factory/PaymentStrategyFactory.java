package factory;

import enums.PaymentMode;
import strategy.PaymentStrategy;
import strategy.impl.payment.CardPayment;
import strategy.impl.payment.CashPayment;
import strategy.impl.payment.UpiPayment;

public class PaymentStrategyFactory {
    public static PaymentStrategy get(PaymentMode mode) {
        return switch (mode) {
            case CASH -> new CashPayment();
            case UPI -> new UpiPayment();
            case CARD -> new CardPayment();
        };
    }
}
