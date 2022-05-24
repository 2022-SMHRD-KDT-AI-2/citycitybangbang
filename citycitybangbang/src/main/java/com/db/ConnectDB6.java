package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB6 {
    private static ConnectDB6 instance6 = new ConnectDB6();

    public static ConnectDB6 getInstance6() {
        return instance6;
    }
    public ConnectDB6() {  }

    // oracle ∞Ë¡§`
    String jdbcUrl = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:XE";
    String userId = "bang";
    String userPw = "1234";

    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;
    
    List list = new ArrayList();

    String sql = "";
    String sql2 = "";
    String returns = "a";
   
    public List connectionDB6(String mem_id) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT ACC_DATE, ACC_PLACE, RE_COMPLETE FROM t_report WHERE MEM_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mem_id);

            rs = pstmt.executeQuery();  
            
            list.clear();
            
            while (rs.next()) {
      
            		list.add(rs.getString(1)  + ";" + rs.getString(2) + ";" + rs.getString(3) + "\n");
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return list;
    }
}