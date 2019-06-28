package com.example.sqlite;

public class EmployeeDetailsClass {
    int ID;
    String name;
    String position;
    String date;

    public EmployeeDetailsClass(int ID, String name, String position, String date) {
        this.ID = ID;
        this.name = name;
        this.position = position;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
