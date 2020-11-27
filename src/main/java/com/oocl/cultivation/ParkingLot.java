package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Integer capacity;
    private List<Car> cars;
    private final Map<Ticket, Car> carTicketHashMap;

    public ParkingLot(Integer capacity){
        this.capacity = capacity;
//        this.cars = new ArrayList<>();
        this.carTicketHashMap = new HashMap<>();
    }

    public Ticket park(Car car){
//        if(capacity - this.cars.size() == 0){
//            return null;
//        }
        if(this.carTicketHashMap.size() >= capacity){
            return null;
        }
        Ticket ticket = new Ticket(car.getLicense());
//        cars.add(car);
        this.carTicketHashMap.put(ticket,car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car car = this.carTicketHashMap.get(ticket);
        this.carTicketHashMap.remove(ticket);
        return car;
//        if(ticket.getUsed()){
//            return null;
//        }
//        for(Car carList:cars){
//            if(carList.getLicense().equals(ticket.getLicense())){
//                ticket.setUsed();
//                cars.remove(carList);
//                return carList;
//            }
//        }
//        return null;
    }
}
