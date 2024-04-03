package com.example.hospital;

import android.content.Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDCDemo {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/patients";
    private static final String USER = "root";
    private static final String PASS = "";

    private Context context;

    public JDCDemo(Context context) {
        this.context = context;
    }

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return A Connection object representing the database connection.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the MySQL JDBC driver class is not found.
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return connection;
    }

    /**
     * Closes the given database connection.
     *
     * @param connection The Connection object to be closed.
     */
    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}