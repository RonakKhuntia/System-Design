package model;

import enums.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class ParkingFloor {
    private final String id;
    private final Map<String, ParkingSpot> spots = new HashMap<>();

    public Map<String, ParkingSpot> getSpots() {
        return spots;
    }

    public ParkingFloor(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addSpot(ParkingSpot spot) {
        spots.put(spot.getId(), spot);
    }

    public ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : spots.values()) {
            if (spot.getAllowedType() == vehicleType && spot.tryOccupy()) {
                return spot;
            }
        }
        return null;
    }
}
