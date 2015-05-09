package com.apathon.ebills;

import android.app.Application;
import android.content.Context;

import java.io.IOException;
import java.sql.SQLException;

import electronics.payu.com.electronics.db.DataBaseHelper;

/**
 * Created by suraj on 12/2/2014.
 */
public class App extends Application {

    private static App instance;
    private static DataBaseHelper db;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        db = new DataBaseHelper(getContext());
        try {
            doDbCall(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static final Context getContext() {
        return getInstance().getApplicationContext();
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onTerminate() {
        db.close();
        super.onTerminate();
    }

    public void doDbCall(DataBaseHelper db) throws SQLException {
        try {
            db.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            db.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    public static DataBaseHelper getDb() {
        return db;
    }



}
