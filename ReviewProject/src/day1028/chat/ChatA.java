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

public class ChatA extends JFrame implements KeyListener,ActionListener{
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	
	JButton bt_open;
	private ChatB chatB;
	private ChatC chatC;
		
	public ChatA() {
		super("�θ�â");
		// ����
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField();
		bt = new JButton("send");
		bt_open = new JButton("open");
		
		// ����
		add(p_south,BorderLayout.SOUTH);
		p_south.add(t_input);
		p_south.add(bt);
		p_south.add(bt_open);
		
		add(scroll);
		// ��Ÿ��
		area.setBackground(Color.YELLOW);
		t_input.setPreferredSize(new Dimension(120,30));
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		// �̺�Ʈ����
		t_input.addKeyListener(this);
		bt.addActionListener(this);
		
		setBounds(200,150,300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// ��ư �̺�Ʈ
		Object obj = e.getSource();
		JButton btn = (JButton)obj;
		
		if(btn==bt) {// ���͸� ġ�� ȿ���� ���� ��!
			send();
		}else if(btn.equals(bt_open)) {
			open();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {;}

	@Override
	public void keyReleased(KeyEvent e) {
		// Ű���� �̺�Ʈ -> ���ͷ� �̺�Ʈ�� ����Ű�� ���� 10		
		int key = e.getKeyCode();
		if(key==10) {
			send();
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {;}
	
	public void send() {
		// area�� ���� �ۼ��� ���� ������ ����..
		String msg = t_input.getText();
		area.append(msg+"\n");
		t_input.setText("");
		
		// �ʿ��� ����.
		chatB.area.append(msg+"\n");
		chatC.area.append(msg+"\n");
		
	}
	public void open() {
		// ������ �ΰ� â�� �����̴�!!
		chatB = new ChatB();
		chatC = new ChatC();	
		
		chatB.setChatA(this);
		chatB.setChatC(chatC);
		// chatC���� chatA,chatB�� �Ѱ�����
		chatC.setChatA(this);
		chatC.setChatB(chatB);		
	}
	public static void main(String[] args) {
		new ChatA();
	}
}
