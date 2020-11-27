package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Ticket park(Car car) throws NotEnoughPositionException {
        ParkingLot parkingLot = parkingLotList.stream()
                .max(Comparator.comparing(ParkingLot::getEmptyPosition))
                .get();
        return parkingLot.park(car);
    }
}
