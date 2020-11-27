package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotServiceManagerTest {
    @Test
    void should_contain_added_parking_boy_from_management_list_when_add_parking_boy_given_service_manager_adds_parking_boy(){
        //given
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(new ArrayList<>(), new ArrayList<>());

        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>());
        ParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>());
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ArrayList<>());

        List<ParkingBoy> parkingBoyList = new ArrayList<>();

        parkingBoyList.add(parkingBoy);
        parkingBoyList.add(smartParkingBoy);
        parkingBoyList.add(superSmartParkingBoy);

        //when
        serviceManager.addParkingBoy(parkingBoyList);

        //then
        assertTrue(serviceManager.getParkingBoysList().contains(parkingBoy));
        assertTrue(serviceManager.getParkingBoysList().contains(smartParkingBoy));
        assertTrue(serviceManager.getParkingBoysList().contains(superSmartParkingBoy));
    }
}
