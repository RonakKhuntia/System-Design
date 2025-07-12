package strategy.impl.pricing;

import enums.VehicleType;
import strategy.PricingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class EventBasedPricing implements PricingStrategy {
    private static final Map<VehicleType, Double> EVENT_HOURLY_RATES = Map.of(
            VehicleType.CAR, 50.0,
            VehicleType.BIKE, 30.0,
            VehicleType.TRUCK, 70.0
    );

    @Override
    public double calculateFee(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime) {
        long durationMinutes = Duration.between(entryTime, exitTime).toMinutes();
        long hours = (long) Math.ceil(durationMinutes / 60);

        double ratePerHour = EVENT_HOURLY_RATES.getOrDefault(type, 0.0);
        return ratePerHour * hours;
    }
}
