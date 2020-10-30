package day1027.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoApp extends JFrame{
	JMenuBar bar;
	JMenu m_file,m_edit,m_style,m_view,m_help;
	JTextArea area;
	JScrollPane scroll;
	
	JMenuItem[] items;
	String[] item_title = {"���θ����","�� â","����","����","�ٸ��̸����� ����","������ ����","�μ�","������"};
	
	public MemoApp() {
		bar = new JMenuBar();
		
		m_file = new JMenu("����");
		m_edit = new JMenu("����");
		m_style = new JMenu("����");
		m_view = new JMenu("����");
		m_help = new JMenu("����");
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
		setJMenuBar(bar); // bar ����! �޴��� ����
		add(scroll);
		
		// �޴��� ������ �ֱ�
		
		items = new JMenuItem[item_title.length]; // ���� Ȯ��
		for (int i = 0; i < items.length; i++) {
			items[i] = new JMenuItem(item_title[i]);
			if(i==5||i==7) {
				m_file.addSeparator();
			}
			m_file.add(items[i]);
		}
		
		m_file.setPreferredSize(new Dimension(100,45));
		m_edit.setPreferredSize(new Dimension(100,45));
		m_style.setPreferredSize(new Dimension(100,45));
		m_view.setPreferredSize(new Dimension(100,45));
		m_help.setPreferredSize(new Dimension(100,45));
		
		bar.setBackground(Color.BLACK);
		m_file.setForeground(Color.WHITE);
		m_edit.setForeground(Color.WHITE);
		m_style.setForeground(Color.WHITE);
		m_view.setForeground(Color.WHITE);
		m_help.setForeground(Color.WHITE);
		
		area.setBackground(Color.YELLOW);
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
