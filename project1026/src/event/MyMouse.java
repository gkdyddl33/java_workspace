package event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouse implements MouseListener{
	// ���콺 Ŭ����, "���콺 Ŭ���޾�" �޽��� ����
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("���콺 Ŭ���޴�?");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("���콺�� ����?");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("���콺�� �����ߴ�?");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("���콺�� ������?");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("���콺�� �ƴ�?");
	}
}
