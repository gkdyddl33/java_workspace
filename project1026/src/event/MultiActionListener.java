package event;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiActionListener implements ActionListener{

	@Override
	// �ش� �޼���� ������ ��� ��ư�� ������ �Ѿ�´�.
	public void actionPerformed(ActionEvent e) {
		// ������ �߻���Ų �̺�Ʈ�� ���� ��� ������ Event �ν��Ͻ��� ���޵Ǿ����Ƿ�
		// � ��ư�� ���ȴ����� �� �� �ִ�.
		System.out.println(e);
		
		// �̺�Ʈ ��ü�� �޼��� �߿�, �̺�Ʈ�� ����Ų ������Ʈ(�̺�Ʈ)�� �����ϴ� �޼���
		// �̺�Ʈ�� ����Ų ���� ��Ƴ��� �޼���
//		Object obj = e.getSource();
//		Button bt1 = null;
//		Button bt2 = null;
//		if((Button)obj == bt1) {
//			System.out.println("ù��° ��ư�� �������ϴ�.");
//		}else {
//			System.out.println("�ι�° ��ư�� �������ϴ�.");
//		}
		
	}
	

}
