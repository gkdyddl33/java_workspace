package day1111.board;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import common.image.ImageUtil;

public class BoardApp2 extends JFrame{

	JPanel p_north;
	JButton bt_board,bt_regist,bt_login;
	JPanel p_center;
	// 화면전환 패널을 담게 될 배열
	JPanel[] pages = new JPanel[5];
	BoardList2 boardList;
		
	public static final int BOARD_LIST=0;
	public static final int BOARD_WRITE=1;
	public static final int BOARD_DETAIL=2;
	public static final int MEMBER_REGIST=3;
	public static final int MEMBER_LOGIN=4;
	
	public BoardApp2() {
		p_north = new JPanel();
		bt_board = new JButton(ImageUtil.getIcon(this.getClass(), "res/board.png", 100, 45));
		bt_regist = new JButton(ImageUtil.getIcon(this.getClass(), "res/register.png", 100, 45));
		bt_login = new JButton(ImageUtil.getIcon(this.getClass(), "res/login.png", 100, 45));
		p_center = new JPanel();
		
		pages[0] = new BoardList2(this);	
		pages[1] = new BoardWrite2(this);	
		pages[2] = new BoardDetail2(this);
		
		p_north.add(bt_board);
		p_north.add(bt_regist);
		p_north.add(bt_login);
		
		p_center.add(pages[0]); 
		p_center.add(pages[1]); 
		p_center.add(pages[2]);
		
		add(p_north,BorderLayout.NORTH);
		add(p_center);		
		
		// 게시판 버튼과 리스너연결
		bt_board.addActionListener((e)->{
			setPage(BOARD_LIST);
		});
		
		setVisible(true);
		setSize(800,620);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setPage(int showIndex) {
		for(int i=0;i<pages.length;i++) {
			if(i==showIndex) {
				pages[i].setVisible(true);
			}else {
				pages[i].setVisible(false);
			}
		}
	}
	public static void main(String[] args) {
		new BoardApp2();
	}
}
