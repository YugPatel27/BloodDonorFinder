package com.example.blooddonor.model;

public class Hospital {
    private String name;
    private String address;
    private String phone;
    private String stock;
    private boolean isVerified;

    public Hospital() {}

    public Hospital(String name, String address, String phone, String stock, boolean isVerified) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.stock = stock;
        this.isVerified = isVerified;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getStock() { return stock; }
    public boolean isVerified() { return isVerified; }

    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setStock(String stock) { this.stock = stock; }
    public void setVerified(boolean verified) { isVerified = verified; }
}
