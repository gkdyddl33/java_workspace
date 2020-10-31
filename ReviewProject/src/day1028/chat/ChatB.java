package day1028.chat;

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

public class ChatB extends JFrame implements KeyListener{
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	
	JButton bt_open;
	ChatA chatA;
	ChatC chatC;
		
	public ChatB() {
		super("자식창");
		// 생성
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField();
		bt = new JButton("send");
		
		// 조립
		add(p_south,BorderLayout.SOUTH);
		p_south.add(t_input);
		p_south.add(bt);
		
		add(scroll);
		// 스타일
		area.setBackground(Color.YELLOW);
		t_input.setPreferredSize(new Dimension(120,30));
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		// 이벤트진행
		t_input.addKeyListener(this);
		
		setBounds(500,150,300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ChatB();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {;}

	@Override
	public void keyReleased(KeyEvent e) {
		// 키보드 이벤트 -> 엔터로 이벤트를 일으키기 위해 10		
		int key = e.getKeyCode();
		if(key==10) {
			send();
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {;}
	public void send() {
		// area에 내가 작성한 글이 나에게 오고..
		String msg = t_input.getText();
		area.append(msg+"\n");
		t_input.setText("");
		
	}
	public void setChatA(ChatA chatA) {
		this.chatA = chatA;
	}
	public void setChatC(ChatC chatC) {
		this.chatC = chatC;
	}
}
