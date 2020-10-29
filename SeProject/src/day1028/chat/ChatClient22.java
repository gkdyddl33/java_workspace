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

public class ChatClient22 extends JFrame implements KeyListener{
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	ChatClient1 ch;
	
	public ChatClient22(ChatClient1 ch) {
		this.ch = ch;
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(15);
		bt = new JButton("send");
		
		p_south.add(t_input);
		p_south.add(bt);
		add(scroll);
		add(p_south,BorderLayout.SOUTH);
		
		area.setBackground(Color.GREEN);
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		t_input.setPreferredSize(new Dimension(265,30));
		
		t_input.addKeyListener(this);
		
		setBounds(500,150,300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		send();
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	private void send() {
		// area창에 전송
		String msg = t_input.getText();
		area.append(msg+"\n");
		t_input.setText("");
		
		// 너의 창에
		ch.area.append(msg+"\n");
		ch.t_input.setText("");

	}
	// main은 자식이므로 필요없음
}
