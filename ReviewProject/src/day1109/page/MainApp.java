package day1109.page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends JFrame implements ActionListener{
	JPanel p_north;
	JPanel p_center;
	
	JButton[] btn = new JButton[4];
	String[] title = {"Home","게시판","로그인","회원가입"};
	
	Home home;
	Board board;
	Login login;
	Member member;
	
	JPanel[] pages = new JPanel[4];
	public MainApp() {
		p_north = new JPanel();
		p_center = new JPanel();
		
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(title[i]);
			p_north.add(btn[i]);
			btn[i].addActionListener(this);
		}
		
		pages[0] = new Home();
		pages[1] = new Board();
		pages[2] = new Login();
		pages[3] = new Member();
		
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		
		pages[0].setPreferredSize(new Dimension(580,480));
		pages[1].setPreferredSize(new Dimension(580,480));
		pages[2].setPreferredSize(new Dimension(580,480));
		pages[3].setPreferredSize(new Dimension(580,480));
		
		p_center.add(pages[0]);
		p_center.add(pages[1]);
		p_center.add(pages[2]);
		p_center.add(pages[3]);
		
		setVisible(true);
		pack();	// 내용물의 크기만큼 수축!
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==btn[0]) {
			setPage(0);
		}else if(obj==btn[1]) {
			setPage(1);
		}else if(obj==btn[2]) {
			setPage(2);
		}else if(obj==btn[3]) {
			setPage(3);
		}
		
	}
	public void setPage(int index) {
		for (int i = 0; i < pages.length; i++) {
			// 내가 선택한 페이지 = index
			if(index==i) {
				pages[i].setVisible(true);
			}else {
				pages[i].setVisible(false);
			}
		}
	}
	public static void main(String[] args) {
		new MainApp();
	}
}
