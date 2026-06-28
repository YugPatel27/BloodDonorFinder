package com.example.blooddonor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.blooddonor.model.DonationHistory;

import java.util.ArrayList;
import java.util.List;

public class DonationHistoryDao {

    private final DatabaseHelper dbHelper;

    public DonationHistoryDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insertHistory(String userUid, DonationHistory history) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_HIST_USER_UID,    userUid);
        values.put(DatabaseHelper.COL_HIST_DATE,        history.getDate());
        values.put(DatabaseHelper.COL_HIST_HOSPITAL,    history.getHospitalName());
        values.put(DatabaseHelper.COL_HIST_UNITS,       history.getUnitsGiven());
        values.put(DatabaseHelper.COL_HIST_BLOOD_GROUP, history.getBloodGroup());

        long result = db.insert(DatabaseHelper.TABLE_DONATION_HISTORY, null, values);
        db.close();
        return result;
    }

    public List<DonationHistory> getHistoryByUser(String userUid) {
        List<DonationHistory> historyList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_DONATION_HISTORY,
                null,
                DatabaseHelper.COL_HIST_USER_UID + " = ?",
                new String[]{userUid},
                null, null,
                DatabaseHelper.COL_HIST_ID + " DESC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                historyList.add(cursorToHistory(cursor));
            }
            cursor.close();
        }

        db.close();
        return historyList;
    }

    private DonationHistory cursorToHistory(Cursor cursor) {
        DonationHistory history = new DonationHistory();
        history.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_HIST_DATE)));
        history.setHospitalName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_HIST_HOSPITAL)));
        history.setUnitsGiven(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_HIST_UNITS)));
        history.setBloodGroup(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_HIST_BLOOD_GROUP)));
        return history;
    }
}
