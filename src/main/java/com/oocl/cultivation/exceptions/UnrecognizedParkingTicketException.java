package com.oocl.cultivation.exceptions;

public class UnrecognizedParkingTicketException extends Exception{
    public UnrecognizedParkingTicketException(){
        super("Not Enough Position");
    }
}
