package music;

import riding.Wing;

// ���ΰ� ����ȯ�� �����ϴ١ڡڡڡڡ�
public class Speaker extends AudioDevice implements Wing{
	/*
	 *	����̶�?
	 *	�θ��� ��� ����� �ڽ��� �����޴� ����
	 *	�θ�Ŭ������ �ҿ����� �߻�Ŭ���� �� ���, �̰� ����� �ƴ϶� ������
	 *	�� �θ��� �߻����� �޼����� �ϼ��� �ڽĿ��� ���ѱ� ���̴�.
	 *	�׷��� �ҿ����� �޼��带 ������ ������ �ؾ� �Ѵ� = �������̵� 
	 */
	boolean ooper;	// �������� ����
	String color = "red";
	
	public void sound() {
	}
	// pm�� ��Ź�� �Ǽ��� �����..���� ��� ������ �����̴�.
	public void setVolume() {
		// �극�̽��� �����ϱ⸸ �ϸ� �ȴ�.
		System.out.println("������ �����ؿ�");
	}
	public void playMP3() {
		System.out.println("MP3 ���� ����");		
	}
	public void fly() {
		System.out.println("�ϴ��� ���ƿ�");
	}
}
