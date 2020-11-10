package day1110.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	// ��ȭ�� ����
	Socket socket;
	String ip = "localhost";
	int port=8989;
	
	Thread thread;
	public EchoClient() {
		// ���ϻ��� - ���ӽõ� - �����
		// �ν��Ͻ��� ������ٴ� ���� �̹� �������� ������ �� �����̴�.
		try {
			socket = new Socket(ip,port);	// String, Int
			System.out.println("���� ����");
			
			// �޴� ��, �� ��� ��
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffr = new BufferedReader(reader);
			
			// ������ ��, �� ���ϴ� ��
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(out);
			BufferedWriter buffw = new BufferedWriter(writer);
			
			thread = new Thread() {
				@Override
				public void run() {
					while(true) {
						try {
							// �����(�Է�) ��Ʈ���� �̿��Ͽ� ������ �޽��� ������(���)
							buffw.write("��ȣ\n");		
							// ���� ó���� ��� ��Ʈ���� ���, ��Ʈ�� ���� �����͸� ��� ��������� ��
							buffw.flush();
							Thread.sleep(1000);
							
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}					
				}
			};
			
			thread.start();// runnable �������� �о�ֱ�(JVM���� �ñ��)
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new EchoClient();
	}
}
