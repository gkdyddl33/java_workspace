package event;

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
		if(e.getSource() == bt1) {
			
		}
		
	}
	

}
