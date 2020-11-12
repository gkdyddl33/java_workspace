package day1110.network.uni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MessageThread2 extends Thread{

	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	UniServer2 uniServer2;
	
	public MessageThread2(UniServer2 uniServer2,Socket socket) {
		this.uniServer2=uniServer2;
		this.socket = socket;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			uniServer2.area.append(msg+"\n");
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
