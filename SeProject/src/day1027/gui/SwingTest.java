package day1027.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/*AWT�� �÷����� ���� �������� �ٸ��� ǥ���Ǵ� ������ �ֱ⿡, AWT�� ������ API-Swing�� ����غ���.*/
/*Swing�� ���Ӱ� ����ʿ� ������, ���� �츮�� ����ؿ��� AWT������Ʈ ��κ� J���ξ �ٴ´�.*/
/*����)AWT�� ������ ���ʿ��� ��Ű���� �ƴϴ�. AWT ��Ű���� event�� ��ġ�����ڴ� ������ AWT�� �̿��Ѵ�.*/
public class SwingTest extends JFrame{
	JCheckBox ch;
	JButton bt;
	
	public SwingTest() {
		ch = new JCheckBox("��ȭ");
		bt = new JButton("����ư");
		add(ch);
		add(bt);
		setLayout(new FlowLayout());
		setVisible(true);	// swing�� ������ â�� ���� �� �ִ�. false�� �ٲ�� ������
		// ���� �ڵ忡 ���� �ذ�å) ������ â ������ ���μ����� �Բ� ����ȴ�.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(300,400);
	}
	public static void main(String[] args) {
		new SwingTest();
	}
	
}
