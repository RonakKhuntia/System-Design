package model;

import enums.VehicleType;

public class Car extends Vehicle {
    public Car(String licensePlateNumber) {
        super(licensePlateNumber, VehicleType.CAR);
    }
}
