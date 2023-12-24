package com.beyti.app;

import com.beyti.utils.DB;
import javax.swing.*;
import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        Connection conn = DB.connect();
        JFrame frame = new LoginFrame(conn, "Login");

    }
}
