package day1103.thread;

/*
 *	�������?
 *	- �ϳ��� ���μ��� ������ �񵿱������� ������ �� �ִ� �� �ϳ��� ���� ���� ������ ���Ѵ�.
 *	
 *	�������� �����ֱ�(LifeCycle - ��������?�������� �������?)
 * 
 */

public class ThreadTest1 {
	// �����
	public static void main(String[] args) {
		//(2)�ð������� �����ϰ�, ���۽��Ѻ���.
		TimeThread tt = new TimeThread();
		tt.start();	// runnable ���·� ����
		
		// 0.5�ʸ��� ���� ����ϴ� �����带 �����ϵ�, ����Ŭ���������� ��������
		// (�����͸�Ŭ����)
		Thread startThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					System.out.println("��");
					try {
						// 0.5��
						Thread.sleep(500);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					
				}
			}
		};
		startThread.start();
		// �����ڰ� ������ �����带 �̿��ؼ� ���ѷ��� ����!!
		MyThread t1 = new MyThread();	// �нŻ���
		
		// �����尡 ������ run() �޼���� jvm�� ���� ȣ��ȴ�.
		// run()���� �����Ǹ� �ؾ� ��밡��!
		// �������� ������ �ý��ۿ� �ðܾ� �Ѵ�.. = �����غ� �ϰ� ���ִ� ��
		// ���� ����δ� �����尡 ������ �ǵ� ���� �ڱ� ���� ���� = �񵿱���
		//t1.start();
		
//		while(true) {
//			System.out.println("hi");
//			
//		}
	}
}
