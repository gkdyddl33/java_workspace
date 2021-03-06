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

// 메모장만들기
public class MemoApp2 extends JFrame{
	JMenuBar bar;
	JMenu m_file,m_edit,m_style,m_view,m_help;
	JTextArea area;
	JScrollPane scroll;
	
	// (2) m_file에 들어갈 아이템 넣기
	JMenuItem[] items;	// 넣을 공간확보
	// 만들어 놓은 공간에 아이템 넣기
	String[] item_title = {"새로만들기","새 창","열기","저장","다른이름으로 저장","페이지 설정","인쇄","끝내기"};
	
	public MemoApp2() {
		// (1) 메뉴아이템이 들어갈 바 생성
		bar = new JMenuBar();
		// (1) 생성한 바에 차례로 넣을 메뉴 생성
		m_file = new JMenu("파일");
		m_edit = new JMenu("편집");
		m_style = new JMenu("서식");
		m_view = new JMenu("보기");
		m_help = new JMenu("도움말");
		
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
				
		// (2) 메뉴 bar에 item생성
		items = new JMenuItem[item_title.length]; // 공간확보 -> 들어갈 아이템 길이만큼
		for (int i = 0; i < items.length; i++) {
			items[i] = new JMenuItem(item_title[i]);
			// 구분선추가
			if(i==5|i==7) {
				m_file.addSeparator();	//separator 세퍼레이터 = 분리,격리
			}
			m_file.add(items[i]);			
		}
		
		// (1) 나머지 변수들도 생성
		area = new JTextArea();
		scroll = new JScrollPane(area);	// 스크롤은 달고 싶은 컴포넌트를 매개변수로 넣기
		add(scroll);
		this.setJMenuBar(bar);
		
		// (4) 메뉴의 크기 조정
		// 발탁된..크기조정..
		m_file.setPreferredSize(new Dimension(100,45)); // preferred 우선의, demension 크기,치수
		m_edit.setPreferredSize(new Dimension(100,45));
		m_style.setPreferredSize(new Dimension(100,45));
		m_view.setPreferredSize(new Dimension(100,45));
		m_help.setPreferredSize(new Dimension(100,45));
		
		// (3) 스타일 적용 bar
		bar.setBackground(Color.BLACK);
		m_file.setForeground(Color.WHITE); // foreground 전경,위치
		m_edit.setForeground(Color.WHITE);
		m_style.setForeground(Color.WHITE);
		m_view.setForeground(Color.WHITE);
		m_help.setForeground(Color.WHITE);
		
		// (3) 스타일 적용 area
		area.setBackground(Color.YELLOW);
		area.setFont(new Font("Verdana", Font.BOLD|Font.ITALIC, 20)); // 글꼴,스타일,크기
		area.setForeground(Color.RED);
		
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	public static void main(String[] args) {
		new MemoApp2();
	}

}
