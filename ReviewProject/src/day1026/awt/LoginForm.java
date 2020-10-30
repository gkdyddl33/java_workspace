package day1026.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class LoginForm extends Frame{
	Label la_id;
	Label la_pass;
	TextField t_id;
	TextField t_pass;
	Panel south;
	Panel center;
	Button bt_login;
	Button bt_regist;
	
	public LoginForm() {
		la_id = new Label("ID");
		la_pass = new Label("Password");
		t_id = new TextField(15);
		t_pass = new TextField(15);
		bt_login  =new Button("Login");
		bt_regist = new Button("∞°¿‘");
		center = new Panel();
		south = new Panel();
		
		center.setBackground(Color.YELLOW);
		add(center,BorderLayout.CENTER);
		add(south,BorderLayout.SOUTH);
		south.setBackground(Color.GREEN);
		
		center.setLayout(new GridLayout(2,2));
		center.add(la_id);
		center.add(t_id);
		center.add(la_pass);
		center.add(t_pass);
		
		south.add(bt_login);
		south.add(bt_regist);
		
		
		
		setSize(400,150);
		setVisible(true);		
	}
	public static void main(String[] args) {
		new LoginForm();
	}
}
