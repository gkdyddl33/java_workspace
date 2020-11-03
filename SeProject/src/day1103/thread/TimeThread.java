package day1103.thread;

import java.util.Calendar;

public class TimeThread extends Thread{

	@Override
	public void run() {
		while(true) {
			// �����带 ��� ����ϱ� ���� �ݺ����� ���
			// ���� �ð��� ���ؼ� 1�ʸ��� �����ؼ� ���!
			// �߻�Ŭ�����̹Ƿ� ��ü �޼���� �ν��Ͻ��� ����.
			Calendar cal= Calendar.getInstance();
			
			// ��,��,��,��,��,�� �����غ���
			System.out.println(cal.get(Calendar.YEAR)+"��"
			+(cal.get(Calendar.MONTH)+1)+"��"+cal.get(Calendar.DATE)
			+cal.get(Calendar.HOUR)+"��"+cal.get(Calendar.MINUTE)+"��"
			+cal.get(Calendar.SECOND));

			
//			System.out.println(cal);
			try {
				// 1000���� 1�� -> ������ٰ� �ٽ� ����(runnable����->����)
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
