package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiActionListener2 implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e);
		
		// �̺�Ʈ ��ü�� �޼��� �߿�, �̺�Ʈ�� ����Ų ������Ʈ(�̺�Ʈ)�� �����ϴ� �޼���
		// �̺�Ʈ�� ����Ų ���� ��Ƴ��� �޼���
		if(e.getSource()) {
			
		}
	}

}
