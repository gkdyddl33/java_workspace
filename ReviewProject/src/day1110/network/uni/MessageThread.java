package day1110.network.uni;

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
	UniServer uniServer; // 서버에 다시 내용을 보내야 하므로 출력!!
	
	public MessageThread(UniServer uniServer,Socket socket) {
		this.uniServer=uniServer;
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
//메세지 받기(청취)
	public void listen() {
		String msg=null;
		try {
			while(true) {
				msg=buffr.readLine(); //현재로서는 한번만 듣는다..
				uniServer.area.append(msg+"\n");
				send(msg);//클라이언트에게 다시 보내야 한다(서버의 의무)
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//클라이언트에게 메시지 보내기 
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
