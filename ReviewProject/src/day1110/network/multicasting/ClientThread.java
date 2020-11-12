package day1110.network.multicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientThread extends Thread{

	MultiClient multiClient;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag = true;
	
	public ClientThread(MultiClient multiClient,Socket socket) {
		this.multiClient=multiClient;
		this.socket=socket;
		
		//접속이 성공되었으므로, 스트림을 얻을 수 있다. 
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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
			while(flag) {// 계속 듣고있어야 내가 치는것을 다 받을 수 있다.
				msg=buffr.readLine();
				multiClient.area.append(msg+"\n"); //대화기록하기				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//서버에 메시지 보내기(출력)
	public void send(String msg) {		
		try {			
			buffw.write(msg+"\n");
			buffw.flush(); //남아있는 데이터없이, 버퍼비우기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
