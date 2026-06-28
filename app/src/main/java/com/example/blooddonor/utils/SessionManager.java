package com.example.blooddonor.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME          = "BloodDonorSession";
    private static final String KEY_IS_LOGGED_IN   = "isLoggedIn";
    private static final String KEY_USER_ID        = "userId";
    private static final String KEY_NAME           = "userName";
    private static final String KEY_EMAIL          = "userEmail";
    private static final String KEY_PHONE          = "userPhone";
    private static final String KEY_BLOOD_GROUP    = "bloodGroup";
    private static final String KEY_USER_TYPE      = "userType";
    private static final String KEY_AVAILABLE      = "isAvailable";
    private static final String KEY_LAST_DONATION  = "lastDonation";
    private static final String KEY_TOTAL_DONATIONS = "totalDonations";
    private static final String KEY_POINTS         = "points";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        pref   = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void saveSession(String userId, String name, String email, String phone,
                            String bloodGroup, String userType, boolean isAvailable,
                            String lastDonation, int totalDonations, int points) {
        editor.putBoolean(KEY_IS_LOGGED_IN,    true);
        editor.putString(KEY_USER_ID,          userId);
        editor.putString(KEY_NAME,             name);
        editor.putString(KEY_EMAIL,            email);
        editor.putString(KEY_PHONE,            phone);
        editor.putString(KEY_BLOOD_GROUP,      bloodGroup);
        editor.putString(KEY_USER_TYPE,        userType);
        editor.putBoolean(KEY_AVAILABLE,       isAvailable);
        editor.putString(KEY_LAST_DONATION,    lastDonation);
        editor.putInt(KEY_TOTAL_DONATIONS,     totalDonations);
        editor.putInt(KEY_POINTS,              points);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void clearSession() {
        editor.clear();
        editor.apply();
    }

    public String getUserId()       { return pref.getString(KEY_USER_ID, ""); }
    public String getUserName()     { return pref.getString(KEY_NAME, ""); }
    public String getUserEmail()    { return pref.getString(KEY_EMAIL, ""); }
    public String getUserPhone()    { return pref.getString(KEY_PHONE, ""); }
    public String getBloodGroup()   { return pref.getString(KEY_BLOOD_GROUP, ""); }
    public String getUserType()     { return pref.getString(KEY_USER_TYPE, "Donor"); }
    public boolean isAvailable()    { return pref.getBoolean(KEY_AVAILABLE, true); }
    public String getLastDonation() { return pref.getString(KEY_LAST_DONATION, ""); }
    public int getTotalDonations()  { return pref.getInt(KEY_TOTAL_DONATIONS, 0); }
    public int getPoints()          { return pref.getInt(KEY_POINTS, 0); }

    public void updateAvailability(boolean isAvailable) {
        editor.putBoolean(KEY_AVAILABLE, isAvailable);
        editor.apply();
    }

    public void updatePoints(int points) {
        editor.putInt(KEY_POINTS, points);
        editor.apply();
    }

    public void updateDonations(int total) {
        editor.putInt(KEY_TOTAL_DONATIONS, total);
        editor.apply();
    }
}
