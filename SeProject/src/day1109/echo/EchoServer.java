package day1109.echo;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
  	���� ��ǥ�� ä��(��Ƽĳ���� ����) ������, �ϴ� ������ Echo System�� ���� �н��Ѵ�.
 */
public class EchoServer {
	ServerSocket server;	// ��ȭ�� ������ �ƴ�, ������ ������ ������ ����!
	int port = 9999;
	
	
	public EchoServer() {
		// ���������� �̿��Ͽ�, ������ �޾ƺ���.
		// 1) �������� ����
		try {
			server = new ServerSocket(port);
			System.out.println("���� ���� �����Ϸ�");
			
			// 1-1) �����ڰ� �߰ߵ� ������ ��� ���·� ��ٸ�..
			Socket socket = server.accept();
			System.out.println("������ �߰�");
			
			// ��ȯ���� ������ �̿��ϸ�, ���� �����ڿ� ���� ������ ���� �� ������,
			// Ư�� ip. �� �����غ���.
			// ���ͳ� �ּ� ������ ���� ��ü			
			InetAddress inet = socket.getInetAddress();
			String ip = inet.getHostAddress();	// ip����
			System.out.println("������ Ŭ���̾�Ʈ�� �����Ǵ� "+ip);			
			
			// Ŭ���̾�Ʈ�� ���� �޽��� �ޱ� = �޽����� �޴� ���� �������� ���α׷����� �����Ͱ� ������ ���̹Ƿ�
			// 												�Է½�Ʈ������ ó���ؾ� �Ѵ�..
			//												�������κ��� ��Ʈ���� �̾Ƴ� �� �ִ�.
			// ����Ʈ ����� �Է½�Ʈ��(�ѱ� ����, �������� ����)
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
