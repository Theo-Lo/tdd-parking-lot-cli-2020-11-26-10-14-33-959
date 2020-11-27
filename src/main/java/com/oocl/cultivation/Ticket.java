package com.oocl.cultivation;

public class Ticket {
    private String license;
    private Boolean used;

    public Ticket(String license){
        this.license = license;
        this.used = false;
    }

    public String getLicense() {
        return license;
    }
    public void setUsed(){
        this.used = true;
    }
    public Boolean getUsed(){
        return this.used;
    }
}
