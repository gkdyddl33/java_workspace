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

// GUI ��, �������� ��ü���� ���� �Ϲ� Ŭ����..
public class MemoApp extends JFrame{
	JMenuBar bar;	// (1) �޸��忡 �޴���
	JMenu m_file,m_edit,m_style,m_view,m_help; // (1) �޴��� �̸� ���� ����
	JTextArea area;  // (1) �ؽ�Ʈ������ ��ũ�� �����
	JScrollPane scroll; 	
	
	// (2) ��ü�ڷ����� �ڷ����̹Ƿ�, �迭�� �����ϴ�. 
	JMenuItem[] items;
	String[] item_title = {"���θ����","�� â","����","����","�ٸ��̸����� ����","������ ����","�μ�","������"};
	
	public MemoApp() {
		bar = new JMenuBar();		
		
		m_file = new JMenu("����");
		m_edit = new JMenu("����");
		m_style = new JMenu("����");
		m_view = new JMenu("����");
		m_help = new JMenu("����");
		
		// bar �� �޴� ����
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
		
		// (2) �޴� bar�� item ����
		// ���� - �޴��������� �����Ȱ� �ƴ϶�, �������� �� �ڸ��� 8ĭ Ȯ��
		// js�ʹ� �޸�, �ڹٿ����� �迭�� �ڷ����� �̹� �����Ǹ�, �ش� �ڷ����� ���� �� ����.
		items = new JMenuItem[item_title.length]; // [] [] [] [] [] [] [] []
		for (int i = 0; i < items.length; i++) {
			items[i] = new JMenuItem(item_title[i]);
			// (2-up) 5��° �����ϸ� ���м� �߰�
			if(i==5||i==7) {
				m_file.addSeparator(); // ���м� ����
			}
			// ���� �ڵ�� ��Ҵٸ� ���ϸ޴��� ������ ����
			m_file.add(items[i]);
		}
		
		area = new JTextArea();
		// ��ũ�� �ް� ���� ������Ʈ�� ������ �Ű������� �ֱ�
		scroll = new JScrollPane(area);		
		
		// bar�� ���� Ư������ �ֱ� ������ ��ġ�����ڿ� ������� ������ �������� ��ܿ����� �ٿ���
		this.setJMenuBar(bar); 	// JFrame�� �� ����~
				
		// JFrame�� scroll����(���� ���⿣ area�� �����ؾ� �� �� ó�� ��������, scroll�� area�� �����ϰ� �����Ƿ�
		// scorll�� �ٿ��� �Ѵ�.)
		add(scroll);
		
		// (4) �޴��� ũ�� ����
		m_file.setPreferredSize(new Dimension(100,45));
		m_edit.setPreferredSize(new Dimension(100,45));
		m_style.setPreferredSize(new Dimension(100,45));
		m_view.setPreferredSize(new Dimension(100,45));
		m_help.setPreferredSize(new Dimension(100,45));
		
		// (3) bar ��Ÿ�� ����
		bar.setBackground(Color.BLACK); // ����
		m_file.setForeground(Color.WHITE); // �۾���
		m_edit.setForeground(Color.WHITE); // �۾���
		m_style.setForeground(Color.WHITE); // �۾���
		m_view.setForeground(Color.WHITE); // �۾���
		m_help.setForeground(Color.WHITE); // �۾���
		
		// (3) area ��Ÿ�� ����
		area.setBackground(Color.YELLOW);
		// font ��ü�� ����ϴ� ���� ���� �����غ���(1.�뵵�ľ�, 2.�޸𸮻������)
		area.setFont(new Font("Verdana", Font.BOLD|Font.ITALIC, 20));
		area.setForeground(Color.RED);
		
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		

	}
	public static void main(String[] args) {
		new MemoApp();
	}
}
