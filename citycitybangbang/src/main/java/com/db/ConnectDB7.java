package com.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.tribes.util.Arrays;

import citycitybangbang.model.ReportVO;

public class ConnectDB7 {
    private static ConnectDB7 instance7 = new ConnectDB7();

    
    public static ConnectDB7 getInstance7() {
        return instance7;
    }
    public ConnectDB7() {  }

    // oracle ∞Ë¡§`
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
   
    public List<ReportVO> connectionDB7(String ACC_DATE) {
    	
    	System.out.println(ACC_DATE.substring(5, 6));
    	
    	
    	StringBuffer sb = new StringBuffer();
    	if (ACC_DATE.substring(5,6).equals("0")) {
    		if(ACC_DATE.substring(7,8).equals("0")) {	
    			sb.append(ACC_DATE);
    			sb.deleteCharAt(5);
    			sb.deleteCharAt(6);
    		}else if(ACC_DATE.substring(8,9).equals("0")) {
    			sb.append(ACC_DATE);
    			sb.deleteCharAt(5);
    			sb.deleteCharAt(7);
    		}else {
    			sb.append(ACC_DATE);
    			sb.deleteCharAt(5);
    		}
		}

    	System.out.println("ConnetDB7 : "+ sb);
    	
    	
    	String sb2 = sb.toString();
    	
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            //'?%'   '%'||?||'%'
            sql = "SELECT ACC_DATE, ACC_PLACE, RE_COMPLETE FROM t_report WHERE acc_date LIKE ?||'%'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sb2);

            rs = pstmt.executeQuery();  
            list.clear();

            
            while (rs.next()) {
            		
            		//list.add(rs.getString(1)  + "." + rs.getString(2) + "." + rs.getString(3) + "\n");
            		String acc_date = rs.getString(1);
            		String acc_place = rs.getString(2);
            		char re_complete = rs.getString(3).charAt(0);
            		
            		ReportVO rvo = new ReportVO();
            		
            		rvo.setAcc_date(acc_date);
            		rvo.setAcc_place(acc_place);
            		rvo.setRe_complete(re_complete);
            		
            		list.add(rvo);
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