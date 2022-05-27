package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB9 {
    private static ConnectDB9 instance9 = new ConnectDB9();

    public static ConnectDB9 getInstance9() {
        return instance9;
    }
    public ConnectDB9() {  }

    // oracle ∞Ë¡§`
    String jdbcUrl = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:XE";
    String userId = "bang";
    String userPw = "1234";

    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;
    
    List list1 = new ArrayList();

    String sql = "";
    String sql2 = "";
    String returns = "a";
   
    public List connectionDB9() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT RE_DATE FROM t_report";
            pstmt = conn.prepareStatement(sql);


            rs = pstmt.executeQuery();  
            
            list1.clear();
            
            while (rs.next()) {
      
            		list1.add(rs.getString(1));
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return list1;
    }
}