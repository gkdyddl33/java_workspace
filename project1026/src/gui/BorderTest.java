package gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.BorderLayout;

/*
 *	���� ������Ʈ�� �÷����� ����, � ������� �÷��������� �����ϴ� ���� ��ġ�� �Ѵ�.
 *	AWT ���� �����ϴ� ��ġ�� ������ ���� 5���� ������ �ִ�.
 *	1) FlowLayout : ��������� �þ�� ��ġ���, ���� ������ �����ϸ� ���� ������ ��������.
 *					�����̳��� ũ�⿡ ���� �帣�� ��ġ�� �ǹǷ� flow��� ���� �ο�
 *	2) BorderLayout : ��,��,��,��,�߾��̶�� ������ ���� ��ġ ���
 *	3) GridLayout : ���� �� �ٵ��� ����� ��ġ���
 *	-> ��ǻ� ���� 1,2,3 ������ ����� ���� ������
 *	4) CardLayout : ī��ó�� �ϳ��� ī�尡 ���� ���� �ٸ� ī�尡 �Ⱥ��̴� ����� ��ġ
 *	5) GridBagLayout : GridLayout�� �ȼ������� ���ϰ� ������ �� �ִ� ��ġ���
 *	6) Frame
 */
public class BorderTest {
	public static void main(String[] args) {
		Frame frame = new Frame();
		
		// BorderLayout �н�
		Button bt_north = new Button("North");
		Button bt_south = new Button("south");
		Button bt_west = new Button("west");
		Button bt_east = new Button("east");
		Button bt_center = new Button("center");
		
		// ��ġ ����� ��������.
		frame.setLayout(new BorderLayout());
		
		//��ư�� �����ӿ� ���̱�
		frame.add(bt_north,BorderLayout.NORTH); // ���ʿ� ��ư ����
		frame.add(bt_south,BorderLayout.SOUTH); // ���ʿ� ��ư ����
		frame.add(bt_west,BorderLayout.WEST); // ���ʿ� ��ư ����
		frame.add(bt_east,BorderLayout.EAST); // ���ʿ� ��ư ����
		// CENTER�� ������� �ʾƵ� ����Ʈ�̱� ������ �����ص� ����ȴ�.
		frame.add(bt_center,BorderLayout.CENTER); // ����� ��ư ����		
		
		// ������ ����
		frame.setSize(250, 200);
		frame.setVisible(true);
	}
}
