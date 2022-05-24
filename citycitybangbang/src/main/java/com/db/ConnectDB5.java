package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB5 {
    private static ConnectDB5 instance5 = new ConnectDB5();

    public static ConnectDB5 getInstance5() {
        return instance5;
    }
    public ConnectDB5() {  }

    // oracle 계정`
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

    public String connectionDB5(String mem_id, String acc_date, String acc_place, String re_comment, String image_file) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "INSERT INTO t_report(mem_id, acc_date, acc_place, re_comment, image_file) VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mem_id);
            pstmt.setString(2, acc_date);
            pstmt.setString(3, acc_place);
            pstmt.setString(4, re_comment);
            pstmt.setString(5, image_file);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                returns = "신고 완료!";
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