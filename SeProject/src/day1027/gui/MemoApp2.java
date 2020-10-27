package day1027.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// �޸��常���
public class MemoApp2 extends JFrame{
	JMenuBar bar;
	JMenu m_file,m_edit,m_style,m_view,m_help;
	JTextArea area;
	JScrollPane scroll;
	
	// (2) m_file�� �� ������ �ֱ�
	JMenuItem[] items;	// ���� ����Ȯ��
	// ����� ���� ������ ������ �ֱ�
	String[] item_title = {"���θ����","�� â","����","����","�ٸ��̸����� ����","������ ����","�μ�","������"};
	
	public MemoApp2() {
		// (1) �޴��������� �� �� ����
		bar = new JMenuBar();
		// (1) ������ �ٿ� ���ʷ� ���� �޴� ����
		m_file = new JMenu("����");
		m_edit = new JMenu("����");
		m_style = new JMenu("����");
		m_view = new JMenu("����");
		m_help = new JMenu("����");
		
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
				
		// (2) �޴� bar�� item����
		items = new JMenuItem[item_title.length]; // ����Ȯ�� -> �� ������ ���̸�ŭ
		for (int i = 0; i < items.length; i++) {
			items[i] = new JMenuItem(item_title[i]);
			// ���м��߰�
			if(i==5|i==7) {
				m_file.addSeparator();	//separator ���۷����� = �и�,�ݸ�
			}
			m_file.add(items[i]);			
		}
		
		// (1) ������ �����鵵 ����
		area = new JTextArea();
		scroll = new JScrollPane(area);	// ��ũ���� �ް� ���� ������Ʈ�� �Ű������� �ֱ�
		add(scroll);
		this.setJMenuBar(bar);
		
		// (4) �޴��� ũ�� ����
		// ��Ź��..ũ������..
		m_file.setPreferredSize(new Dimension(100,45)); // preferred �켱��, demension ũ��,ġ��
		m_edit.setPreferredSize(new Dimension(100,45));
		m_style.setPreferredSize(new Dimension(100,45));
		m_view.setPreferredSize(new Dimension(100,45));
		m_help.setPreferredSize(new Dimension(100,45));
		
		// (3) ��Ÿ�� ���� bar
		bar.setBackground(Color.BLACK);
		m_file.setForeground(Color.WHITE); // foreground ����,��ġ
		m_edit.setForeground(Color.WHITE);
		m_style.setForeground(Color.WHITE);
		m_view.setForeground(Color.WHITE);
		m_help.setForeground(Color.WHITE);
		
		// (3) ��Ÿ�� ���� area
		area.setBackground(Color.YELLOW);
		area.setFont(new Font("Verdana", Font.BOLD|Font.ITALIC, 20)); // �۲�,��Ÿ��,ũ��
		area.setForeground(Color.RED);
		
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	public static void main(String[] args) {
		new MemoApp2();
	}

}
