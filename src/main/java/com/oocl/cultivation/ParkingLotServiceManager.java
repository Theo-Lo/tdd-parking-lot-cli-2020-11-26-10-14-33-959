package com.oocl.cultivation;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy{
    List<ParkingBoy> managementList;
    public ParkingLotServiceManager(List<ParkingLot> parkingLotList, List<ParkingBoy> managementList) {
        super(parkingLotList);
        this.managementList = managementList;
    }

    public void addParkingBoy(List<ParkingBoy> managementList) {
    }

    public List<ParkingBoy> getParkingBoysList() {
        return managementList;
    }
}
