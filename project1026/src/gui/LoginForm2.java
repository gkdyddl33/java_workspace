package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class LoginForm2 extends Frame {
	Label la_id;
	Label la_pass;
	TextField t_id;
	TextField t_pass;
	
	Button bt_login;
	Button bt_regist;
	
	Panel p_center;	// �׸��� ����
	Panel p_south;	// ��ư ����
	
	public LoginForm2() {
		la_id = new Label("ID");
		la_pass = new Label("Password");
		t_id = new TextField(15);
		t_pass = new TextField(15);
		bt_login = new Button("Login");
		bt_regist = new Button("����");
		
		p_center = new Panel();
		p_south = new Panel();
		
		// frame�� ����Ʈ�� borderlayout - ��������
		p_center.setBackground(Color.GRAY);
		p_south.setBackground(Color.YELLOW);
		
		// (*) ���̾ƿ��� ����.
		this.add(p_center,BorderLayout.CENTER);	// ��ġ���
		this.add(p_south,BorderLayout.SOUTH);
		
		p_center.setLayout(new GridLayout(2,2));
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pass);
		p_center.add(t_pass);
		
		p_south.add(bt_login);
		p_south.add(bt_regist);
		
		this.setSize(400,150);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new LoginForm2();
	}
}
