package day1110.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
 	�����̶�?
 	�ϻ��Ȱ������ ���� � �Ŵ� ���������̴�.
 	
 	�����̶�?
 	�ϻ��Ȱ - ���������� ���, ������ ���۽�ų �� �ִ�.
 	���α׷��� �о� - ��ũ��ũ ������ ��� ��Ʈ��ũ ���� ���α׷����� �����ϰ� �ϴ� ��..
 							����, ��ǻ� ���α׷��� ���� �����ڰ� �����ϴ� ����� ��Ʈ���� �� ���̴�.
 	
 	������ �ڹ� �� �����ϴ� ����̰� ����̴�.(X)
 	�����ϴ� ��κ��� ���� ���ø����̼ǿ��� �����ϰ� �ִ�.
 */
public class EchoServer {
	// Ŭ���̾�Ʈ�� �޾Ƶ��� ��Ʈ��ȣ, �� ��Ʈ��ȣ�� ���� �ٸ� ��Ʈ��ũ ���μ����� ���е� �� �ִ�.
	// ����Ŭ 1521, MySql 3306
	int port=8989;
	
	// ��ȭ�� ������ �ƴ϶�, �����ڸ� �����ϰ�, �����ڰ� �߰ߵǸ� ��ȭ�� ������ ��ȯ���ִ� ��ü
	// ��, ��ġ ��ȭ���� ���� �︮�� �� ���ĺ��� ��ȭ�� �ް� ��ȭ�ϴ� �Ͱ� ��� = ��ȭ �޴¿� ����
	ServerSocket server;
	
	public EchoServer() {
		try {
			server = new ServerSocket(port);	// �������� ����
			Socket socket = server.accept();	// �������� �� Ŭ���̾�Ʈ '����' ��ٸ� = ���Ѵ��
			System.out.println("������ �߰�");	// ���� �޸𸮿� �ø���.
			
			// �����ڴ� ��ȯ���� �������κ��� ��ſ� �ʿ��� ����� ��Ʈ���� ���� �� �ִ�.
			// �̶� �����ڴ� ��Ʈ��ũ �Ϻο� ���� �ƹ��� ������ ���� �׳� ��Ʈ�� ó���� �ϸ� �˾Ƽ� ��������
			// ��ȭ ���� ����� �����ϸ� �� ��� �͵��� ������ �˾Ƽ� ���ش�.
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffr = new BufferedReader(reader);
			
			String data;
			while(true) {
				data = buffr.readLine();	// ���ϰ� ����� ��Ʈ�����κ��� �б�
				System.out.println(data);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EchoServer();
	}
}
