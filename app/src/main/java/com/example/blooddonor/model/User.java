package com.example.blooddonor.model;

public class User {
    private String userId;
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String bloodGroup;
    private String userType;
    private double latitude;
    private double longitude;
    private String lastDonationDate;
    private boolean isAvailable;
    private int totalDonations;
    private int points;

    public User() {}

    public User(String userId, String fullName, String email, String phone,
                String password, String bloodGroup, String userType,
                double latitude, double longitude, String lastDonationDate,
                boolean isAvailable, int totalDonations, int points) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.bloodGroup = bloodGroup;
        this.userType = userType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lastDonationDate = lastDonationDate;
        this.isAvailable = isAvailable;
        this.totalDonations = totalDonations;
        this.points = points;
    }

    public String getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPassword() { return password; }
    public String getBloodGroup() { return bloodGroup; }
    public String getUserType() { return userType; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getLastDonationDate() { return lastDonationDate; }
    public boolean isAvailable() { return isAvailable; }
    public int getTotalDonations() { return totalDonations; }
    public int getPoints() { return points; }

    public void setUserId(String userId) { this.userId = userId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPassword(String password) { this.password = password; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public void setUserType(String userType) { this.userType = userType; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setLastDonationDate(String lastDonationDate) { this.lastDonationDate = lastDonationDate; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public void setTotalDonations(int totalDonations) { this.totalDonations = totalDonations; }
    public void setPoints(int points) { this.points = points; }
}
