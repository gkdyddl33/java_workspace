package day1110.network.multicasting;

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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiServer extends JFrame{
	JTextField t_port; 
	JButton bt;
	JPanel p_north;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;
	int port=7777;
	
	Thread thread; //���ξ����� ���, �����ڸ� �����ϰԵ� ������!! (accept() �޼��� ������..)
	
	// �����ִ� �����͸� ����ִ� ��ü
	Vector<MessageThread> clientList = new Vector<>();	// �����ڼ� ����
										
	public MultiServer() {
		t_port = new JTextField(Integer.toString(port),10);
		bt = new JButton("��������");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		//����
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		//������ư�� ������ ����
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thread = new Thread() {
					public void run() {
						startServer();
					}
				};
				thread.start();//Runnable �� ���Խ�Ű��!!!
			}
		});
		
		setVisible(true);
		setBounds(600,200,300,400); //x,y,width,height ���� 
		
	}
	
	//���� ����
		public void startServer() {
			try {
				server = new ServerSocket(Integer.parseInt(t_port.getText())); //�������� ���� 
				area.append("���� �غ�\n");
				
				//������ �������� �����ڸ� �����ؾ� �Ѵٸ�, �� �����ڸ��� �񵿱������� �� ���������μ��� �������
				//��ȭ�� �ְ� �޴� ��ü�� �������� �ν��Ͻ��� ó����
				while(true) {
					Socket socket = server.accept();//������ ������ ���ÿ� ��ȭ�� ���� ��ȯ!!
					area.append("������ �߰�\n");
					
					// �� �ټ��� ������ �� ������ �־����.
					MessageThread messageThread = new MessageThread(this, socket);
					messageThread.start();// Runnable�� ����!!
					
					clientList.add(messageThread);	// ���� ������ Ŭ���̾�Ʈ�� ���� �̷�� ������ ��ȭ�����带
																	// vector�� ��Ƶд�. �� ����� ���԰�, ���� ���Դ����� ���� 
																	// �����͸� ������ �� �ְ� �ȴ�.
					area.append("������� �����ڴ� "+clientList.size()+"��\n");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	public static void main(String[] args) {
		new MultiServer();
	}

}