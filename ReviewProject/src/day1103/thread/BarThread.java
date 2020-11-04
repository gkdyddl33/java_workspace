package day1103.thread;

import javax.swing.JProgressBar;

public class BarThread extends Thread {
	JProgressBar bar;
	int interval;
	int n;
	
	public BarThread(JProgressBar bar,int interval) {
		// ������ ������ ���� ������ ����� ����.. ����� ��Ǫ���̹Ƿ� �Ű������� �޾Ƽ�
		// �������.
		// ������bar, ������ �ӵ�
		this.bar = bar;
		this.interval =interval;
	}
	
	@Override
	public void run() {
		// ��� ����
		while(true) {
			n++;
			bar.setValue(n);
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
}
