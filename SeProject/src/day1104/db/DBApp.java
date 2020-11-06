package day1104.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/*
	데이터베이스 연동 순서
	1) 드라이버 로드(자바에서 db를 연동시켜주는 라이브러리 jar)
		ex. node.js에서 전용 모듈이 있듯..
	2) 접속을 시도
	3) 쿼리문 수행
	4) db 접속을 끊어야 함
 */
public class DBApp {
	public static void main(String[] args) {
		// 메서드의 지역변수라서 반드시 초기화해야 함..(멤버변수처럼 자동으로 해주는 일이 없다)
		Connection con=null;		
		PreparedStatement pstmt = null;
		
		try {
			//1) 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver"); //로드하고싶은 클래스 경로를 String으로 명시
			System.out.println("드라이버 로드 성공");
			
			//2) 접속 시도 
			String str="jdbc:oracle:thin:@localhost:1521:XE";//접속 문자열..
			
			//접속이 성공되면, con이 반환되므로, 따라서 null이면 접속실패
			con=DriverManager.getConnection(str , "user1104", "user1104");
			if(con==null) {
				System.out.println("접속실패ㅜㅜ");
			}else {
				System.out.println("접속성공");
				// 쿼리문을 수행하는 객체는 preparedstatement 인터페이스이다
				String sql = "insert into member(member_id,name,age,phone)";
				sql += " values(seq_member.ne xtval,'adamas',34,'010')";
				
				// 쿼리 수행할 준비!!
				pstmt = con.prepareStatement(sql);
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
			System.out.println("지정한 드라이버를 찾을 수 없습니다");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// db연동에 사용되었던 모든 객체는 닫아야 한다.
			if(pstmt !=null) {// 존재할때만 닫는다..
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
			if(con !=null) {// 존재할때만 닫는다..
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}			
		}
	}
}

