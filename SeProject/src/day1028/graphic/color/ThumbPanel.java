package day1028.graphic.color;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


/*������ �г� �����ϱ� - ���� �г��� Ŀ���͸���¡(�츮�Ը����ٲ���) custom*/
public class ThumbPanel extends JPanel implements MouseListener{
	JPanel p_center;
	// ������ �Լ��� ���������� �Ű������� �ֱ� ������ ��� �Ұ������� ���� ����
	Color color;
	
	public ThumbPanel(JPanel p_center,Color color) {// ���� ������ ���� ������ ����� �����Ÿ� ��������
		this.p_center=p_center;
		this.color=color;
		
		// �ʺ�,����,������ ���� �гη� �¾��!
		this.setPreferredSize(new Dimension(100,100));	// �ϳ��� �� ũ��
		this.setBackground(color); // 7���� ������ ��� �ϱ� ������ ������ ����!
		//(2) �����ư  ���콺�̺�Ʈ!!
		this.addMouseListener(this); // �����гΰ� �����ʿ��� ����		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("Ŭ���߾�?");
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		// center ���� �г��� ������ ��(�����г�)�� ���� �������� ��������!
		p_center.setBackground(color); // (���� ����)		
	}

}
