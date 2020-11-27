package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_a_parking_ticket_when_park_the_car_given_a_car_and_parking_lot_with_available_capacity() throws NotEnoughPositionException {
        //given
        Car car = new Car("car1");
        ParkingLot parkingLot = new ParkingLot(10);

        //when
        final Ticket ticket = parkingLot.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_only_one_car_parked_when_park_multiple_cars_given_multiple_car_and_parking_lot_with_1_capacity() throws NotEnoughPositionException {
        //given
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        ParkingLot parkingLot = new ParkingLot(1);

        //when
        final Ticket ticket1 = parkingLot.park(car1);
        //then
        assertNotNull(ticket1);

        //when
        final NotEnoughPositionException notEnoughPositionException = assertThrows(NotEnoughPositionException.class, () -> {
            parkingLot.park(car2);
        });
        //then
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

    @Test
    void should_be_parked_when_park_multiple_cars_given_multiple_car_and_parking_lot_with_available_capacity() throws NotEnoughPositionException {
        //given
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        ParkingLot parkingLot = new ParkingLot(2);

        //when
        final Ticket ticket1 = parkingLot.park(car1);
        final Ticket ticket2 = parkingLot.park(car2);

        //then
        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertNotEquals(ticket1,ticket2);
    }

    @Test
    void should_return_car_when_fetch_car_given_valid_parking_ticket_parking_lot_that_parked_the_car() throws NotEnoughPositionException {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("car1");
        Ticket ticket = parkingLot.park(car);

        //when
        final Car actual = parkingLot.fetch(ticket);

        //then
        assertEquals(car, actual);
    }

    @Test
    void should_return_null_when_fetch_car_given_used_parking_ticket() throws NotEnoughPositionException {
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("car1");
        Ticket ticket = parkingLot.park(car);

        //when
        final Car firstFetch = parkingLot.fetch(ticket);
        final Car secondFetch = parkingLot.fetch(ticket);

        //then
        assertNotNull(firstFetch);
        assertNull(secondFetch);
    }

    @Test
    void should_return_null_when_fetch_car_given_invalid_parking_ticket(){
        //given
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket ticket = new Ticket("Wrong License");

        //when
        final Car wrongFetch = parkingLot.fetch(ticket);

        //then
        assertNull(wrongFetch);
    }
}
