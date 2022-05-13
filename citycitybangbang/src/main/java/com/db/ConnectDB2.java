package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB2 {
    private static ConnectDB2 instance2 = new ConnectDB2();

    public static ConnectDB2 getInstance2() {
        return instance2;
    }
    public ConnectDB2() {  }

    // oracle ����`
    String jdbcUrl = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:XE";
    String userId = "bang";
    String userPw = "1234";

    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;

    String sql = "";
    String sql2 = "";
    String returns = "a";

    public String connectionDB2(String mem_id) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT MEM_ID FROM t_member WHERE MEM_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mem_id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                returns = "�̹� �����ϴ� ���̵� �Դϴ�.";
            }else {
            	returns = "���� ������ ���̵� �Դϴ�.";
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