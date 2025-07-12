package model;

import enums.VehicleType;

public class Truck extends Vehicle {
    public Truck(String licensePlateNumber) {
        super(licensePlateNumber, VehicleType.TRUCK);
    }
}
