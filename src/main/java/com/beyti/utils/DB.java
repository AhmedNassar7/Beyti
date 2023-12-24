package com.beyti.utils;

//import org.apache.ibatis.jdbc.ScriptRunner;

//import java.io.*;
import com.beyti.models.Employee;
import com.beyti.models.BaseModel;
import com.beyti.models.SalesOffice;

import java.sql.*;

public class DB {
    public static Connection connect() {
        Connection conn;
//        Statement stmt;
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-RNQD9CI;Database=beytiDB;" +
                    "IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;");
            System.out.println("Connection Established!");
            BaseModel.setConnection(conn);
            int salesOfficeId = 1;
            int managerId = 1;
            if(SalesOffice.get().size() == 0){
                SalesOffice salesOffice = SalesOffice.create("Cairo");
                assert salesOffice != null;
                salesOfficeId = salesOffice.number;
            }
            if(Employee.get().size() == 0) {
                Employee admin = Employee.create(salesOfficeId, "Ahmed", "Hatem", "admin", "admin");
                assert admin != null;
                managerId = admin.id;
            }
            SalesOffice.update(salesOfficeId, managerId);


        } catch (SQLException e) {

//            if (e.getErrorCode() == 4060) {
//                try {
//                    conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-RNQD9CI;" +
//                            "IntegratedSecurity=true;encrypt=true;trustServerCertificate=true;");
//                    stmt = conn.createStatement();
//                    stmt.execute("CREATE DATABASE beytiDB");
//                    migrate(conn);
//                    System.out.println("Migrated & Connection Established!");
//
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            } else {
//            }
            e.printStackTrace();
            throw new RuntimeException(e);

        }
        return conn;
    }

//    private static void migrate(Connection conn) {
//        try {
//
//            File sqlScript = new File("sql/structure.sql").getAbsoluteFile();
//            if (sqlScript.exists()) {
////                SQLScriptRunner runner = new SQLScriptRunner(conn, true, true);
//                ScriptRunner sr = new ScriptRunner(conn);
//                try {
////                    runner.runScript(new BufferedReader(new FileReader("sql/structure.sql")));
//                    Reader reader = new BufferedReader(new FileReader("sql/structure.sql"));
//                    sr.runScript(reader);
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            } else throw new FileNotFoundException("Database structure SQL script not found");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
}


