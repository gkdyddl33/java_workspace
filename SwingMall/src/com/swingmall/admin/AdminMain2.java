package com.swingmall.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.swingmall.util.db.DBManager;

/*������ ȭ��*/
public class AdminMain2 extends JFrame{

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 900;
	
	public static final int HOME = 0;
	public static final int PRODUCT = 1; // ��ǰ����
	public static final int MEMBER = 2;		// ȸ������
	public static final int ORDER = 3;	// �ֹ�����
	public static final int BOARD = 4;		// �Խ��ǰ���
	
	JPanel p_navi;
	String[] navi_title = {"Home","��ǰ����","ȸ������","�ֹ�����","�Խ��ǰ���"};
	JButton[] navi = new JButton[navi_title.length];
	
	JPanel p_content;
	JPanel user_container;
	JLabel la_footer;
	
	Page[] page = new Page[5];	// ������ ����
	
	private DBManager dbManager;
	private Connection con;
	
	public AdminMain2() {
		dbManager = new DBManager();	// ������ ����
		p_navi = new JPanel();
		user_container = new JPanel();
		p_content = new JPanel();
		la_footer = new JLabel("SwingMall All rights reserved",SwingConstants.CENTER);
		
		// ���� �׺���̼� ����
		for(int i=0;i<navi.length;i++) {
			navi[i] = new JButton(navi_title[i]);
			navi[i].setBackground(Color.BLACK);
			navi[i].setForeground(Color.WHITE);
			p_navi.add(navi[i]);
		}
		
		user_container.setPreferredSize(new Dimension(WIDTH,HEIGHT-50));
		user_container.setBackground(Color.WHITE);
		la_footer.setPreferredSize(new Dimension(WIDTH,50));
		la_footer.setFont(new Font("Arial Black", Font.BOLD, 19));
		
		user_container.setLayout(new BorderLayout());
		for(int i=0;i<page.length;i++) { // ������ ����
			p_content.add(page[i]);
		}
		
		user_container.add(p_navi,BorderLayout.NORTH);
		user_container.add(p_content);
		
		this.add(user_container);
		this.add(la_footer,BorderLayout.SOUTH);
		
		setSize(1200,900);
		setVisible(true);
		setLocationRelativeTo(null);
		
		this.addWindowListener(new WindowAdapter() {// ��������
			@Override
			public void windowClosing(WindowEvent e) {
				dbManager.disConnect(con);
				System.exit(0);
			}
		});
		
		// �׺���̼� ��ư�� ������ ����
		for(int i=0;i<navi.length;i++) {
			navi[i].addActionListener((e)->{
				Object obj = e.getSource();
				if(obj==navi[0]) {
					showPage(0);
				}else if(obj==navi[1]) {
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
	public void showPage(int showIndex) {// ������ index
		for(int i=0;i<page.length;i++) {
			if(i==showIndex) {
				page[i].setVisible(true);
			}else {
				page[i].setVisible(false);
			}			
		}
	}
	// �ٸ� Ŭ�������� ������ �� �ֵ��� getter ����
	public Connection getCon() {
		return con;
	}
	
	public DBManager getDbManager() {
		return dbManager;
	}
	public static void main(String[] args) {
		new AdminMain2();
	}
}
