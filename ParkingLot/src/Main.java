import enums.PaymentMode;
import enums.PricingStrategyType;
import enums.VehicleType;
import factory.PricingStrategyFactory;
import factory.VehicleFactory;
import model.*;
import service.ParkingLot;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance();
        EntryGate entryGate = new EntryGate("EG1");
        ExitGate exitGate = new ExitGate("XG1");

        lot.setPricingStrategy(PricingStrategyFactory.get(PricingStrategyType.valueOf("EVENT_BASED")));

        ParkingFloor floor1 = new ParkingFloor("Floor1");
        floor1.addSpot(new ParkingSpot("F1S1", VehicleType.BIKE));
        floor1.addSpot(new ParkingSpot("F1S2", VehicleType.CAR));
        floor1.addSpot(new ParkingSpot("F1S3", VehicleType.TRUCK));
        floor1.addSpot(new ParkingSpot("F1S4", VehicleType.BIKE));
        lot.addFloor(floor1);

        System.out.println("----------------------------------------");

        Vehicle car = VehicleFactory.create("KA01AB1234", VehicleType.CAR);

        LocalDateTime entryTime = LocalDateTime.now();
        Ticket ticket = entryGate.parkVehicle(car, entryTime);

        System.out.println("----------------------------------------");

        lot.printStatus();

        System.out.println("----------------------------------------");

        LocalDateTime exitTime = LocalDateTime.now();
        exitGate.unparkVehicle(ticket.getTicketId(), exitTime, PaymentMode.CARD);

        System.out.println("----------------------------------------");

//        Vehicle bike1 = VehicleFactory.create("KA01AB1234", VehicleType.BIKE);
//        Vehicle bike2 = VehicleFactory.create("KA01AB5678", VehicleType.BIKE);
//        LocalDateTime entryTime = LocalDateTime.now();
//
//        Thread t1 = new Thread(() -> entryGate.parkVehicle(bike1, entryTime));
//        Thread t2 = new Thread(() -> entryGate.parkVehicle(bike2, entryTime));
//
//        t1.start();
//        t2.start();
    }
}