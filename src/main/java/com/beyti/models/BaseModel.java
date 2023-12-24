package com.beyti.models;

import java.sql.Connection;

public class BaseModel {
    protected static Connection conn;

    public static void setConnection(Connection conn) {
        BaseModel.conn = conn;
    }
}