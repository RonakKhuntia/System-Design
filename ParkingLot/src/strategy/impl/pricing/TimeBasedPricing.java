package strategy.impl.pricing;

import enums.VehicleType;
import strategy.PricingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeBasedPricing implements PricingStrategy {
    private static final LocalTime PEAK_START = LocalTime.of(8, 0);
    private static final LocalTime PEAK_END = LocalTime.of(17, 0);

    private boolean isPeak(LocalTime time) {
        return !time.isBefore(PEAK_START) && !time.isAfter(PEAK_END);
    }

    @Override
    public double calculateFee(VehicleType type, LocalDateTime entryTime, LocalDateTime exitTime) {
        if (exitTime.isBefore(entryTime)) {
            throw new IllegalArgumentException("ExitTime is before EntryTime");
        }

        long durationMinutes = Duration.between(entryTime, exitTime).toMinutes();
        long totalHours = (long) Math.ceil((double) durationMinutes / 60);

        int peakHours = 0;
        int nonPeakHours = 0;

        LocalDateTime currentHour = entryTime;
        for (int i = 0; i < totalHours; i++) {
            if (isPeak(currentHour.toLocalTime())) {
                peakHours++;
            } else {
                nonPeakHours++;
            }
            currentHour = currentHour.plusHours(1);
        }

        double peakRate = switch (type) {
            case CAR -> 30.0;
            case BIKE -> 15.0;
            case TRUCK -> 50.0;
        };

        double nonPeakRate = switch (type) {
            case CAR -> 20.0;
            case BIKE -> 10.0;
            case TRUCK -> 30.0;
        };

        return peakHours * peakRate + nonPeakHours * nonPeakRate;
    }
}
