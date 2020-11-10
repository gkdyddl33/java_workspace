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
	
	// ★★★ 메인쓰레드 대신, 접속자를 감지하게 될 쓰레드
	//			사용이유? accept() 때문에..무한대기를 안하기 위해
	Thread thread;
	// 3) 클라이언트에서 날라오는 메시지를 받아야 한다.
	BufferedReader buffr; // 듣기
	BufferedWriter buffw; // 말하기
	
	public EchoServer() {
		// 생성
		t_port = new JTextField(Integer.toString(port),10);
		bt = new JButton("서버가동");
		p_north = new JPanel();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		// 조립
		add(p_north,BorderLayout.NORTH);
		p_north.add(t_port);
		p_north.add(bt);
		add(scroll);
		
		// 1) 서버가동버튼과 리스너 연결
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
		
		// 윈도우
		setVisible(true);
		setBounds(600, 200, 300, 400);		
	}
	
	public void startServer() {// 1) 리스너연결 method
		try {
			// 서버 소켓 생성
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 준비\n");
			// 서버 가동
			// 자바는 쓰레드기반이므로, 지금까지 메인 실행부라 불렸던 실행주체도 사실은 시스템에 의해
			// 생성된 쓰레드였다..하지만 메인쓰레드는 개발자가 생성하는 일반 쓰레드와는 하는역할에 차이가 있다.
			// 메인쓰레드는 프로그램을 운영해주는 역할. 특히, 그래픽처리,이벤트처리까지 담당하므로
			// 절대로 아래의 코드로는 업무를 하면 안된다. 
			// = 무한루프와 무한대기상태를 만들면 안된다. ex. accept(), read()..등
			// 참고로 안드로이드에서는 메인쓰레드의 1,2,번 시도자체를 에러로 본다.
			// ------> 결론 : 별도의 쓰레드를 생성하여 처리하자.
			Socket socket = server.accept(); // 대화용소켓 부착 반환
			area.append("접속자 발견\n");
			
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			listen();	// 듣기시작
		} catch (NumberFormatException e) {
			e.printStackTrace();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 3) 메세지 받기(청취) - 서버에 메시지 보임
	public void listen() {
		String msg = null;
		try {
			while(true) {
				msg = buffr.readLine();	
				area.append(msg+"\n");	// listen() 들은걸 보여줄게
				
				// 4) 메시지를 보내자마자..넘어오게하자
				send(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 4) 클라이언트에게 메시지 보내기 - 서버에 보이는 메시지를 보냄
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
