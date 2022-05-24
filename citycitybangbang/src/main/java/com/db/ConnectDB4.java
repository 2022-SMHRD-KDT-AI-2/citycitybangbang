package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB4 {
    private static ConnectDB4 instance4 = new ConnectDB4();

    public static ConnectDB4 getInstance4() {
        return instance4;
    }
    public ConnectDB4() {  }

    // oracle °èÁ¤`
    String jdbcUrl = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:XE";
    String userId = "bang";
    String userPw = "1234";

    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;

    String sql = "";
    String sql2 = "";
    String returns = "a";

    public String connectionDB4(String mem_id) {
    	int cnt = -1;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "DELETE FROM t_member WHERE MEM_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mem_id);
            
            cnt = pstmt.executeUpdate();

            if (cnt>=1) {
                returns = "È¸¿ø Å»Åð ¼º°ø!";
            }else {
            	returns = "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns;
    }
}