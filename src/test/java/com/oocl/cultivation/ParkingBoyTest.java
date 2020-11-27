package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.UnrecognizedParkingTicketException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingBoyTest {
    @Test
    void should_parking_boy_call_parking_lot_park_function_once_when_park_the_car() throws NotEnoughPositionException {
        //given
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
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
        Car car = new Car();
        Ticket ticket = new Ticket();

        //when
        parkingBoy.fetch(ticket);

        //then
        verify(parkingLot, times(1)).fetch(ticket);
    }

    @Test
    void not_enough_position_throw() throws NotEnoughPositionException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());
        final NotEnoughPositionException notEnoughPositionException = assertThrows(NotEnoughPositionException.class, () -> {
            parkingLot.park(new Car());
        });
        assertEquals("Not Enough Position", notEnoughPositionException.getMessage());
    }

    @Test
    void unrecognized_parking_ticket_throw(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        final UnrecognizedParkingTicketException UnrecognizedParkingTicketException = assertThrows(UnrecognizedParkingTicketException.class, () -> {
            parkingLot.fetch(new Ticket());
        });
        assertEquals("Unrecognized parking ticket", UnrecognizedParkingTicketException.getMessage());
    }

    @Test
    void should_return_parking_ticket_for_parking_2_given_parking_lot_1_is_full() throws NotEnoughPositionException, UnrecognizedParkingTicketException {
        //given
        Car car = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(10);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingBoy.park(car);
        //when
        Ticket ticket2 = parkingBoy.park(car2);
        //then
        assertNotNull(ticket2);
        assertEquals(car2, parkingLot2.fetch(ticket2));
    }
}
