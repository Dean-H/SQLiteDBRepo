package com.example.dean.sqlitetutorial.person;

@SuppressWarnings("all")
public class Person {
    private int ID;
    private String name;
    private String phoneNumber;
    private String email;

    public Person(){
        this(0, null, null, null);
    }
    public Person(int ID, String name, String phoneNumber, String email){
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return this.name;
    }
}

