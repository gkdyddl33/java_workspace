package day1111.member;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import day1111.board.BoardApp;

public class RegistForm extends JPanel{

	JPanel p_container;	// �������� �����̳�
	JTextField t_id;
	JPasswordField t_pass;
	JTextField t_name;
	JButton bt;	
	JButton bt_login;
	
	BoardApp boardApp;
	Connection con;
	
	public RegistForm(BoardApp boardApp) {
		this.boardApp=boardApp;
		// ���� ��Ű����� .com���� ��밡���ϳ� �ٸ� ��Ű���� ��� ���� ������ �ٸ���.
		// BoardApp ���� private�� ������ ���ؼ� get/set�� ��������� �� �����´�.
		con = boardApp.getCon();
		System.out.println("�� �¾ �� "+con);
		
		p_container = new JPanel();
		t_id = new JTextField(30);
		t_pass = new JPasswordField(30);
		t_name = new JTextField(30);
		bt = new JButton("����");
		bt_login = new JButton("�α���");
		
		p_container.setBackground(Color.PINK); 
		p_container.setPreferredSize(new Dimension(400,200));
		p_container.add(t_id);
		p_container.add(t_pass);
		p_container.add(t_name);
		p_container.add(bt);
		p_container.add(bt_login);
		add(p_container);
		
		setVisible(true);
		
		// ���Թ�ư�� ������ ����
		bt.addActionListener((e)->{
			regist();
		});
		
		// �α��� ��ư�� ������ ����
		bt_login.addActionListener((e)->{
			boardApp.setPage(BoardApp.MEMBER_LOGIN);
		});
	}
	// ����Ŭ�� �����Ͽ� insert ����!! -> id,pass,name �Է��� ���� ����
	public void regist() {
		PreparedStatement pstmt = null;
		
		String sql = "insert into board_member(member_id,m_id,m_pass,m_name)";
		sql += " values(seq_board_member.nextval, ?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, new String(t_pass.getPassword())); // �迭 -> ��Ʈ������
			pstmt.setString(3, t_name.getText());
			
			int result = pstmt.executeUpdate();	// �������࿡ ���� ������ ���� ���ڵ���� ��ȯ�ǹǷ�,
																	// ���� 0 �� ��ȯ�ȴٸ� ���з� �Ǵ�!!
			if(result==0) {
				JOptionPane.showMessageDialog(this, "���Կ� �����Ͽ����ϴ�.\n�����ڿ� �������ּ���.");
			}else {
				JOptionPane.showMessageDialog(this, "������ ���ϵ���䢾");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
}