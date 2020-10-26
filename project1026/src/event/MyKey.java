package event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Ű����� ������ �̺�Ʈ�� �߻��ϸ�, �ڹ� ����ӽ��� KeyListener���� 
// KeyEvent �ν��Ͻ��� �����Ѵ�. �� ���޵� �ν��Ͻ���  KeyListener�� ������
// �߻�޼���鿡 ���޵Ǿ����Ƿ�, �����ڴ� �߻�޼��带 �������ϸ鼭
// ���ϴ� ������ �ۼ��ϸ� �ȴ�!
public class MyKey implements KeyListener{
	// �������̵� �ϰ�, Ű���� �������� ������? �޽��� ������ �ϱ�
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Ű�� ���� ��,keyPressed called");
	}
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Ű�� ������ ����,keyPressed called");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println("Ű�� ĥ��,keyPressed called");
	}

	public static void main(String[] args) {
		
	}
}
