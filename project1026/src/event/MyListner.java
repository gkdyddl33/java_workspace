package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *	jvm�� ������κ��� �̺�Ʈ������ ������, �ش� ������ ������ ���� �˸´�
 *	�̺�Ʈ ��ü�� �ν��Ͻ��� �޸𸮿� �ø���, �� �÷��� �ν��Ͻ� ������
 *	�����ʶ� �Ҹ��� �̺�Ʈ ���� ��ü���� ���޵Ǿ�����.
 *	�̶� �����ʴ� ��ü�� �ҿ����� �������̽��� �����Ǵµ�, �� ������
 *	Ŭ������ ������ �� ���� ��� ó�������� ���� �����ڰ� �����ؾ� �ϱ� �����̴�.
 *	��) Ŭ�� �̺�Ʈ�� �ڹٿ����� �׼��̺�Ʈ�� �ϰ� �ش� ��ü�� ActionEvent�̴�.
 *	�� ActionEvent�� �ν��Ͻ��� ����ڰ� Ŭ���� �� ������ �����ʿ��� ���޵Ǿ�����
 *	�����ڴ� �� �������� �߻�޼��带 ������ �����ν� �ϰ� ���� ������ �ϼ� ������ �ȴ�. 
 */
public class MyListner implements ActionListener{// �߻��̶� ������ �ؾ� ��
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("�� ������?");
	}
}
