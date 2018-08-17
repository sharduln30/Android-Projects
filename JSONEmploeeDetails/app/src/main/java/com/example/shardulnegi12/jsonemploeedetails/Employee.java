package com.example.shardulnegi12.jsonemploeedetails;

import java.io.Serializable;

public class Employee implements Serializable{

    private int id;
    private String name;
    private String designation;
    private double salary;
    private String[] hobbies;

    public Employee(int id, String name, String designation, double salary, String[] hobbies) {

        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary  = salary;
        this.hobbies = hobbies;

    }

    public int getId() {
        return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return (name + "\n" + designation);
    }
}
