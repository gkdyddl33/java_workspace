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

	JPanel p_container;	// 묶기위한 컨테이너
	JTextField t_id;
	JPasswordField t_pass;
	JTextField t_name;
	JButton bt;	
	JButton bt_login;
	
	BoardApp boardApp;
	Connection con;
	
	public RegistForm(BoardApp boardApp) {
		this.boardApp=boardApp;
		// 같은 패키지라면 .com으로 사용가능하나 다른 패키지일 경우 변수 접근이 다르다.
		// BoardApp 에서 private로 변수를 정해서 get/set을 사용함으로 써 가져온다.
		con = boardApp.getCon();
		System.out.println("나 태어날 때 "+con);
		
		p_container = new JPanel();
		t_id = new JTextField(30);
		t_pass = new JPasswordField(30);
		t_name = new JTextField(30);
		bt = new JButton("가입");
		bt_login = new JButton("로그인");
		
		p_container.setBackground(Color.PINK); 
		p_container.setPreferredSize(new Dimension(400,200));
		p_container.add(t_id);
		p_container.add(t_pass);
		p_container.add(t_name);
		p_container.add(bt);
		p_container.add(bt_login);
		add(p_container);
		
		setVisible(true);
		
		// 가입버튼과 리스너 연결
		bt.addActionListener((e)->{
			regist();
		});
		
		// 로그인 버튼과 리스너 연결
		bt_login.addActionListener((e)->{
			boardApp.setPage(BoardApp.MEMBER_LOGIN);
		});
	}
	// 오라클에 접속하여 insert 실행!! -> id,pass,name 입력후 가입 삽입
	public void regist() {
		PreparedStatement pstmt = null;
		
		String sql = "insert into board_member(member_id,m_id,m_pass,m_name)";
		sql += " values(seq_board_member.nextval, ?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t_id.getText());
			pstmt.setString(2, new String(t_pass.getPassword())); // 배열 -> 스트링으로
			pstmt.setString(3, t_name.getText());
			
			int result = pstmt.executeUpdate();	// 쿼리수행에 의해 영향을 받은 레코드수가 반환되므로,
																	// 만일 0 이 반환된다면 실패로 판단!!
			if(result==0) {
				JOptionPane.showMessageDialog(this, "가입에 실패하였습니다.\n관리자에 문의해주세요.");
			}else {
				JOptionPane.showMessageDialog(this, "가입을 축하드려요♥");
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
