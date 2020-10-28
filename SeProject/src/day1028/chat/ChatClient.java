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

public class ChatClient extends JFrame implements KeyListener,ActionListener{
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	JButton bt_open; // ���ν���(1) ��ȭ������ ���� ��ư
	ChatClient2  ch2; // ���ν���(4)
	
	public ChatClient() {
		// ���ν���(3) �� �ڽ��� ������ = ������ �θ� ���� �¾�� ��..super()
		super("�� �θ�â");
		// ����
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(10);
		bt = new JButton("send");
		bt_open = new JButton("open");	// ���ν���(1) ��ȭ������ ���� ��ư
		
		
		// �г����� - �г��� ����Ʈ�� FlowLayout
		p_south.add(t_input);
		p_south.add(bt);
		p_south.add(bt_open); // ���ν���(1) �Ʒ�â�� ����!!
		
		add(scroll);	// ���Ϳ� ��ũ�� ����
		add(p_south,BorderLayout.SOUTH);	// ���ʿ� �г� ����
		
		// ��Ÿ�� ����
		area.setBackground(Color.YELLOW);
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		t_input.setPreferredSize(new Dimension(250,30));
		
		// (**) �����ֱ� ���� �̸� �����س���(������Ʈ�� ������ ����)
		//MyActionListener listener = new MyActionListener(t_input,area);
		//bt.addActionListener(listener);
		t_input.addKeyListener(this); // ���� �����ʴ�. -> ���� �����쿡 ����
													// �����ӿ� ���͸� ġ�� ������!!!!
		
		// ���ν���(2) send��ư, open ��ư�� ������ ����
		bt.addActionListener(this); // ���� �������� �� �������̴�.
		bt_open.addActionListener(this);
		
		
		//setSize(300,400); -> �ش� �ڵ带 �Ʒ��ڵ�� ���� = ���� ���ϴ� ��ġ�� ���
		setBounds(200,150,300,400);
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
	
	// ���ν���(2) ��ư ������ �� �̺�Ʈ�� �������ִ� �޼ҵ�
	@Override
	public void actionPerformed(ActionEvent e) {
		// �������� �̺�Ʈ.. ��� �̺�Ʈ ���� �𸣴�.. �̺�Ʈ�� ��ȯ���ִ� �޼ҵ� ���
		Object obj = e.getSource();
		JButton btn = (JButton)obj; // �ʼ��� �ƴ����� �����ϴٸ� �����ִ� ��!
		
		// �Ѵ� ����� ����(=)
		if(btn==bt) {// ������ư�� send��ư�� �ּҰ��� ���ٸ�?
			System.out.println("send ��ư ������");		
			send();
		}else if(btn.equals(bt_open)) {// ������ư�� open��ư�� ������ ���ٸ�?
			System.out.println("open ��ư ������");	
			open();
		}		
	}
	// ���ν���(3) �޽���â�� ��ȭ ���� �����ϱ� = �޼ҵ� ȣ�� �����
	public void send() {
		// (4) �������� ä��ó��
		String msg = t_input.getText();		
		area.append(msg+"\n");
		t_input.setText(""); 
		
		// (4) �ʿ����� ä��â.. -> ���Ե� ó��.class
		// msg = ch2.t_input.getText();	// ����. ��°� ���ߵ�	
		ch2.area.append(msg+"\n");
		ch2.t_input.setText("");

	}
	// ���ν���(3) �޽���â�� ���� ������ ���� = �޼ҵ� ȣ�� �����
	public void open() {
		// ChatClient2 �� ȭ�鿡 ����
		ch2 = new ChatClient2(this);	 // (5)�����ڸ� ���� �ٽô� ���� ���⿡ ���� ����	
	}
	public static void main(String[] args) {
		new ChatClient();
	}


}
