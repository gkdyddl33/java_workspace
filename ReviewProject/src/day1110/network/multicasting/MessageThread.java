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
				msg=buffr.readLine(); //����μ��� �ѹ��� ��´�..
				
				if(msg.equals("exit")) {
					// �ڵ��ɾ� �� �����ٴ� ���̸�..������ ó��
					// 1)���͸�ܿ��� ����  2) flag�� false ��� : ������ �Ҹ�
					multiServer.clientList.remove(this);	// ���� ��ܿ��� ����					
					flag = false;
					multiServer.area.append("������� ������ �� "+multiServer.clientList.size()+"\n");
				}else {
					// �ƴѰ��
					multiServer.clientList.remove(this);					
					flag = false;	// Ŭ���̾�Ʈ���� �ٽ� ������
				}			
				
				multiServer.area.append(msg+"\n");
				send(msg);//Ŭ���̾�Ʈ���� �ٽ� ������ �Ѵ�(������ �ǹ�)
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void send(String msg) {
		// msg ���� �������� �迭 ����Ʈ�� ����.
		for(int i=0;i<multiServer.clientList.size();i++) {
			MessageThread messageThread = multiServer.clientList.get(i);
			messageThread.buffw.write(msg+"\n");
			messageThread.buffw.flush();
		}
	}
	
}
