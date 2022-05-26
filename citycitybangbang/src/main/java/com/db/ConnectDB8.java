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
    
    List<ReportVO> list = new ArrayList();


    String sql = "";
    String sql2 = "";
    String returns = "a";
   
    public List<ReportVO> connectionDB8(String ACC_DATE, String ACC_PLACE, String re_complete) {
    	
    	
    	StringBuffer sb = new StringBuffer();
    	if (ACC_DATE.substring(5, 6).equals("0")) {
 
    		sb.append(ACC_DATE);
			sb.deleteCharAt(5);
		}
    	String sb2= sb.toString();
    	
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            if(.equals("전체")) {

            //'?%'   '%'||?||'%'
            	sql = "SELECT MEM_ID, ACC_DATE, ACC_PLACE, RE_COMPLETE FROM t_report WHERE acc_date LIKE ?||'%' AND ACC_PLACE LIKE '%'||?||'%'";
            	pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, sb2);
            	pstmt.setString(2, ACC_PLACE);
            } else {
            	sql = "SELECT MEM_ID, ACC_DATE, ACC_PLACE, RE_COMPLETE FROM t_report WHERE acc_date LIKE ?||'%' AND ACC_PLACE LIKE '%'||?||'%' AND RE_COMPLETE = ? ";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, sb2);
                pstmt.setString(2, ACC_PLACE);
                pstmt.setString(3, re_complete);
            }

            rs = pstmt.executeQuery();  
            list.clear();

            
            while (rs.next()) {
            	
            	String mem_id = rs.getString(1);
            	String acc_date = rs.getString(2);
        		String acc_place = rs.getString(3);
        		char re_complete = rs.getchar(4);
            		
            	ReportVO rvo = new ReportVO();
            	
            	rvo.setMem_id(mem_id);
        		rvo.setAcc_date(ACC_DATE);
        		rvo.setAcc_place(ACC_PLACE);
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