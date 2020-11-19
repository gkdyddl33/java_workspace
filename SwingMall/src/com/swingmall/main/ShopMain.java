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
	// �� ��� ����
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 900;
	
	public static final int HOME = 0;
	public static final int PRODUCT = 1;
	public static final int QNA = 2;
	public static final int MYPAGE = 3;
	public static final int LOGIN = 4;

	JPanel p_navi;					// �� ������Ʈ�� �� "�޴�" �� ������ �����̳� �г� 
	String[] navi_title = {"Home","Product","QnA","MyPage","Login"};
	JButton[] navi = new JButton[navi_title.length];	// �迭���� - �޴�
	
	JPanel user_container;	// ������,����� �� ȭ���� �������� �� �ִ� �����̳�
	JPanel p_content;			// ���⿡ ��� �������� �̸� �پ��� ���� ����
										// ���� showPage �޼���� ������ ���� ����..
	
	// �� ��ư�� ���� ��� ������ ���� = ����� 5��
	Page[] page = new Page[5];
	
	JLabel la_footer;					// ������ �ϴ��� ī�Ƕ���Ʈ ����
	
	DBManager dbManager;		// ������ ���� Ŭ���� ��������
	Connection con;	
	
	public ShopMain() {
		dbManager = new DBManager();
		p_navi = new JPanel();
		user_container = new JPanel();	
		p_content = new JPanel();
		la_footer = new JLabel("SwingMall All rights reserved",SwingConstants.CENTER);
		
		// ����
		con=dbManager.connect();
		if(con == null) {
			JOptionPane.showMessageDialog(this, "�����ͺ��̽��� ������ �� �����ϴ�.");
			System.exit(0);
		}else {
			this.setTitle("SwingShop�� ���Ű� ȯ���մϴ�.");
		}
		
		// �� 1) ���� �׺���̼� ����
		for(int i=0;i<navi.length;i++) {
			navi[i] = new JButton(navi_title[i]);
			p_navi.add(navi[i]);
		}
		
		// �� 2) ��ư�� ���� ��� ������ ���� - shopMain�� �μ��� �Ѱܹ޴´�.
		page[0] = new Home(this);	
		page[1] = new Product(this);	
		page[2] = new QnA(this);	
		page[3] = new MyPage(this);	
		page[4] = new Login(this);			
		
		// ��Ÿ��
		user_container.setPreferredSize(new Dimension(WIDTH,HEIGHT-50));
		user_container.setBackground(Color.WHITE);
		la_footer.setPreferredSize(new Dimension(WIDTH,50));		
		la_footer.setFont(new Font("Arial Black",Font.BOLD,19));
		
		// ����
		user_container.setLayout(new BorderLayout());		
		// 1) ��� �������� �̸� �ٿ�����.
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
		
		// �����Ӱ� ������ ����(��������)
		this.addWindowListener(new WindowAdapter() {			
			public void windowClosing(WindowEvent e) {
				dbManager.disConnect(con);
				System.exit(0);
			}
		});
		
		// 1) �׺���̼� ��ư�� ������ ����
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
	
	// 1) (admin ���� �Űܿ���) ������ �������� �� ������ �������� �����ϴ� �޼���
	public void showPage(int showIndex) {// �Ű������δ� �����ְ� ���� ������ �ѹ�
		for(int i=0;i<page.length;i++) {
			if(i==showIndex) {
				page[i].setVisible(true);		// ���̰� �� ������
			}else {
				page[i].setVisible(false);	// �Ⱥ��̰� �� ������				
			}			
		}
	}
	public static void main(String[] args) {
		new ShopMain();
	}
}
