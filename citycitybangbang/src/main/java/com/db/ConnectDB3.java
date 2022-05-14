package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB3 {
    private static ConnectDB3 instance3 = new ConnectDB3();

    public static ConnectDB3 getInstance3() {
        return instance3;
    }
    public ConnectDB3() {  }

    // oracle 계정`
    String jdbcUrl = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:XE";
    String userId = "bang";
    String userPw = "1234";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    String sql = "";
    String returns = "a";

    public String connectionDB3(String mem_id, String mem_pwd) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT MEM_ID FROM t_member WHERE MEM_ID = ? AND MEM_PWD = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mem_id);
            pstmt.setString(2, mem_pwd);

            rs = pstmt.executeQuery();
            if (rs.next()) {     
            	returns = "로그인 성공!";
            } else {
            	returns = "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns;
    }
}