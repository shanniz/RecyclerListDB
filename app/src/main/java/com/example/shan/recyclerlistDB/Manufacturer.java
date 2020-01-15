package com.example.shan.recyclerlistDB;

public class Manufacturer {
    private int id;
    private String name;
    private String phone;
    private String address;

    public Manufacturer(){
        this.id = 0;
        this.name = this.phone = this.address = "";
    }

    public Manufacturer(String name){
        //this.id = id;
        this.name = name;
        this.phone = "";
        this.address = "";
    }
    public Manufacturer(String name, String phone){
        //this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = "";
    }
    public Manufacturer(int id, String name, String phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = "";
    }
    public Manufacturer(int id, String name, String phone, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public String getAddress(){
        return address;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }


}
