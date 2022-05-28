package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;
import org.apache.catalina.tribes.util.Arrays;
import citycitybangbang.model.ReportVO;

public class ConnectDB10 {
    private static ConnectDB10 instance10 = new ConnectDB10();

    
    public static ConnectDB10 getInstance10() {
        return instance10;
    }
    public ConnectDB10() {  }

    // oracle 拌沥`
    String jdbcUrl = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:XE";
    String userId = "bang";
    String userPw = "1234";

    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;
    
    List<ReportVO> list = new ArrayList();


    String sql = "";
    String sql2 = "";
    String returns = "a";
   
    public List<ReportVO> connectionDB10(String check, String num) {    	
    	
 
    	
    		
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
        	if("贸府".equals(check)) {
        		sql = "UPDATE T_REPORT SET RE_COMPLETE='Y' WHERE RE_NUM=?";
            	pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, num);
        	}else if("固贸府".equals(check)) {
        		sql = "UPDATE T_REPORT SET RE_COMPLETE='N' WHERE RE_NUM=?";
            	pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, num);
        	}                       
            
            rs = pstmt.executeQuery();  
 
            
            		
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return list ;
    }

}