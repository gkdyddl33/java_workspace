package day1028.graphic.line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener{
	XCanvas can;
	
	// ���� �˹��� new���� �Ѱܹ���
	public MyListener(XCanvas can) {
		this.can = can;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// (5) LineMaker�� �˹����� ���� �׸���, LineMaker Ŭ������ JTextField �� ���� �̿��Ͽ�..
		// paint() �޼���� �����ڰ� ���� ȣ���� ���� ����, ȣ���ؼ��� �ȵȴ�.
		// paint()�޼���� �׸��� �׷��� �غ� �Ǿ��� �� �ý���. ��, JVM�� ���� ȣ��ȴ�.
		// ���� �����ڰ� ���ϴ� Ÿ�ӿ�, �׸��� �����ϰ� �Ϸ���, paint() �޼��带 ���� ȣ���ؼ��� �ƴϵǰ�
		// ������ ���� �ý��ۿ� ��û!
		// repaint() �ٽ� �׷��ּ���! ->  update() ȭ�� �����
		// -> paint()
		// �˹����� ������.repaint();
		// xcanva�� paint() �Ұ���.		
		can.repaint();
	}
}
