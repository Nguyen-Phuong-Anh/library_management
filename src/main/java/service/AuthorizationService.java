package service;

import com.mycompany.demo1.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizationService {

    public AuthorizationService() {
    }
    
    public String logIn(String username, String pwd) throws SQLException {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        String role = null;
        
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM accounts");
            sql.append(" WHERE username = ? AND password = ?");
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, username);
                stm.setString(2, pwd);
                res = stm.executeQuery();
                
                if (res.next()) {
                    role = res.getString("role");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(cnt != null) {
                    cnt.close();
                }
                if(stm != null) {
                    stm.close();
                }
                if(res != null) {
                    res.close();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            
        }
        return role;
    }
}
