package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.ParkingBoyNotInManagementListException;
import com.oocl.cultivation.exceptions.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotServiceManagerTest {
    @Test
    void should_contain_added_parking_boy_from_management_list_when_add_parking_boy_given_service_manager_adds_parking_boy() {
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
    void should_throw_parking_boy_not_in_management_list_when_assign_parking_boy_to_park_a_car_given_he_is_not_in_management_list() {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLotList.add(parkingLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        HashSet<ParkingBoy> parkingBoyList = new HashSet<>();
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoyList);

        //when
        final ParkingBoyNotInManagementListException parkingBoyNotInManagementListException = assertThrows(ParkingBoyNotInManagementListException.class, () -> serviceManager.assignParkingBoyToPark(parkingBoy, car));
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
        Ticket ticket = serviceManager.assignParkingBoyToPark(parkingBoy, car);

        //when
        Car actual = serviceManager.assignParkingBoyToFetch(parkingBoy, ticket);


        //then
        assertEquals(car, actual);
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
        final ParkingBoyNotInManagementListException parkingBoyNotInManagementListException = assertThrows(ParkingBoyNotInManagementListException.class, () -> serviceManager.assignParkingBoyToFetch(parkingBoy2, ticket));
        //then
        assertEquals("ParkingBoy not in management list", parkingBoyNotInManagementListException.getMessage());
    }

    @Test
    void should_return_not_enough_position_exception_when_park_car_given_service_manager_assign_a_parking_boy_to_park() {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        HashSet<ParkingBoy> parkingBoyList = new HashSet<>();
        parkingBoyList.add(superSmartParkingBoy);
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoyList);

        //when
        final NotEnoughPositionException notEnoughPositionException = assertThrows(NotEnoughPositionException.class, () -> serviceManager.assignParkingBoyToPark(superSmartParkingBoy, car));
        //then
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

    @Test
    void should_return_unrecognized_parking_ticket_exception_when_fetch_car_given_used_ticket_and_service_manager_assign_a_parking_boy_to_park() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(9);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        HashSet<ParkingBoy> parkingBoyList = new HashSet<>();
        parkingBoyList.add(superSmartParkingBoy);
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoyList);
        Ticket ticket = serviceManager.assignParkingBoyToPark(superSmartParkingBoy, car);
        serviceManager.assignParkingBoyToFetch(superSmartParkingBoy, ticket);
        //when
        final UnrecognizedParkingTicketException UnrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> serviceManager.assignParkingBoyToFetch(superSmartParkingBoy, ticket));
        //then
        assertEquals("Unrecognized parking ticket", UnrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_unrecognized_parking_ticket_exception_when_fetch_car_given_invalid_ticket_and_service_manager_assign_a_parking_boy_to_park() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(9);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        HashSet<ParkingBoy> parkingBoyList = new HashSet<>();
        parkingBoyList.add(superSmartParkingBoy);
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(new ArrayList<>(), parkingBoyList);
        Ticket ticket = new Ticket();
        //when
        final UnrecognizedParkingTicketException UnrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> serviceManager.assignParkingBoyToFetch(superSmartParkingBoy, ticket));
        //then
        assertEquals("Unrecognized parking ticket", UnrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_a_ticket_when_park_given_a_car_and_service_manager() throws Exception {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(parkingLotList, new HashSet<>());

        //when
        Ticket ticket = serviceManager.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_a_car_when_fetch_given_a_ticket_and_service_manager() throws Exception {
        //given
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager serviceManager = new ParkingLotServiceManager(parkingLotList, new HashSet<>());
        Ticket ticket = serviceManager.park(car);

        //when
        Car actual = serviceManager.fetch(ticket);


        //then
        assertEquals(car, actual);
    }
}
