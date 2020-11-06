package day1105.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
	�ڹٿ��� �����ͺ��̽��� �����ϴ� ����� ������ JDBC
	(Java DataBase Connectivity) ��� �ϸ�, java.sql ��Ű���� api�� �����ȴ�.
	�����ͺ��̽��� ���� ������ ����
	1) DB������ �˸´� ����̹��� �ε�(oracle, mysql,mssql..���� jar�� �ʿ���)
	2) ����
	3) ���ϴ� ���� ����
	4) ��������(Ư�� ��Ʈ�� �� DB�� ��� �� �ݵ�� ��������)	
 */
public class OracleTest {
	
	public static void main(String[] args) {
		// ����ϰ����ϴ� ����̹� Ŭ������ ���!
		// (1)Ŭ���� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
			
			// 2) ����
			String url = "jdbc:oracle:thin@localhost:1521:XE";
			String user = "user1104";
			String password = "user1104";
			
			// 2) ���� �õ���, ������ �����Ǹ� Connection �̶�� �������̽��� �ν��Ͻ��� ��ȯ����!
			// Connection���� ������ ���� ������ ����ִ�.
			// ���� �������δ� Connection ��ü�� null ���η� �Ǵ��Ѵ�.
			Connection con = DriverManager.getConnection(url,user,password);
			if(con==null) {
				System.out.println("���ӽ���");
			}else {
				System.out.println("���Ӽ���");
				// �������� �����ϴ� ��ü�� preparedstatement �������̽��̴�
				String sql = "insert into member(member_id,name,age,phone)";
				sql += " values(seq_member.nextval,'scott',36,'010";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				// DML ���� ����� �� �޼��� �����
				// ���� ���� �� �������θ� �Ǵ��ϱ� ���ؼ��� executeUpdate() �޼����� ��ȯ���� �̿��Ѵ�.
				// ��ȯ���� int���̸�, ������ ���࿡ ���� �ݿ��� ���ڵ��� ���� ��ȯ�Ѵ�.
				// ���� insert �� ��� ��ȯ���� 1, update, delete�� ����� ����޾Ҵ��� �˼��� ����.
				// 0�̸� ������ ���з� �����Ѵ�.
				int result = pstmt.executeUpdate();
				if(result != 1) {
					System.out.println("�Է� ����");
				}else {
					System.out.println("�Է¼���");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("������ ����̹��� ã�� �� �����ϴ�.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
