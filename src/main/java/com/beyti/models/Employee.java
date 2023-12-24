package com.beyti.models;

import java.sql.*;
import java.util.LinkedList;

public class Employee extends BaseModel {
    public int id;
    public int officeNumber;
    public String firstName;
    public String lastName;
    public String username;
    private String password;

    public boolean isManager() throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM SalesOffice WHERE managerId = ?");
        pstmt.setInt(1, this.id);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }

    private Employee(Connection conn, int id, int officeNumber, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.officeNumber = officeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Employee create(int officeNumber, String firstName, String lastName, String username, String password) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Employee (officeNumber, firstName, lastName, username, password) VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, officeNumber);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, username);
            pstmt.setString(5, password);
            pstmt.executeUpdate();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT TOP 1 * FROM Employee ORDER BY id DESC");
            if(rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                return new Employee(conn, id, officeNumber, firstName, lastName, username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public static void update(int id, int officeNumber, String firstName, String lastName, String username) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE Employee SET officeNumber = ?, firstName = ?, lastName = ?, username = ? WHERE id = ?");
            pstmt.setInt(1, officeNumber);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, username);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(int id) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Employee WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static LinkedList<Employee> get() {
        LinkedList<Employee> employeesList = new LinkedList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");
            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                int officeNumber = rs.getInt("officeNumber");
                Employee emp = new Employee(conn, id, officeNumber, firstName, lastName, username, password);
                System.out.println(emp);
                employeesList.add(emp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeesList;
    }
    public static String[] getColumns(){
        return new String[]{"id", "officeNumber", "firstName", "lastName", "username"};
    }
}
