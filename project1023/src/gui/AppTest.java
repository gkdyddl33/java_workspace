package gui;
/*
 *	���ݱ����� �ֿܼ����� �ڹٸ� �����������,
 *	�ڹٵ� �׷��� API�� �����Ѵ�.(�������̽�)
 *	������ ���� ����.. �ڹ��� ������� ��ư�� �����غ���. 
 */
import java.awt.Frame;
import java.awt.Button;

public class AppTest {
	public static void main(String[] args) {
		// �ڹٿ����� �������� ������ ���ִ� Ŭ������ Frame�� ����
		Frame frame;
		frame = new Frame();	// ������ ����
		frame.setSize(300, 400);// ������ �����쿡 �ʺ�,���� �ֱ�
		frame.setVisible(true); // �������� �Ӽ��� ���̰� ó��
		
		// ��ư�����Ͽ� �����ӿ� ���̱�
		Button bt = new Button("�չ�ư");
		frame.add(bt);
	}
}
