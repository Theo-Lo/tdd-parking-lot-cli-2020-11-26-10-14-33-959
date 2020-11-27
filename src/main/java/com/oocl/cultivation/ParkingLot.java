package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final Integer capacity;
    private List<Car> cars;

    public ParkingLot(Integer capacity){
        this.capacity = capacity;
        this.cars = new ArrayList<>();
    }

    public Ticket park(Car car){
        if(this.capacity - cars.size() <= 0){
            return null;
        }
        final Ticket ticket = new Ticket(car.getLicense());
        cars.add(car);
        return new Ticket(ticket.getLicense());
    }

    public Car fetch(Ticket ticket) {
        if(ticket.getUsed()){
            return null;
        }
        for(Car carList:cars){
            if(carList.getLicense().equals(ticket.getLicense())){
                ticket.setUsed();
                cars.remove(carList);
                return carList;
            }
        }
        return null;
    }
}
