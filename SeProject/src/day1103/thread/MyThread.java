package day1103.thread;

/*
 *	�����ڴ� ���������� �����ϰ� ���� �ڵ尡 ���� ��, �����带 ��ӹ޾�
 *	run() ������ �ϸ� �ȴ�. ��, run()�� �����ڰ� �ۼ��ϰ��� �ϴ� ������ �ۼ��ϸ� �ȴ�.
 * 
 */
public class MyThread extends Thread{

	@Override
	public void run() {
		// jvm�� �ش� run() �޼��带 �������ָ�, �̶��� ������ running ���¶� �Ѵ�.
		while(true) {
			System.out.println("��");			
		}
	}
}
