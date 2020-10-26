package gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class Test {
	public static void main(String[] args) {
		// 프레임 생성
		Frame frame = new Frame("로그인 양식");
		frame.setVisible(true);
		frame.setSize(300,400);
		
		// id,pass 입력란 생성
		GridLayout grid = new GridLayout(2,1);
		frame.setLayout(grid);
		
		
		Label label1 = new Label("ID");
		Label label2 = new Label("Password");
		frame.add(label1);
		frame.add(label2);
		
		TextField tf1 = new TextField("",10);
		TextField tf2 = new TextField("",10);
		frame.add(tf1);
		frame.add(tf2);
		
		// 아래 버튼 생성		
		Panel p = new Panel();			
		Button bt1 = new Button("로그인");
		Button bt2 = new Button("회원가입");
		
		p.add(bt1);
		p.add(bt2);
	}
	
}
