package com.beyti.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Property extends BaseModel {
    public int id;
    public String address;
    public String city;
    public String state;
    public String postCode;
    public int salesOfficeNumber;

    private Property(int id, String address, String city, String state, String postCode, int salesOfficeNumber) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
        this.salesOfficeNumber = salesOfficeNumber;
    }

    public static Property create(String address, String city, String state, String postCode, int salesOfficeNumber) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Property (address, city, state, postCode, officeNumber) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, address);
            pstmt.setString(2, city);
            pstmt.setString(3, state);
            pstmt.setString(4, postCode);
            pstmt.setInt(5, salesOfficeNumber);
            pstmt.executeUpdate();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM Property ORDER BY id DESC");
            if(rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                return new Property(id, address, city, state, postCode, salesOfficeNumber);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static void update(int id, String address, String city, String state, String postCode, int salesOfficeNumber) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Property SET address = ?, city = ?, state = ?," +
                    " postCode = ?, officeNumber = ? WHERE id = ?");
            pstmt.setString(1, address);
            pstmt.setString(2, city);
            pstmt.setString(3, state);
            pstmt.setString(4, postCode);
            pstmt.setInt(5, salesOfficeNumber);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void delete(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Property WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static LinkedList<Property> get() {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Property");
            ResultSet rs = pstmt.executeQuery();
            LinkedList<Property> properties = new LinkedList<Property>();
            while(rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String postCode = rs.getString("postCode");
                int salesOfficeNumber = Integer.parseInt(rs.getString("officeNumber"));
                properties.add(new Property(id, address, city, state, postCode, salesOfficeNumber));
            }
            return properties;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getColumns() {
        return new String[] {"id", "address", "city", "state", "postCode", "SalesOfficeNumber"};
    }

}
