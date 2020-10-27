package day1027.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient2 extends JFrame implements KeyListener{
	JTextArea area;
	JTextField t_input;
	JButton bt;
	JPanel p_south;
	JScrollPane scroll;
	
	public ChatClient2() {
		area = new JTextArea();
		t_input = new JTextField(15);
		bt = new JButton("send");
		p_south = new JPanel();
		scroll = new JScrollPane(area);
		
		// 패널조립
		p_south.add(t_input);
		p_south.add(bt);
		
		add(scroll);
		add(p_south,BorderLayout.SOUTH);
		
		// 스타일 적용
		area.setBackground(Color.YELLOW);
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		t_input.setPreferredSize(new Dimension(265,30));
		
		// ★★★ 리스너 연결
		MyActionListener2 listener = new MyActionListener2(bt);
		bt.addActionListener(listener);
		t_input.addKeyListener(this);
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// 엔터키를 누르면, area에 입력 데이터를 누적시켜 반영하자.
		int key = e.getKeyCode();
		if(key==10) {
			// syso로 key값 확인 = 엔터(10)
			String msg = t_input.getText();
			area.append(msg+"\n");
			t_input.setText("");
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new ChatClient2();
	}
}
