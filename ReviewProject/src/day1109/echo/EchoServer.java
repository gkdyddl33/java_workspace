package day1109.echo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	// ��ȭ�� ������ �ƴ�, ������ ������ ������ ����!
	ServerSocket server;
	int port = 9999;
	
	public EchoServer() {
		try {
			server = new ServerSocket(port);
			System.out.println("���� ���� �����Ϸ�");
			
			// �����ڰ� �߰ߵ� ������ ��� ���·� ��ٸ�
			Socket socket = server.accept();
			System.out.println("������ �߰�");
			
			// �����ڿ� ���� ������ ���� �� ������, Ư�� ip.�� �����غ���.
			// ���ͳ� �ּ� ������ ���� ��ü
			// ��, �������� ip�� ��������.
			InetAddress inet = socket.getInetAddress();
			String ip = inet.getHostAddress();
			System.out.println("������ Ŭ���̾�Ʈ�� �����Ǵ� "+ip);
			
			// ������ �ޱ� = �Է� ��Ʈ��
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
