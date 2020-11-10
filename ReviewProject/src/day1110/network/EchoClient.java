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

	Socket socket;
	String ip = "localhost";
	int port = 8989;
	
	Thread thread;
	public EchoClient() {
		try {
			socket = new Socket(ip,port);
			System.out.println("���� ����");
			
			// ��ȭ�� �ʿ�? �Է� ���
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffr = new BufferedReader(reader);
			
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(out);
			BufferedWriter buffw = new BufferedWriter(writer);
			
			thread = new Thread() {
				@Override
				public void run() {
					while(true) {
						try {
							buffw.write("��ȣ");
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
