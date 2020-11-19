package com.swingmall.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.crypto.SecretKeyFactorySpi;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.swingmall.board.QnA;
import com.swingmall.home.Home;
import com.swingmall.member.Login;
import com.swingmall.member.MyPage;
import com.swingmall.product.Product;
import com.swingmall.util.db.DBManager;

public class ShopMain extends JFrame{
	// ★ 상수 선언
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 900;
	
	public static final int HOME = 0;
	public static final int PRODUCT = 1;
	public static final int QNA = 2;
	public static final int MYPAGE = 3;
	public static final int LOGIN = 4;

	JPanel p_navi;					// ★ 웹사이트의 주 "메뉴" 를 포함할 컨테이너 패널 
	String[] navi_title = {"Home","Product","QnA","MyPage","Login"};
	JButton[] navi = new JButton[navi_title.length];	// 배열생성 - 메뉴
	
	JPanel user_container;	// 관리자,사용자 측 화면을 구분지을 수 있는 컨테이너
	JPanel p_content;			// 여기에 모든 페이지가 미리 붙어져 있을 것임
										// 추후 showPage 메서드로 보일지 여부 설정..
	
	// ★ 버튼의 개수 대로 페이지 구성 = 현재는 5개
	Page[] page = new Page[5];
	
	JLabel la_footer;					// 윈도우 하단의 카피라이트 영역
	
	DBManager dbManager;		// 윈도우 접속 클래스 가져오기
	Connection con;	
	
	public ShopMain() {
		dbManager = new DBManager();
		p_navi = new JPanel();
		user_container = new JPanel();	
		p_content = new JPanel();
		la_footer = new JLabel("SwingMall All rights reserved",SwingConstants.CENTER);
		
		// 접속
		con=dbManager.connect();
		if(con == null) {
			JOptionPane.showMessageDialog(this, "데이터베이스에 접속할 수 없습니다.");
			System.exit(0);
		}else {
			this.setTitle("SwingShop에 오신걸 환영합니다.");
		}
		
		// ★ 1) 메인 네비게이션 생성
		for(int i=0;i<navi.length;i++) {
			navi[i] = new JButton(navi_title[i]);
			p_navi.add(navi[i]);
		}
		
		// ★ 2) 버튼의 개수 대로 페이지 구성 - shopMain을 인수로 넘겨받는다.
		page[0] = new Home(this);	
		page[1] = new Product(this);	
		page[2] = new QnA(this);	
		page[3] = new MyPage(this);	
		page[4] = new Login(this);			
		
		// 스타일
		user_container.setPreferredSize(new Dimension(WIDTH,HEIGHT-50));
		user_container.setBackground(Color.WHITE);
		la_footer.setPreferredSize(new Dimension(WIDTH,50));		
		la_footer.setFont(new Font("Arial Black",Font.BOLD,19));
		
		// 조립
		user_container.setLayout(new BorderLayout());		
		// 1) 모든 페이지를 미리 붙여놓자.
		for(int i=0;i<page.length;i++) {
			p_content.add(page[i]);			
		}
				
		user_container.add(p_navi,BorderLayout.NORTH);
		user_container.add(p_content);
		
		this.add(user_container);
		this.add(la_footer,BorderLayout.SOUTH);
		
		setSize(1200,900);
		setVisible(true);
		setLocationRelativeTo(null);
		
		// 프레임과 리스너 연결(접속해제)
		this.addWindowListener(new WindowAdapter() {			
			public void windowClosing(WindowEvent e) {
				dbManager.disConnect(con);
				System.exit(0);
			}
		});
		
		// 1) 네비게이션 버튼과 리스너 연결
		for(int i=0;i<navi.length;i++) {
			navi[i].addActionListener((e)->{
				Object obj = e.getSource();
				if(obj==navi[0]) {// home
					showPage(0);					
				} else if(obj==navi[1]) {
					showPage(1);
				} else if(obj==navi[2]) {
					showPage(2);
				} else if(obj==navi[3]) {
					showPage(3);
				} else if(obj==navi[4]) {
					showPage(4);
				}
			});
		}
	}
	
	// 1) (admin 에서 옮겨오기) 보여질 페이지와 안 보여질 페이지를 설정하는 메서드
	public void showPage(int showIndex) {// 매개변수로는 보여주고 싶은 페이지 넘버
		for(int i=0;i<page.length;i++) {
			if(i==showIndex) {
				page[i].setVisible(true);		// 보이게 할 페이지
			}else {
				page[i].setVisible(false);	// 안보이게 할 페이지				
			}			
		}
	}
	public static void main(String[] args) {
		new ShopMain();
	}
}
