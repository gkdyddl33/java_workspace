package day1105.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
	자바에서 데이터베이스를 연동하는 기술을 가리켜 JDBC
	(Java DataBase Connectivity) 라고 하며, java.sql 패키지에 api가 지원된다.
	데이터베이스를 연동 업무의 순서
	1) DB기종에 알맞는 드라이버를 로드(oracle, mysql,mssql..각각 jar가 필요함)
	2) 접속
	3) 원하는 쿼리 수행
	4) 접속해제(특히 스트림 및 DB는 사용 후 반드시 해제하자)	
 */
public class OracleTest {
	
	public static void main(String[] args) {
		// 사용하고자하는 드라이버 클래스를 등록!
		// (1)클래스 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
			
			// 2) 접속
			String url = "jdbc:oracle:thin@localhost:1521:XE";
			String user = "user1104";
			String password = "user1104";
			
			// 2) 접속 시도후, 접속이 성공되면 Connection 이라는 인터페이스의 인스턴스를 반환해줌!
			// Connection에는 성공한 접속 정보가 들어있다.
			// 접속 성공여부는 Connection 객체의 null 여부로 판단한다.
			Connection con = DriverManager.getConnection(url,user,password);
			if(con==null) {
				System.out.println("접속실패");
			}else {
				System.out.println("접속성공");
				// 쿼리문을 수행하는 객체는 preparedstatement 인터페이스이다
				String sql = "insert into member(member_id,name,age,phone)";
				sql += " values(seq_member.nextval,'scott',36,'010";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				// DML 쿼리 실행시 이 메서드 사용함
				// 쿼리 수행 후 성공여부를 판단하기 위해서는 executeUpdate() 메서드의 반환형을 이용한다.
				// 반환형은 int값이며, 쿼리문 수행에 의해 반영된 레코드의 수를 반환한다.
				// 따라서 insert 의 경우 반환값이 1, update, delete는 몇건이 영향받았는지 알수는 없다.
				// 0이면 무조건 실패로 간주한다.
				int result = pstmt.executeUpdate();
				if(result != 1) {
					System.out.println("입력 실패");
				}else {
					System.out.println("입력성공");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("지정한 드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
