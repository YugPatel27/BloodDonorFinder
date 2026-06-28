package com.example.blooddonor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.blooddonor.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final DatabaseHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insertUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_USER_UID,          user.getUserId());
        values.put(DatabaseHelper.COL_USER_NAME,         user.getFullName());
        values.put(DatabaseHelper.COL_USER_EMAIL,        user.getEmail());
        values.put(DatabaseHelper.COL_USER_PHONE,        user.getPhone());
        values.put(DatabaseHelper.COL_USER_PASSWORD,     user.getPassword());
        values.put(DatabaseHelper.COL_USER_BLOOD_GROUP,  user.getBloodGroup());
        values.put(DatabaseHelper.COL_USER_TYPE,         user.getUserType());
        values.put(DatabaseHelper.COL_USER_LATITUDE,     user.getLatitude());
        values.put(DatabaseHelper.COL_USER_LONGITUDE,    user.getLongitude());
        values.put(DatabaseHelper.COL_USER_LAST_DONATION, user.getLastDonationDate());
        values.put(DatabaseHelper.COL_USER_AVAILABLE,    user.isAvailable() ? 1 : 0);
        values.put(DatabaseHelper.COL_USER_TOTAL_DON,    user.getTotalDonations());
        values.put(DatabaseHelper.COL_USER_POINTS,       user.getPoints());

        long result = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();
        return result;
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                null,
                DatabaseHelper.COL_USER_EMAIL + " = ?",
                new String[]{email},
                null, null, null
        );

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = cursorToUser(cursor);
        }

        if (cursor != null) cursor.close();
        db.close();
        return user;
    }

    public List<User> getAllDonors() {
        List<User> donors = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                null,
                DatabaseHelper.COL_USER_TYPE + " = ?",
                new String[]{"Donor"},
                null, null,
                DatabaseHelper.COL_USER_TOTAL_DON + " DESC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                donors.add(cursorToUser(cursor));
            }
            cursor.close();
        }

        db.close();
        return donors;
    }

    public List<User> getDonorsByBloodGroup(String bloodGroup) {
        if (bloodGroup == null || bloodGroup.equals("All")) {
            return getAllDonors();
        }

        List<User> donors = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                null,
                DatabaseHelper.COL_USER_TYPE + " = ? AND "
                        + DatabaseHelper.COL_USER_BLOOD_GROUP + " = ?",
                new String[]{"Donor", bloodGroup},
                null, null, null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                donors.add(cursorToUser(cursor));
            }
            cursor.close();
        }

        db.close();
        return donors;
    }

    public void updateAvailability(String uid, boolean isAvailable) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_USER_AVAILABLE, isAvailable ? 1 : 0);
        db.update(DatabaseHelper.TABLE_USERS, values,
                DatabaseHelper.COL_USER_UID + " = ?", new String[]{uid});
        db.close();
    }

    public void updatePoints(String uid, int points) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_USER_POINTS, points);
        db.update(DatabaseHelper.TABLE_USERS, values,
                DatabaseHelper.COL_USER_UID + " = ?", new String[]{uid});
        db.close();
    }

    public void updateTotalDonations(String uid, int total, String lastDate) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_USER_TOTAL_DON, total);
        values.put(DatabaseHelper.COL_USER_LAST_DONATION, lastDate);
        db.update(DatabaseHelper.TABLE_USERS, values,
                DatabaseHelper.COL_USER_UID + " = ?", new String[]{uid});
        db.close();
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setUserId(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_UID)));
        user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_NAME)));
        user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_EMAIL)));
        user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_PHONE)));
        user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_PASSWORD)));
        user.setBloodGroup(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_BLOOD_GROUP)));
        user.setUserType(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_TYPE)));
        user.setLatitude(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_LATITUDE)));
        user.setLongitude(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_LONGITUDE)));
        user.setLastDonationDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_LAST_DONATION)));
        user.setAvailable(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_AVAILABLE)) == 1);
        user.setTotalDonations(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_TOTAL_DON)));
        user.setPoints(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_POINTS)));
        return user;
    }
}
