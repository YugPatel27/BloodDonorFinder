package com.example.blooddonor.model;

public class DonationHistory {
    private String date;
    private String hospitalName;
    private String unitsGiven;
    private String bloodGroup;

    public DonationHistory() {}

    public DonationHistory(String date, String hospitalName, String unitsGiven, String bloodGroup) {
        this.date = date;
        this.hospitalName = hospitalName;
        this.unitsGiven = unitsGiven;
        this.bloodGroup = bloodGroup;
    }

    public String getDate() { return date; }
    public String getHospitalName() { return hospitalName; }
    public String getUnitsGiven() { return unitsGiven; }
    public String getBloodGroup() { return bloodGroup; }

    public void setDate(String date) { this.date = date; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }
    public void setUnitsGiven(String unitsGiven) { this.unitsGiven = unitsGiven; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
}
