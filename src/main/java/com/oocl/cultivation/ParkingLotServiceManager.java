package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.ParkingBoyNotInManagementListException;
import com.oocl.cultivation.exceptions.UnrecognizedParkingTicketException;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy{
    HashSet<ParkingBoy> managementList;
    public ParkingLotServiceManager(List<ParkingLot> parkingLotList, HashSet<ParkingBoy> managementList) {
        super(parkingLotList);
        this.managementList = managementList;
    }

    public void addParkingBoy(HashSet<ParkingBoy> managementList) {
        this.managementList.addAll(managementList);
    }

    public HashSet<ParkingBoy> getParkingBoysList() {
        return managementList;
    }

    public Ticket assignParkingBoyToPark(ParkingBoy parkingBoy, Car car) throws Exception {
        if(!managementList.contains(parkingBoy)) throw new ParkingBoyNotInManagementListException();
        return parkingBoy.park(car);
    }

    public Car assignParkingBoyToFetch(ParkingBoy parkingBoy, Ticket ticket) throws UnrecognizedParkingTicketException {
        return null;
    }
}
