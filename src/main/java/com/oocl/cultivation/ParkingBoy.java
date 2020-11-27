package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    ParkingLot parkingLot;
    List<ParkingLot> parkingLotList = new ArrayList<>();
    public ParkingBoy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
        this.parkingLotList.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        for (ParkingLot parkingLot : parkingLotList) {
            try {
                return parkingLot.park(car);
            } catch (NotEnoughPositionException notEnoughPositionException) {
                // Do nothing
            }
        }
        throw new NotEnoughPositionException();
    }

    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        for (ParkingLot parkingLot : parkingLotList) {
            try {
                return parkingLot.fetch(ticket);
            } catch (UnrecognizedParkingTicketException unrecognizedParkingTicketException) {
                // Do nothing
            }
        }
        throw new UnrecognizedParkingTicketException();
    }
}
