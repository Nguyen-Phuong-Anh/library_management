
package com.mycompany.demo1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class DBConnection {
    public static String url = "jdbc:postgresql://localhost:5432/smsdb";
    public static String user = "postgres";
    public static String password = "superpower";
    
    public static Connection getCon() {
        Connection cnt;
        cnt = null;
        try {
            cnt = DriverManager.getConnection(url, user, password);
            return cnt;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}