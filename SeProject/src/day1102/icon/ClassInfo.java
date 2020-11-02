package day1102.icon;

import java.lang.reflect.Method;
import java.net.URL;

/*
 *	java.lang �� �ִ� Ŭ���� �� Ŭ������ ���� ������ ���� Ŭ������ �����ȴ�. 
 */
public class ClassInfo {
	Class myClass;
		
	public void test() {
		
	}
	public ClassInfo() {
		// ���� ������� ClassInfo��� Ŭ������ ���� ������ ����غ���.
		// ���α׷��������� ���� Ŭ������ ���� ������ ���غ���.
		myClass = this.getClass();		// ���� �ν��Ͻ��� ������ ����!
		Method[] methods = myClass.getMethods();		// ���� �ν��Ͻ��� ������ �޼������ ��ȯ
		for (int i = 0; i < methods.length; i++) {
			System.out.println("���� ��ü�� ������ �޼���� "+methods[i]);
		}
		// �����? ����� �޼������ �����Ϸ��� �� ���� �ƴϰ�
		// Class Ŭ������ �̿��ϸ�, �ش� Ŭ������ "ȯ�� ����" � ������ �� �ִ�.
		// ���� �츮�� Ŭ���� �н��� �����ؼ� �츮�� ������� res��� ��Ű���� ��θ� ������ ����..
		// �Ʒ��� �޼��带 �̿��ϸ� Ŭ���� �н��� �������� �ڿ��� ������ �� �ֵ�
		// ��Ű���� ���� �ڿ��� Ŭ������ �ƴ� �Ϲ� ������ ��� . �� �ƴ� /�����÷� �����ؾ� ��
		URL url = myClass.getClassLoader().getResource("res/tube.png");	// ��Ű����� �����ϰ��� �� ���ϸ��� ����
																											// �׷��� �ϵ��ũ�� �ƴ� �Ϲ� ��η� ã�ƿ��� ���̴�.
		System.out.println(url); // ���� �ִ� ��ġ�� �˾Ƹ����ش�.
	}
	public static void main(String[] args) {
		new ClassInfo();
	}
}
