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
	String[] item_title = {"새로만들기","새 창","열기","저장","다른이름으로 저장","페이지 설정","인쇄","끝내기"};
	
	public MemoApp() {
		bar = new JMenuBar();
		
		m_file = new JMenu("파일");
		m_edit = new JMenu("편집");
		m_style = new JMenu("서식");
		m_view = new JMenu("보기");
		m_help = new JMenu("도움말");
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
		setJMenuBar(bar); // bar 부착! 메뉴판 부착
		add(scroll);
		
		// 메뉴에 아이템 넣기
		
		items = new JMenuItem[item_title.length]; // 공간 확보
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
