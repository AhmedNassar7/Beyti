package com.beyti.models;

import java.sql.*;
import java.util.LinkedList;

public class SalesOffice extends BaseModel{
    public int number;
    public String location;
    public int managerId;
    private SalesOffice(int number, String location, int managerId) {
        this.number = number;
        this.location = location;
        this.managerId = managerId;
    }

    public static SalesOffice create(String location, int managerId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SalesOffice (location, managerId) VALUES (?, ?)");
            pstmt.setString(1, location);
            pstmt.setInt(2, managerId);
            pstmt.executeUpdate();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM SalesOffice ORDER BY number DESC");
            if(rs.next()) {
                return new SalesOffice(rs.getInt("number"), rs.getString("location"), rs.getInt("managerId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }
    public static SalesOffice create(String location) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SalesOffice (location) VALUES (?)");
            pstmt.setString(1, location);
            pstmt.executeUpdate();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM SalesOffice ORDER BY number DESC");
            if(rs.next()) {
                return new SalesOffice(rs.getInt("number"), rs.getString("location"), rs.getInt("managerId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }
    public static void update(int number, String location, int managerId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE SalesOffice SET location = ?, managerId = ? WHERE number = ?");
            pstmt.setString(1, location);
            pstmt.setInt(2, managerId);
            pstmt.setInt(3, number);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static void update(int number, int managerId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE SalesOffice SET managerId = ? WHERE number = ?");
            pstmt.setInt(1, managerId);
            pstmt.setInt(2, number);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static void delete(int number) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM SalesOffice WHERE number = ?");
            pstmt.setInt(1, number);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static LinkedList<SalesOffice> get() {
        LinkedList<SalesOffice> officesList =  new LinkedList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SalesOffice");
            while(rs.next()){
                int number = rs.getInt("number");
                String location = rs.getString("location");
                SalesOffice office = new SalesOffice(number, location, rs.getInt("managerId"));
                officesList.add(office);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return officesList;
    }

    public static String[] getColumns(){
        return new String[]{"number", "location", "managerId"};
    }
}
