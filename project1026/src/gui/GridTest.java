package gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;

/*
 *	awt/swing/fx --> �ȵ���̵�
 */
public class GridTest {
	public static void main(String[] args) {
		Frame frame = new Frame("�׸��� ���̾ƿ�");
		
		// ���̾ƿ� �Ŵ��� ���� �� ���
		GridLayout layout = new GridLayout(1,3);
		// FlowLayout layout = new FlowLayout();	// �ڱ� �ڽ� ������ ũ�⸦ ����ش�.
		// �׸��带 �����ϸ鼭, ������Ʈ�� ������ ũ�⸦ �����ϴ� �����?
		// �ΰ��� ������������ �������� ����..
		// �ذ�å? ������Ʈ �� ��ġ������ ������ ������ �г��� �̿��ϸ� �ȴ�.
		// �� �κ������� �ٸ� ��ġ�����ڸ� ������ �� ���� ����..
		frame.setLayout(layout); // �����ӿ� ���̾ƿ� ����
		
		Panel p = new Panel();	// ���� ������ ���� = �г�
		
		Button bt1 = new Button("��ư1");
		Button bt2 = new Button("��ư2");
		Button bt3 = new Button("��ư3");
		
		// �гο� ��ư �ֱ�
		p.add(bt1);
		// �����ӿ� �гγֱ�
		frame.add(p);
		
//		for(int i=0;i<6;i++) {
//			frame.add(new Button("��ư"+i));
//		}
		frame.setSize(300,200);
		frame.setVisible(true);
	}
}
