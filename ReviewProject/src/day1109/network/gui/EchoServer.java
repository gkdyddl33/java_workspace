package day1109.network.gui;

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
	
	Thread thread;
	BufferedReader buffr;
	BufferedWriter buffw;
		
	public EchoServer() {
		t_port = new JTextField(Integer.toString(port),10);
		bt = new JButton("서버가동");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		add(p_north,BorderLayout.NORTH);
		p_north.add(t_port);
		p_north.add(bt);
		add(area);
		
		// 서버가동 버튼 리스너 연결
		bt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				thread = new Thread() {
					@Override
					public void run() {
						startServer();						
					}
				};
				thread.start();
			}
		});
		
		setVisible(true);
		setBounds(600,200,300,400);
	}
	
	public void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버준비\n");
			
			Socket socket = server.accept();
			area.append("접속자 발견\n");
			
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			listen();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void listen() {
		String msg = null; // 비어있는데 send로 인해 누적됨
		try {
			msg = buffr.readLine();
			area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String msg){// 클라이언트가 입력하면 받는 쪽이라서 msg입력할 필요가 없다.
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
