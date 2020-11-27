package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.UnrecognizedParkingTicketException;

public class ParkingBoy {
    private final ParkingLot parkingLot;
    public ParkingBoy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }
    public Ticket park(Car car) throws NotEnoughPositionException {
        return this.parkingLot.park(car);
    }
    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        return parkingLot.fetch(ticket);
    }
}
