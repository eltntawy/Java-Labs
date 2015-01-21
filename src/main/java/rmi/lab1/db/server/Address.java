package main.java.rmi.lab1.db.server;

import java.io.Serializable;

public class Address implements Serializable{

    private String street;
    private int streetNumber;
    
    
    
    public Address(String street, int streetNumber) {
	super();
	this.street = street;
	this.streetNumber = streetNumber;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
    
    
}
