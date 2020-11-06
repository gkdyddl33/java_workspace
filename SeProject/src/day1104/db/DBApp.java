package day1104.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/*
	�����ͺ��̽� ���� ����
	1) ����̹� �ε�(�ڹٿ��� db�� ���������ִ� ���̺귯�� jar)
		ex. node.js���� ���� ����� �ֵ�..
	2) ������ �õ�
	3) ������ ����
	4) db ������ ����� ��
 */
public class DBApp {
	public static void main(String[] args) {
		// �޼����� ���������� �ݵ�� �ʱ�ȭ�ؾ� ��..(�������ó�� �ڵ����� ���ִ� ���� ����)
		Connection con=null;		
		PreparedStatement pstmt = null;
		
		try {
			//1) ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver"); //�ε��ϰ���� Ŭ���� ��θ� String���� ���
			System.out.println("����̹� �ε� ����");
			
			//2) ���� �õ� 
			String str="jdbc:oracle:thin:@localhost:1521:XE";//���� ���ڿ�..
			
			//������ �����Ǹ�, con�� ��ȯ�ǹǷ�, ���� null�̸� ���ӽ���
			con=DriverManager.getConnection(str , "user1104", "user1104");
			if(con==null) {
				System.out.println("���ӽ��Ф̤�");
			}else {
				System.out.println("���Ӽ���");
				// �������� �����ϴ� ��ü�� preparedstatement �������̽��̴�
				String sql = "insert into member(member_id,name,age,phone)";
				sql += " values(seq_member.ne xtval,'adamas',34,'010')";
				
				// ���� ������ �غ�!!
				pstmt = con.prepareStatement(sql);
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
			System.out.println("������ ����̹��� ã�� �� �����ϴ�");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// db������ ���Ǿ��� ��� ��ü�� �ݾƾ� �Ѵ�.
			if(pstmt !=null) {// �����Ҷ��� �ݴ´�..
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}
			if(con !=null) {// �����Ҷ��� �ݴ´�..
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}			
			}			
		}
	}
}

