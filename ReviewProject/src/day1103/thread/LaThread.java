package day1103.thread;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class LaThread extends Thread {	
	int interval;	
	int n;
	JProgressBar bar1;
	
	public LaThread(JProgressBar bar1,int interval) {
		// ������ ������ ���� ������ ����� ����.. ����� ��Ǫ���̹Ƿ� �Ű������� �޾Ƽ�
		// �������.
		// ������bar, ������ �ӵ�		
		this.bar1 =bar1;
	}
	
	@Override
	public void run() {
		// ��� ����
		while(true) {
			n++;
			bar1.setValue(n);
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
}
