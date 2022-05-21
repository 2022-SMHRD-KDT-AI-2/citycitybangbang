package citycitybangbang.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import citycitybangbang.model.ReportVO;

public class ReportDAO  {
	
private Connection conn; // Connection : 데이터베이스에 접근하게 해주는 하나의 객체 

	
	private static SqlSessionFactory sqlSessionFactory;
	// 초기화블럭(프로그램 실행시 한번만 실행되는 부분)
	static {
		try {
		String resource = "citycitybangbang/model/config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ReportVO> reportList (String date) {
		SqlSession session = sqlSessionFactory.openSession();
		List<ReportVO> rptlist = session.selectList("reportList", date);
		session.close();		
		return rptlist;
	}
	
}