package com.example.hospital;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTask extends AsyncTask<Void, Void, Connection> {

    private final JDCDemo jdcDemo;
    private final OnDatabaseConnectionListener listener;

    public DatabaseConnectionTask(JDCDemo jdcDemo, OnDatabaseConnectionListener listener) {
        this.jdcDemo = jdcDemo;
        this.listener = listener;
    }

    @Override
    protected Connection doInBackground(Void... voids) {
        try {
            return jdcDemo.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Connection connection) {
        if (connection != null) {
            listener.onDatabaseConnected(connection);
        } else {
            listener.onDatabaseConnectionFailed();
        }
    }

    public interface OnDatabaseConnectionListener {
        void onDatabaseConnected(Connection connection);
        void onDatabaseConnectionFailed();
    }
}