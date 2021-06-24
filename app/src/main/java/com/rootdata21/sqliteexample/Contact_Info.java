package com.rootdata21.sqliteexample;

public class Contact_Info {

    private int id;
    String name,number;

    Contact_Info(){

    }

    Contact_Info(int id){
        this.id = id;
    }

    Contact_Info(String name,String number){
        this.name = name;
        this.number = number;
    }

    Contact_Info(int id,String name,String number){
        this.id = id;
        this.name = name;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
