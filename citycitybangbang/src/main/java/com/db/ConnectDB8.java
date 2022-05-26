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

public class ConnectDB8 {
    private static ConnectDB8 instance8 = new ConnectDB8();

    
    public static ConnectDB8 getInstance8() {
        return instance8;
    }
    public ConnectDB8() {  }

    // oracle 계정`
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
   
    public List connectionDB7(String ACC_DATE1, String ACC_DATE2, String area, String complete ) {
    	
    	
    	
    	StringBuffer sb = new StringBuffer();
    	if (ACC_DATE1.substring(5, 6).equals("0")) {
 
    		sb.append(ACC_DATE1);
			sb.deleteCharAt(5);
		}
    	String sb2= sb.toString();
    	
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            if(complete.equals("전체")) {

            //'?%'   '%'||?||'%'
            sql = "SELECT ACC_DATE, ACC_PLACE, RE_COMPLETE FROM t_report WHERE acc_date LIKE ?||'%' AND ACC_PLACE LIKE '%'||?||'%'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sb2);
            pstmt.setString(2, area);
            } else {
            	sql = "SELECT ACC_DATE, ACC_PLACE, RE_COMPLETE FROM t_report WHERE acc_date LIKE ?||'%' AND ACC_PLACE LIKE '%'||?||'%' AND RE_COMPLETE = ? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, sb2);
                pstmt.setString(2, area);
                pstmt.setString(3, complete);
            }

            rs = pstmt.executeQuery();  
            list.clear();

            
            while (rs.next()) {
            		
            		//list.add(rs.getString(1)  + "." + rs.getString(2) + "." + rs.getString(3) + "\n");
            		char c = rs.getString(3).charAt(0);
            		list.add(rs.getString(1)+","+rs.getString(2)+","+c+"\n");
            }
            
            
            		
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