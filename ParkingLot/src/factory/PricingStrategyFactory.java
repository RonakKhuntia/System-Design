package factory;

import enums.PricingStrategyType;
import strategy.PricingStrategy;
import strategy.impl.pricing.EventBasedPricing;
import strategy.impl.pricing.TimeBasedPricing;

public class PricingStrategyFactory {
    public static PricingStrategy get(PricingStrategyType type) {
        return switch (type) {
            case TIME_BASED -> new TimeBasedPricing();
            case EVENT_BASED -> new EventBasedPricing();
        };
    }
}
