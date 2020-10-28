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

public class ChatClient extends JFrame implements KeyListener{
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	
	public ChatClient() {
		// ����
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(15);
		bt = new JButton("send");
		
		// �г����� - �г��� ����Ʈ�� FlowLayout
		p_south.add(t_input);
		p_south.add(bt);
		
		add(scroll);	// ���Ϳ� ��ũ�� ����
		add(p_south,BorderLayout.SOUTH);	// ���ʿ� �г� ����
		
		// ��Ÿ�� ����
		area.setBackground(Color.YELLOW);
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		t_input.setPreferredSize(new Dimension(265,30));
		
		// (**) �����ֱ� ���� �̸� �����س���(������Ʈ�� ������ ����)
		MyActionListener listener = new MyActionListener(t_input,area);
		bt.addActionListener(listener);
		t_input.addKeyListener(this); // ���� �����ʴ�. -> ���� �����쿡 ����
													// �����ӿ� ���͸� ġ�� ������!!!!
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// (*) key �̺�Ʈ ����
	@Override
	public void keyPressed(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// ����Ű�� ������, area�� �Է� �����͸� �ݿ�����~(������Ű��)
		int key = e.getKeyCode();	// (***) Ű�ڵ尪 ��ȯ
		// System.out.println(key+"Ű ������?");
		if(key==10) {
			// (****)
			String msg = t_input.getText(); // ������ ��ȯ���ִ� �Լ�
			
			// System.out.println("area�� �߰�");
			area.append(msg+"\n");	// (****) �ؽ�Ʈ �ʵ� ���� ���ؼ� �߰��� ����.
			t_input.setText(""); 
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}

}
