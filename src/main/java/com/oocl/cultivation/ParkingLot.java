package com.oocl.cultivation;

import com.oocl.cultivation.exceptions.NotEnoughPositionException;
import com.oocl.cultivation.exceptions.UnrecognizedParkingTicketException;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Integer capacity;
    private final Map<Ticket, Car> carTicketHashMap;

    public ParkingLot(Integer capacity){
        this.capacity = capacity;
        this.carTicketHashMap = new HashMap<>();
    }

    public Ticket park(Car car) throws NotEnoughPositionException {
        if(this.carTicketHashMap.size() >= capacity){
            throw new NotEnoughPositionException();
        }
        Ticket ticket = new Ticket();
        this.carTicketHashMap.put(ticket,car);
        return ticket;
    }

    public Car fetch(Ticket ticket) throws UnrecognizedParkingTicketException {
        Car car = this.carTicketHashMap.get(ticket);
        this.carTicketHashMap.remove(ticket);
        if(car==null){
            throw new UnrecognizedParkingTicketException();
        }
        return car;
    }

    public Integer getEmptyPosition(){
        return capacity - this.carTicketHashMap.size();
    }
}
