package com.example.blooddonor.model;

public class BloodRequest {
    private String requestId;
    private String requesterName;
    private String requesterPhone;
    private String bloodGroup;
    private String hospitalName;
    private String requiredDateTime;
    private String priority;
    private double latitude;
    private double longitude;
    private String postedDate;

    public BloodRequest() {}

    public BloodRequest(String requestId, String requesterName, String requesterPhone,
                        String bloodGroup, String hospitalName, String requiredDateTime,
                        String priority, double latitude, double longitude, String postedDate) {
        this.requestId = requestId;
        this.requesterName = requesterName;
        this.requesterPhone = requesterPhone;
        this.bloodGroup = bloodGroup;
        this.hospitalName = hospitalName;
        this.requiredDateTime = requiredDateTime;
        this.priority = priority;
        this.latitude = latitude;
        this.longitude = longitude;
        this.postedDate = postedDate;
    }

    public String getRequestId() { return requestId; }
    public String getRequesterName() { return requesterName; }
    public String getRequesterPhone() { return requesterPhone; }
    public String getBloodGroup() { return bloodGroup; }
    public String getHospitalName() { return hospitalName; }
    public String getRequiredDateTime() { return requiredDateTime; }
    public String getPriority() { return priority; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getPostedDate() { return postedDate; }

    public void setRequestId(String requestId) { this.requestId = requestId; }
    public void setRequesterName(String requesterName) { this.requesterName = requesterName; }
    public void setRequesterPhone(String requesterPhone) { this.requesterPhone = requesterPhone; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }
    public void setRequiredDateTime(String requiredDateTime) { this.requiredDateTime = requiredDateTime; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setPostedDate(String postedDate) { this.postedDate = postedDate; }
}
