package day1110.network.multicasting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
	
	Thread thread; 
	Vector<MessageThread> clientList = new Vector<>();
	
	public MultiServer() {
		t_port = new JTextField(Integer.toString(port),10);
		bt = new JButton("서버가동");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		p_north.add(t_port);
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
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
			area.append("접속자발견\n");
			
			MessageThread messageThread = new MessageThread(this, socket);
			messageThread.start();
			clientList.add(messageThread);
			area.append("현재까지 접속자는 "+clientList.size()+"명\n");
			
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