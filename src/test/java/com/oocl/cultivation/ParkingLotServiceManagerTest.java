package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.ParkingBoyNotInManagementListException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotServiceManagerTest {
    @Test
    void should_contain_added_parking_boy_from_management_list_when_add_parking_boy_given_service_manager_adds_parking_boy(){
        //given
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(new ArrayList<>(), new HashSet<>());

        ParkingBoy parkingBoy = new ParkingBoy(new ArrayList<>());
        ParkingBoy smartParkingBoy = new SmartParkingBoy(new ArrayList<>());
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ArrayList<>());

        HashSet<ParkingBoy> parkingBoyList = new HashSet<>();

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

    @Test
    void should_return_a_ticket_when_assign_parking_boy_to_park_a_car_given_he_is_assigned_to_that_parking_lot() throws Exception {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLotList.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        HashSet<ParkingBoy> parkingBoyList = new HashSet<>();
        parkingBoyList.add(parkingBoy);
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoyList);

        //when
        Ticket ticket = serviceManager.assignParkingBoyToPark(parkingBoy, car);

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_throw_parking_boy_not_in_management_list_when_assign_parking_boy_to_park_a_car_given_he_is_not_in_management_list() throws Exception {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLotList.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        HashSet<ParkingBoy> parkingBoyList = new HashSet<>();
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoyList);

        //when
        final ParkingBoyNotInManagementListException parkingBoyNotInManagementListException = assertThrows(ParkingBoyNotInManagementListException.class, () -> {
            serviceManager.assignParkingBoyToPark(parkingBoy, car);
        });
        //then
        assertEquals("ParkingBoy not in management list", parkingBoyNotInManagementListException.getMessage());
    }

    @Test
    void should_return_a_car_when_assign_parking_boy_to_fetch_given_he_is_assigned_to_that_parking_lot() throws Exception {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLotList.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        HashSet<ParkingBoy> parkingBoyList = new HashSet<>();
        parkingBoyList.add(parkingBoy);
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoyList);

        //when
        Ticket ticket = serviceManager.assignParkingBoyToPark(parkingBoy, car);

        //then
        assertEquals(car, serviceManager.assignParkingBoyToFetch(parkingBoy, ticket));
    }

    @Test
    void should_throw_parking_boy_not_in_management_list_when_assign_parking_boy_to_fetch_a_car_given_he_is_not_in_management_list() throws Exception {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLotList.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        ParkingBoy parkingBoy2 = new ParkingBoy(new ArrayList<>());
        HashSet<ParkingBoy> parkingBoyList = new HashSet<>();
        parkingBoyList.add(parkingBoy);
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoyList);
        Ticket ticket = serviceManager.assignParkingBoyToPark(parkingBoy, car);

        //when
        final ParkingBoyNotInManagementListException parkingBoyNotInManagementListException = assertThrows(ParkingBoyNotInManagementListException.class, () -> {
            serviceManager.assignParkingBoyToFetch(parkingBoy2, ticket);
        });
        //then
        assertEquals("ParkingBoy not in management list", parkingBoyNotInManagementListException.getMessage());
    }

}
