package day1027.gui;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;

/*
 *	1) ����Ű ��κ��� - ctrl + shift + L 
 * 2) �ڵ� ����Ʈ : �ش� Ŭ������ Ŀ�� �÷�����, ctrl + shift + O
 * 3) �ش� ��ü�� api ���� �ٷΰ��� : �ش� Ŭ���� Ŀ�� �ø� �� shift + F2
 * 												  ���ͳ� ������ ���ִٸ�..
 */
public class RadioTest extends Frame{
	// �ڹٿ����� üũ�ڽ��� "����" �� ���
	CheckboxGroup group = new CheckboxGroup();

	public RadioTest() {
		group = new CheckboxGroup();
		setLayout(new FlowLayout());
		this.add(new Checkbox("�",group,false));
		this.add(new Checkbox("����",group,false));
		this.add(new Checkbox("��ǻ��",group,false));
		this.add(new Checkbox("��ȭ",group,false));
		this.add(new Checkbox("�丮",group,false));
		this.add(new Checkbox("�ְߵ�����",group,true));
		
		setSize(300,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new RadioTest();
	}

}
