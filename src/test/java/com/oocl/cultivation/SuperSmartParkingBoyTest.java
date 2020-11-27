package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperSmartParkingBoyTest {
    @Test
    void should_park_in_parking_lot_2_when_park_given_parking_lot_2_empty_ratio_is_higher_than_parking_lot_1_while_capacity_number_is_smaller_than_parking_lot_1() throws Exception {
        //given
        // parkingLot 1 has 3/5 ratio, 2 capacity
        // parkingLot 2 has 1/2 ratio, 1 capacity
        // Should choose parkingLot2 even with lower capacity
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot1.park(car1);
        parkingLot1.park(car2);
        parkingLot1.park(car3);
        parkingLot2.park(car4);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        //when
        Ticket ticket = superSmartParkingBoy.park(car5);
        //then
        assertNotNull(ticket);
        assertEquals(car5, parkingLot2.fetch(ticket));
    }

    @Test
    void should_return_not_enough_position_exception_when_park_car_given_multiple_parking_lot_are_full() throws NotEnoughPositionException, UnrecognizedParkingTicketException {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        //when
        final NotEnoughPositionException notEnoughPositionException = assertThrows(NotEnoughPositionException.class, () -> {
            superSmartParkingBoy.park(car);
        });
        //then
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

    @Test
    void should_return_unrecognized_parking_ticket_exception_when_fetch_car_given_used_parking_ticket_and_multiple_parking_lot() throws NotEnoughPositionException, UnrecognizedParkingTicketException {
        //given
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(9);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        Ticket ticket = superSmartParkingBoy.park(car);
        //when
        superSmartParkingBoy.fetch(ticket);
        final UnrecognizedParkingTicketException UnrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            superSmartParkingBoy.fetch(ticket);
        });
        //then
        assertEquals("Unrecognized parking ticket", UnrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_unrecognized_parking_ticket_exception_when_fetch_car_given_invalid_parking_ticket_and_multiple_parking_lot() throws NotEnoughPositionException, UnrecognizedParkingTicketException {
        //given
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(9);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        Ticket ticket = new Ticket();
        //when
        final UnrecognizedParkingTicketException UnrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            superSmartParkingBoy.fetch(ticket);
        });
        //then
        assertEquals("Unrecognized parking ticket", UnrecognizedParkingTicketException.getMessage());
    }
}
