package com.swingmall.member;

import java.awt.Color;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.swingmall.main.Page;
import com.swingmall.main.ShopMain;

public class RegistForm extends Page{
	JPanel p_content; //로그인폼과 동일한 목적으로 ...
	JTextField t_mid;
	JPasswordField t_pass;
	JTextField t_name;
	JTextField t_phone;
	JTextField t_email;
	JButton bt_regist;
	
	public RegistForm(ShopMain shopMain) {
		super(shopMain);
		p_content = new JPanel();
		
		t_mid = new JTextField("아이디 입력");
		t_pass = new JPasswordField();
		t_name = new JTextField("이름 입력");
		t_phone = new JTextField("연락처 입력");
		t_email = new JTextField("이메일 입력");
		bt_regist = new JButton("회원가입");
		
		//스타일 
		p_content.setPreferredSize(new Dimension(400, 350));
		p_content.setBackground(Color.WHITE);
		Dimension d = new Dimension(380,25);
		t_mid.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_phone.setPreferredSize(d);
		t_email.setPreferredSize(d);
		
		//조립 
		p_content.add(t_mid);
		p_content.add(t_pass);
		p_content.add(t_name);
		p_content.add(t_phone);
		p_content.add(t_email);
		p_content.add(bt_regist);
		
		add(p_content);
		
		// 2) 체크!
		bt_regist.addActionListener((e)->{
			if( checkId(t_mid.getText())) {
				JOptionPane.showMessageDialog(RegistForm.this, "중복된 아이디입니다\n 다른 아이디를 사용하세요.");
			}else {
				// 메서드 호출하기 전에, VO 값을 채워야 한다.
				ShopMember vo = new ShopMember();
				vo.setMid(t_mid.getText());
				vo.setPass(new String(t_pass.getPassword()));
				vo.setName(t_name.getText());
				vo.setPhone(t_phone.getText());
				vo.setEmail(t_email.getText());
				int result = regist(vo);
				if(result==0) {
					JOptionPane.showMessageDialog(RegistForm.this, "등록실패");
				}else {
					JOptionPane.showMessageDialog(RegistForm.this, "회원가입 성공");
				}								
			}			
		});
	}
	
	// 2) 회원 존재여부 체크
	// 반환값이 true가 나오면 회원가입 진행시키면 안됨!
	public boolean checkId(String mid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		
		String sql = "select * from shop_member where mid=?";
		
		try {
			pstmt = getShopMain().getCon().prepareStatement(sql);
			pstmt.setString(1, mid);
			rs= pstmt.executeQuery();
			flag = rs.next();			// 레코드가 존재하면 true, 아니면 false 대입됨..
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getShopMain().getDbManager().close(pstmt, null);
		}
		return flag;
	}
	// 1)
	public int regist(ShopMember shopMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into shop_member(member_id,mid,pass,name,phone,email)";
		sql += " values(seq_shop_member.nextval,?,?,?,?,?)";
		
		try {
			pstmt = getShopMain().getCon().prepareStatement(sql);
			pstmt.setString(1, ShopMember.getMid());
			pstmt.setString(2, ShopMember.getPass());
			pstmt.setString(3, ShopMember.getName());
			pstmt.setString(4, ShopMember.getPhone());
			pstmt.setString(5, ShopMember.getEmail());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getShopMain().getDbManager().close(pstmt, null);
		}
		return result;
	}
	
}