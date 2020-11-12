package day1110.network.multicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MessageThread extends Thread{

	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	MultiServer multiServer;
	
	public MessageThread(MultiServer multiServer, Socket socket) {
		this.multiServer=multiServer;
		this.socket=socket;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		listen();
	}
	
	public void listen() {
		String msg=null;
		try {
			while(flag) {
				msg=buffr.readLine(); //현재로서는 한번만 듣는다..
				
				if(msg.equals("exit")) {
					// 코드명령어 중 나간다는 뜻이면..나가는 처리
					// 1)백터명단에서 제거  2) flag도 false 등록 : 쓰레드 소멸
					multiServer.clientList.remove(this);	// 나를 명단에서 제거					
					flag = false;
					multiServer.area.append("현재까지 접속자 수 "+multiServer.clientList.size()+"\n");
				}else {
					// 아닌경우
					multiServer.clientList.remove(this);					
					flag = false;	// 클라이언트에게 다시 보내기
				}			
				
				multiServer.area.append(msg+"\n");
				send(msg);//클라이언트에게 다시 보내야 한다(서버의 의무)
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void send(String msg) {
		// msg 누적 하지말고 배열 리스트로 넣자.
		for(int i=0;i<multiServer.clientList.size();i++) {
			MessageThread messageThread = multiServer.clientList.get(i);
			messageThread.buffw.write(msg+"\n");
			messageThread.buffw.flush();
		}
	}
	
}
