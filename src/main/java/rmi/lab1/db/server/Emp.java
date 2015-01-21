package main.java.rmi.lab1.db.server;

import java.io.Serializable;

public class Emp implements Serializable{

    
    private int id;
    private String name;
    private String job;
    private int age ;
    
    private Address address;
    
    
    public Emp(int id, String name, String job, int age, Address address) {
	super();
	this.id = id;
	this.name = name;
	this.job = job;
	this.age = age;
	this.address = address;
    }

    public Emp() {
	// TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    
}
