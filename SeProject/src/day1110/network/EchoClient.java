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
	// 대화용 소텟
	Socket socket;
	String ip = "localhost";
	int port=8989;
	
	Thread thread;
	public EchoClient() {
		// 소켓생성 - 접속시도 - 연결됨
		// 인스턴스가 얻어졌다는 것은 이미 서버와의 접속이 된 상태이다.
		try {
			socket = new Socket(ip,port);	// String, Int
			System.out.println("접속 성공");
			
			// 받는 용, 즉 듣는 용
			InputStream is = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(is);
			BufferedReader buffr = new BufferedReader(reader);
			
			// 보내는 용, 즉 말하는 용
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(out);
			BufferedWriter buffw = new BufferedWriter(writer);
			
			thread = new Thread() {
				@Override
				public void run() {
					while(true) {
						try {
							// 얻어진(입력) 스트림을 이용하여 서버에 메시지 보내기(출력)
							buffw.write("야호\n");		
							// 버퍼 처리된 출력 스트림의 경우, 스트림 안의 데이터를 모두 비워버리는 것
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
			
			thread.start();// runnable 영역으로 밀어넣기(JVM에게 맡기기)
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
