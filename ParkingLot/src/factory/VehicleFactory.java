package factory;

import enums.VehicleType;
import model.Bike;
import model.Car;
import model.Truck;
import model.Vehicle;

public class VehicleFactory {
    public static Vehicle create(String licensePlateNumber, VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> new Car(licensePlateNumber);
            case BIKE -> new Bike(licensePlateNumber);
            case TRUCK -> new Truck(licensePlateNumber);
        };
    }
}
