package com.beyti.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Owner extends BaseModel {
    public final int id;
    public String firstName;
    public String lastName;

    private Owner(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public static Owner create(String firstName, String lastName) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Owner (firstName, lastName) VALUES (?, ?)");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.executeUpdate();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM Owner ORDER BY id DESC");
            if(rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                return new Owner(id, firstName, lastName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static void update(int id, String firstName, String lastName) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Owner SET firstName = ?, lastName = ? WHERE id = ?");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Owner WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static LinkedList<Owner> get() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Owner");
            ResultSet rs = pstmt.executeQuery();
            LinkedList<Owner> ownersList = new LinkedList<>();
            while(rs.next()) {
                ownersList.add(new Owner(rs.getInt("id"), rs.getString("firstName"), rs.getString("LastName")));
            }
            return ownersList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String[] getColumns() {
        return new String[] {"id", "firstName", "lastName"};
    }
}
