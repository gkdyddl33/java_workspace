package day1110.network.gui;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame{

	JPanel p_north;
	Choice ch_ip;
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt_send;
		
	Socket socket;	// 대화용 소켓
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public EchoClient() {
		// 생성
		p_north = new JPanel();
		ch_ip = new Choice();
		t_port = new JTextField("7777",10);
		bt_connect = new JButton("OK");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(15);
		bt_send = new JButton("send");
		
		// 조립
		add(p_north,BorderLayout.NORTH);
		p_north.add(ch_ip);
		p_north.add(t_port);
		p_north.add(bt_connect);
		add(area);
		add(p_south,BorderLayout.SOUTH);
		p_south.add(t_input);
		p_south.add(bt_send);
		
		ch_ip.setPreferredSize(new Dimension(90,20));
		ch_ip.add("localhost");
		
		// 2) 접속버튼과 리스너 연결
		bt_connect.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();				
			}
		});
		
		// 3) send버튼
		bt_connect.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				send();		
				listen();
			}
		});
		
		
		// 윈도우
		setVisible(true);
		setBounds(300, 200, 300, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void connect() {//2) 리스너 연결 - 에코서버에 연결
		try {
			socket =  new Socket(ch_ip.getSelectedItem(),Integer.parseInt(t_port.getText()));
			area.append("서버에 접속\n");
			
			// 3) 대화용 소켓의 접속이 성공되었으므로 스트림을 얻을 수 있다.
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 3) 클라이언트 -> 서버 에 메시지 보내기(출력) 
	public void send() {// send 버튼
		String msg = t_input.getText();	// 유저가 입력한 텍스트박스 메시지
		try {
			// 출력 - 버퍼비우기
			buffw.write(msg+"\n");
			buffw.flush();
						
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 4) 서버가 보낸 메시지 듣기 
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			area.append(msg+"\n"); // 대화기록하기
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new EchoClient();
	}
}
