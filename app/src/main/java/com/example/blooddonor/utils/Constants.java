package com.example.blooddonor.utils;

public class Constants {

    public static final String[] BLOOD_GROUPS = {
        "All", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    };

    public static final String[] BLOOD_GROUPS_ONLY = {
        "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    };

    public static final String PRIORITY_NORMAL   = "Normal";
    public static final String PRIORITY_URGENT   = "Urgent";
    public static final String PRIORITY_CRITICAL = "Critical";

    public static final String TYPE_DONOR     = "Donor";
    public static final String TYPE_REQUESTER = "Requester";

    public static final int[] DISTANCE_OPTIONS = {5, 10, 20, 50, 100};

    public static final int POINTS_PER_DONATION = 100;
    public static final int BADGE_FIRST_DROP   = 1;
    public static final int BADGE_LIFE_SAVER   = 3;
    public static final int BADGE_HERO_DONOR   = 5;
    public static final int BADGE_CHAMPION     = 10;
    public static final int BADGE_LEGEND       = 25;

    public static final String DEMO_OTP = "123456";

    public static final String PREF_REQUESTS     = "blood_requests";
    public static final String KEY_REQUESTS_JSON = "requests_json";

    public static final int DONATION_GAP_DAYS = 90;
}
