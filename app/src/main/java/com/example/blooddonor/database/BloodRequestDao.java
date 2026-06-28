package com.example.blooddonor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.blooddonor.model.BloodRequest;

import java.util.ArrayList;
import java.util.List;

public class BloodRequestDao {

    private final DatabaseHelper dbHelper;

    public BloodRequestDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insertRequest(BloodRequest request, String postedByUid) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        String uid = "REQ" + System.currentTimeMillis();
        values.put(DatabaseHelper.COL_REQ_UID,         uid);
        values.put(DatabaseHelper.COL_REQ_NAME,        request.getRequesterName());
        values.put(DatabaseHelper.COL_REQ_PHONE,       request.getRequesterPhone());
        values.put(DatabaseHelper.COL_REQ_BLOOD_GROUP, request.getBloodGroup());
        values.put(DatabaseHelper.COL_REQ_HOSPITAL,    request.getHospitalName());
        values.put(DatabaseHelper.COL_REQ_DATETIME,    request.getRequiredDateTime());
        values.put(DatabaseHelper.COL_REQ_PRIORITY,    request.getPriority());
        values.put(DatabaseHelper.COL_REQ_LATITUDE,    request.getLatitude());
        values.put(DatabaseHelper.COL_REQ_LONGITUDE,   request.getLongitude());
        values.put(DatabaseHelper.COL_REQ_POSTED_DATE, request.getPostedDate());
        values.put(DatabaseHelper.COL_REQ_POSTED_BY,   postedByUid);

        long result = db.insert(DatabaseHelper.TABLE_BLOOD_REQUESTS, null, values);
        db.close();
        return result;
    }

    public List<BloodRequest> getAllRequests() {
        List<BloodRequest> requests = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_BLOOD_REQUESTS,
                null,
                null,
                null,
                null, null,
                DatabaseHelper.COL_REQ_ID + " DESC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                requests.add(cursorToRequest(cursor));
            }
            cursor.close();
        }

        db.close();
        return requests;
    }

    public List<BloodRequest> getRequestsByBloodGroup(String bloodGroup) {
        List<BloodRequest> requests = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_BLOOD_REQUESTS,
                null,
                DatabaseHelper.COL_REQ_BLOOD_GROUP + " = ?",
                new String[]{bloodGroup},
                null, null,
                DatabaseHelper.COL_REQ_ID + " DESC"
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                requests.add(cursorToRequest(cursor));
            }
            cursor.close();
        }

        db.close();
        return requests;
    }

    private BloodRequest cursorToRequest(Cursor cursor) {
        BloodRequest req = new BloodRequest();
        req.setRequestId(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_REQ_UID)));
        req.setRequesterName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_REQ_NAME)));
        req.setRequesterPhone(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_REQ_PHONE)));
        req.setBloodGroup(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_REQ_BLOOD_GROUP)));
        req.setHospitalName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_REQ_HOSPITAL)));
        req.setRequiredDateTime(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_REQ_DATETIME)));
        req.setPriority(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_REQ_PRIORITY)));
        req.setLatitude(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_REQ_LATITUDE)));
        req.setLongitude(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_REQ_LONGITUDE)));
        req.setPostedDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_REQ_POSTED_DATE)));
        return req;
    }
}
