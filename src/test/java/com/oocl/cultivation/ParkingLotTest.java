package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void should_return_a_parking_ticket_when_park_the_car_given_a_car_and_parking_lot_with_available_capacity(){
        //given
        Car car = new Car("car1");
        ParkingLot parkingLot = new ParkingLot(10);

        //when
        final Ticket ticket = parkingLot.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_only_one_car_parked_when_park_multiple_cars_given_multiple_car_and_parking_lot_with_1_capacity(){
        //given
        Car car1 = new Car("car1");
        Car car2 = new Car("car2");
        ParkingLot parkingLot = new ParkingLot(1);

        //when
        final Ticket ticket1 = parkingLot.park(car1);
        final Ticket ticket2 = parkingLot.park(car2);

        //then
        assertNotNull(ticket1);
        assertNull(ticket2);
    }
}
