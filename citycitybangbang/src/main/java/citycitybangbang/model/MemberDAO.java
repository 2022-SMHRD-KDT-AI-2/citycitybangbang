package citycitybangbang.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import citycitybangbang.model.MemberVO;


public class MemberDAO  {
	
	private Connection conn; // Connection : �����ͺ��̽��� �����ϰ� ���ִ� �ϳ��� ��ü 

	
	private static SqlSessionFactory sqlSessionFactory;
	// �ʱ�ȭ��(���α׷� ����� �ѹ��� ����Ǵ� �κ�)
	static {
		try {
		String resource = "citycitybangbang/model/config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberVO checkLogin(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		MemberVO mbVO = session.selectOne("checkLogin", vo);
		return mbVO;
	}
	
	public void getdb() {
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:XE";
	         String dbid = "bang";
	         String dbpw = "1234";
	         
	         conn = DriverManager.getConnection(url, dbid, dbpw);
	         
	         if(conn != null) {
	            System.out.println("���Ἲ��");
	         }
	         
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
}