package com.oocl.cultivation;

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
}
