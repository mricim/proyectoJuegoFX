package main.java.dataBase;

import java.sql.*;

public class Mysql {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://armegis.mricim.tk:3306/armegis?serverTimezone=UTC";

    //  Database credentials
    static final String USER = "user";
    static final String PASS = "user12345!Ab";

    public static void conection() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT id, name, email, password, date_register, last_conexion FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String date_register = rs.getString("date_register");
                String last_conexion = rs.getString("last_conexion");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + name);
                System.out.print(", email: " + email);
                System.out.print(", First: " + password);
                System.out.println(", Last: " + date_register);
                System.out.println(", Last: " + last_conexion);
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main

    public static PersonSQL emailToUser(String email) {
        return conex("SELECT id, name, email, password, date_register, last_conexion FROM users WHERE email = '"+email+"'");
    }
    public static PersonSQL conex(String sql) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String date_register = rs.getString("date_register");
                String last_conexion = rs.getString("last_conexion");
                return new PersonSQL(id,name,email,password,date_register,last_conexion);
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return null;
    }
}//end JDBCExample
