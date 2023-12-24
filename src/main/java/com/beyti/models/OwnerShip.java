package com.beyti.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class OwnerShip extends BaseModel{
    public final int ownerId;
    public final int propertyId;
    public float percent;

    private OwnerShip(int ownerId, int propertyId, float percent) {
        this.ownerId = ownerId;
        this.propertyId = propertyId;
        this.percent = percent;
    }

    public static OwnerShip create(int ownerId, int propertyId, float percentage) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PropertyOwner (ownerId, propertyId, [percent]) VALUES (?, ?, ?)");
            pstmt.setInt(1, ownerId);
            pstmt.setInt(2, propertyId);
            pstmt.setFloat(3, percentage);
            pstmt.executeUpdate();
            return new OwnerShip(ownerId, propertyId, percentage);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(int ownerId, int propertyId, float percentage) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE PropertyOwner SET [percent] = ? WHERE ownerId = ? AND propertyId = ?");
            pstmt.setFloat(1, percentage);
            pstmt.setInt(2, ownerId);
            pstmt.setInt(3, propertyId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(int ownerId, int propertyId) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM PropertyOwner WHERE ownerId = ? AND propertyId = ?");
            pstmt.setInt(1, ownerId);
            pstmt.setInt(2, propertyId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static LinkedList<OwnerShip> get() {
        try {
            LinkedList<OwnerShip> ownerShips = new LinkedList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PropertyOwner");
            while (rs.next()) {
                ownerShips.add(new OwnerShip(rs.getInt("ownerId"), rs.getInt("propertyId"), rs.getFloat("percent")));
            }
            return ownerShips;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getColumns() {
        return new String[] {"ownerId", "propertyId", "percent"};
    }


}
