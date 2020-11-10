package day1110.network.uni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

/*�� �������, ��ȭ�� �������̹Ƿ� �� ��� ��Ʈ���� ������ �ν��Ͻ��� �����ؾ� �Ѵ�.
	�Ʒ��� Ŭ������ ������� �����ϴ� ��������, �� �ν��Ͻ�����
	���� �񵿱������� ���� �� �� �ִ�.
*/
public class MessageThread extends Thread{
	
	Socket socket;	// ������ ������� ��ȭ�� ������ �����ؾ� ��Ʈ���� ���� �� �����Ƿ�,
							// �����ڰ� �����Ǹ� �ش� ������ �μ��� �Ѱܹ���.
	
	BufferedReader buffr;//���
	BufferedWriter buffw;//���ϱ�	
	UniServer uniServer; // JTextArea area; ���
	
	public MessageThread(UniServer uniServer, Socket socket) {
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
		listen();	// ��� ���ϰ� ������ ���� ������
	}
	
	//�޼��� �ޱ�(û��)
	public void listen() {
		String msg=null;
		try {
			while(true) {
				msg=buffr.readLine(); //����μ��� �ѹ��� ��´�..
				uniServer.area.append(msg+"\n");
				send(msg);//Ŭ���̾�Ʈ���� �ٽ� ������ �Ѵ�(������ �ǹ�)
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Ŭ���̾�Ʈ���� �޽��� ������ 
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

}
