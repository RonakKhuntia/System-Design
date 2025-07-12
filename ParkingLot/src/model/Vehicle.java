package model;

import enums.VehicleType;

public abstract class Vehicle {
    private final String licensePlateNumber;

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    private final VehicleType vehicleType;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    protected Vehicle(String licensePlateNumber, VehicleType vehicleType) {
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
    }
}
