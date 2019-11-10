package com.k3.juniordesigndemo.controller;


import android.content.Context;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.provider.Settings.Secure;
import com.google.firebase.database.core.Repo;
import com.k3.juniordesigndemo.model.Report;

import java.util.HashMap;
import java.util.Map;

public class Singleton {
    static Report report = new Report();

    static DatabaseReference db = getDatabase();

    static Context ctxt = null;


    public static void setContext(Context ct) {
        ctxt = ct;
    }

    public static String getDevId() {
        if (ctxt != null) {
            return Secure.getString(ctxt.getContentResolver(),
                    Secure.ANDROID_ID);
        }
        return "0";
    }


    public static void newReport(String address, double lat, double lng) {
        report = new Report(address, lat, lng);
    }




    public static Report getReport() {
        return report;
    }

    public static void submitReport() {

        db.child("reports").child(Integer.toString(report.calcId())).setValue(report, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {
                    report.details = databaseError.getMessage();
                }
            }
        });
    }

    public static DatabaseReference getDatabase() {
        FirebaseDatabase dba = FirebaseDatabase.getInstance();
        dba.setPersistenceEnabled(true);
        return dba.getReference();
    }
}
