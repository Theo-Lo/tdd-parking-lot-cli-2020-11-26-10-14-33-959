package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingBoyTest {
    @Test
    void should_parking_boy_call_parking_lot_park_function_once_when_park_the_car() throws NotEnoughPositionException {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("car1");
        //when
        parkingBoy.park(car);
        //then
        verify(parkingLot, times(1)).park(car);

    }

    @Test
    void should_parking_boy_call_parking_lot_fetch_function_once_when_fetch_a_car() throws UnrecognizedParkingTicketException {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car("car1");
        Ticket ticket = new Ticket(car.getLicense());

        //when
        parkingBoy.fetch(ticket);

        //then
        verify(parkingLot, times(1)).fetch(ticket);
    }

    @Test
    void not_enough_position_throw() throws NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("car1"));
        final NotEnoughPositionException notEnoughPositionException = assertThrows(NotEnoughPositionException.class, () -> {
            parkingLot.park(new Car("car2"));
        });
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

    @Test
    void unrecognized_parking_ticket_throw(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("car1");
        final UnrecognizedParkingTicketException UnrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingLot.fetch(new Ticket(car.getLicense()));
        });
        assertEquals("Unrecognized parking ticket", UnrecognizedParkingTicketException.getMessage());
    }
}
