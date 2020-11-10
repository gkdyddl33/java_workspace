package day1109.echo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	// 대화용 소켓이 아닌, 접속자 감지용 서버측 소켓!
	ServerSocket server;
	int port = 9999;
	
	public EchoServer() {
		try {
			server = new ServerSocket(port);
			System.out.println("서버 소켓 생성완료");
			
			// 접속자가 발견될 때까지 블락 상태로 기다림
			Socket socket = server.accept();
			System.out.println("접속자 발견");
			
			// 접속자에 대한 정보를 구할 수 있으며, 특히 ip.를 조사해보자.
			// 인터넷 주소 정보를 가진 객체
			// 즉, 접속자의 ip를 가져오다.
			InetAddress inet = socket.getInetAddress();
			String ip = inet.getHostAddress();
			System.out.println("접속한 클라이언트의 아이피는 "+ip);
			
			// 데이터 받기 = 입력 스트림
			InputStream is = socket.getInputStream();
			int data;
			while(true) {
				data = is.read();
				System.out.print((char)data);				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EchoServer();
	}
}
