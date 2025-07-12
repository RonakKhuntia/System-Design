package service;

import enums.PaymentMode;
import enums.PricingStrategyType;
import factory.PaymentStrategyFactory;
import factory.PricingStrategyFactory;
import model.ParkingFloor;
import model.ParkingSpot;
import model.Ticket;
import model.Vehicle;
import strategy.PaymentStrategy;
import strategy.PricingStrategy;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private static final ParkingLot INSTANCE = new ParkingLot();

    private final Map<String, ParkingFloor> floors = new HashMap<>();
    private final Map<String, Ticket> activeTickets = new HashMap<>();
    private PricingStrategy pricingStrategy;

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    private ParkingLot() {
        this.pricingStrategy = PricingStrategyFactory.get(PricingStrategyType.TIME_BASED);
    }

    public static ParkingLot getInstance() {
        return INSTANCE;
    }

    public void addFloor(ParkingFloor floor) {
        floors.put(floor.getId(), floor);
    }

    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
        for (ParkingFloor floor : floors.values()) {
            ParkingSpot spot = floor.findAvailableSpot(vehicle.getVehicleType());
            if (spot != null) {
                String ticketId = UUID.randomUUID().toString();
                Ticket ticket = new Ticket();
                ticket.setTicketId(ticketId);
                ticket.setEntryTime(entryTime);
                ticket.setVehicle(vehicle);
                ticket.setFloorId(floor.getId());
                ticket.setSpotId(spot.getId());
                activeTickets.put(ticketId, ticket);
                System.out.println("Vehicle parked. Ticket: " + ticketId);
                return ticket;
            }
        }
        System.out.println("No spot available for vehicle type : " + vehicle.getVehicleType());
        return null;
    }

    public void unparkVehicle(String ticketId, LocalDateTime exitTime, PaymentMode paymentMode) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Invalid ticket Id " + ticketId);
            return;
        }

        double fee = pricingStrategy.calculateFee(
                ticket.getVehicle().getVehicleType(),
                ticket.getEntryTime(),
                exitTime
        );

        PaymentStrategy strategy = PaymentStrategyFactory.get(paymentMode);
        PaymentProcessor processor = new PaymentProcessor(strategy);
        boolean paid = processor.pay(ticket, fee);

        if (!paid) {
            System.out.println("Vehicle cannot exit. Payment Unsuccessful.");
            return;
        }

        ParkingSpot spot = floors.get(ticket.getFloorId()).getSpots().get(ticket.getSpotId());
        spot.vacate();
        activeTickets.remove(ticketId);

        System.out.println("Vehicle exited. Fee charged: Rs." + fee);
    }

    public void printStatus() {
        for (ParkingFloor floor : floors.values()) {
            System.out.println("Floor: Floor" + floor.getId());
            for (ParkingSpot spot : floor.getSpots().values()) {
                System.out.println("Spot: " + spot.getId() + " [" + spot.getAllowedType() + "] " + " - " + spot.isOccupied());
            }
        }
    }

}
