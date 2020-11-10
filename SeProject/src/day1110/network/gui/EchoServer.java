package day1110.network.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoServer extends JFrame{

	JTextField t_port;
	JButton bt;
	JPanel p_north;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	int port = 7777;
	
	// �ڡڡ� ���ξ����� ���, �����ڸ� �����ϰ� �� ������
	//			�������? accept() ������..���Ѵ�⸦ ���ϱ� ����
	Thread thread;
	// 3) Ŭ���̾�Ʈ���� ������� �޽����� �޾ƾ� �Ѵ�.
	BufferedReader buffr; // ���
	BufferedWriter buffw; // ���ϱ�
	
	public EchoServer() {
		// ����
		t_port = new JTextField(Integer.toString(port),10);
		bt = new JButton("��������");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		// ����
		add(p_north,BorderLayout.NORTH);
		p_north.add(t_port);
		p_north.add(bt);
		add(scroll);
		
		// 1) ����������ư�� ������ ����
		bt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				thread = new Thread() {
					@Override
					public void run() {
						startServer();						
					}
				};
				thread.start();
			}
		});
		
		// ������
		setVisible(true);
		setBounds(600, 200, 300, 400);		
	}
	
	public void startServer() {// 1) �����ʿ��� method
		try {
			// ���� ���� ����
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("���� �غ�\n");
			// ���� ����
			// �ڹٴ� ���������̹Ƿ�, ���ݱ��� ���� ����ζ� �ҷȴ� ������ü�� ����� �ý��ۿ� ����
			// ������ �����忴��..������ ���ξ������ �����ڰ� �����ϴ� �Ϲ� ������ʹ� �ϴ¿��ҿ� ���̰� �ִ�.
			// ���ξ������ ���α׷��� ����ִ� ����. Ư��, �׷���ó��,�̺�Ʈó������ ����ϹǷ�
			// ����� �Ʒ��� �ڵ�δ� ������ �ϸ� �ȵȴ�. 
			// = ���ѷ����� ���Ѵ����¸� ����� �ȵȴ�. ex. accept(), read()..��
			// ����� �ȵ���̵忡���� ���ξ������� 1,2,�� �õ���ü�� ������ ����.
			// ------> ��� : ������ �����带 �����Ͽ� ó������.
			Socket socket = server.accept(); // ��ȭ����� ���� ��ȯ
			area.append("������ �߰�\n");
			
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			listen();	// ������
		} catch (NumberFormatException e) {
			e.printStackTrace();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 3) �޼��� �ޱ�(û��) - ������ �޽��� ����
	public void listen() {
		String msg = null;
		try {
			while(true) {
				msg = buffr.readLine();	
				area.append(msg+"\n");	// listen() ������ �����ٰ�
				
				// 4) �޽����� �����ڸ���..�Ѿ��������
				send(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 4) Ŭ���̾�Ʈ���� �޽��� ������ - ������ ���̴� �޽����� ����
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {		
		new EchoServer();
	}
}
