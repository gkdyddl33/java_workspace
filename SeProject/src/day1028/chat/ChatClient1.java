package day1028.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient1 extends JFrame implements KeyListener,ActionListener{
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	JButton bt_open;
	ChatClient22 ch2;
	ChatClient3 ch3;
	
	public ChatClient1() {
		super("�� �θ�â");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(10);
		bt = new JButton("send");
		bt_open = new JButton("open");
		
		// �г����� - ����Ʈ�� flow
		p_south.add(t_input);
		p_south.add(bt);
		p_south.add(bt_open);
		add(scroll);
		add(p_south,BorderLayout.SOUTH);
		
		area.setBackground(Color.YELLOW);
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		t_input.setPreferredSize(new Dimension(250,30));
		t_input.addKeyListener(this);	
		bt_open.addActionListener(this);
		
		
		setBounds(200,150,300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void keyPressed(KeyEvent e) {;}
	
	@Override
	public void keyReleased(KeyEvent e) {
//		int key = e.getKeyCode();
//		if(key==10) {
//			String msg = t_input.getText();
//			area.append(msg+"\n");
//			t_input.setText("");
//		}
		send();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {;}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// �̺�Ʈ�� ����Ű��?
		Object obj = e.getSource();
		JButton btn = (JButton)obj; // �ٿ�ĳ����
		
		if(btn==bt) {
			send();
		}else if(btn.equals(bt_open)) {
			open();
		}
		
	}
	public void send() {// ���ν���(4)
		// �������� ó��
		String msg = t_input.getText();
		area.append(msg+"\n");
		t_input.setText("");
		
		// ����â�� ���� ó��
		ch2.area.append(msg+"\n");
		ch2.t_input.setText("");
		
		ch3.area.append(msg+"\n");
		ch3.t_input.setText("");
	}
	private void open() {
		// ���ο� â�� ���� ��
		ch2 = new ChatClient22(this);
		ch3 = new ChatClient3(this);
	}
	
	public static void main(String[] args) {
		new ChatClient1();
	}
}
