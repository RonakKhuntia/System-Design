package model;

import enums.VehicleType;

public class Bike extends Vehicle {
    public Bike(String licensePlateNumber) {
        super(licensePlateNumber, VehicleType.BIKE);
    }
}
