package day1111.board;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.image.ImageUtil;
import day1111.member.BoardMember;
import day1111.member.Login;
import day1111.member.RegistForm;

/*
 	BoardApp �� ��� ���������� �����ϰ� �ִ� �ֻ��� �����̳� �̹Ƿ�.. 	
*/

public class BoardApp extends JFrame{

	JPanel p_north;
	JButton bt_board,bt_regist,bt_login;
	JPanel p_center;	
	private JPanel[] pages = new JPanel[5];	// 1) ȭ����ȯ�� ���� �г��� ��� ��
	BoardList boardList;
	
	// 5) �����? ������ �ʴ� �����Ϳ� �ǹ̸� �ο��Ͽ� �������� ���̱� ���� ����Ѵ�.
	public static final int BOARD_LIST=0;
	public static final int BOARD_WRITE=1;
	public static final int BOARD_DETAIL=2;
	public static final int MEMBER_REGIST=3;
	public static final int MEMBER_LOGIN=4;
	
	// ��� ���������� ����� �������� ��ü�� �������� ����
	// �� Ŀ�ؼ� ��ü�� ���α׷��� ������ ���ÿ� ������ ���� ����.
	private Connection con;
	
	// �Ʒ� ������ ���ӿ� �ʿ��� ������!!!
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "user1104";
	private String pass = "user1104";
	
	// �α��� ���� ���θ� ������ ���� -> Login�� �� ��, �Խ��� ������� ���� ��..
	// �α����� ���� ������ �Խ��� ����� ������ �ʰ� ����.
	private boolean hasSession = false;	// �α��� ���� ��..
	
	// ȸ�������� ������ �ڷ���? Ŭ������ ��� ����غ���!!
	private BoardMember boardMember;	// �� ������ �����Ͱ� ä������ ������?
												// ������ null.. �α��� ������ �� �ν��Ͻ� ��������.
	
	public BoardApp() {
		// (*)�������� �����ֱ� ������ �����ͺ��̽� ���� �����س��� -> �׸��� ������ ����
		// con �� null�� ������ �ǹǷ� null�� ������ �ʰ� �ϱ� ���ؼ��� ������ ���� �ϰ� �����ϰԲ�
		this.getConnection();
		
		p_north = new JPanel();
		bt_board = new JButton(ImageUtil.getIcon(this.getClass(), "res/board.png", 100, 45));
		bt_regist = new JButton(ImageUtil.getIcon(this.getClass(), "res/register.png", 100, 45));
		bt_login = new JButton(ImageUtil.getIcon(this.getClass(), "res/login.png", 100, 45));
		p_center = new JPanel();
		
		pages[0] = new BoardList(this);	// 1) �Խ��� ���������
		pages[1] = new BoardWrite(this);	// 2) �Խ��� �۾���������
		pages[2] = new BoardDetail(this);	// 3) �Խ��� �۾���������
		pages[3] = new RegistForm(this);	// 4) ȸ������
		pages[4] = new Login(this);	// 4) ȸ������
		
		// ����
		p_north.add(bt_board);
		p_north.add(bt_regist);
		p_north.add(bt_login);
		
		p_center.add(pages[0]);  // 1) �߾��гο� �Խ��� ��� �ٿ��ֱ�
		p_center.add(pages[1]);  // 2) �߾��гο� �Խ��� ��� �ٿ��ֱ�
		p_center.add(pages[2]);  // 3) �߾��гο� �Խ��� ��� �ٿ��ֱ�
		p_center.add(pages[3]);  // 4) �߾��гο� �Խ��� ��� �ٿ��ֱ�
		p_center.add(pages[4]);  // 4) �߾��гο� �Խ��� ��� �ٿ��ֱ�
//		pages[0].setVisible(false);
//		pages[1].setVisible(false);
		
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		
		// ����Ʈ�� ������ �������� �Ⱥ��̰� �� �������� ���� ó��
		// �α����� ���� ������ �Խ��� ����� ������ �ʰ� ����.
		loginCheck(BoardApp.BOARD_LIST);
		
		setVisible(true);
		setSize(800,620);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				disConnection();// (*)��������
			}
		});
		
		// �Խ��� ��ư�� �����ʿ���
		bt_board.addActionListener((e)->{
			setPage(BOARD_LIST);
		});
		
		// ���Թ�ư�� ������ ����
		bt_regist.addActionListener((e)->{
			// �ش� ǥ���� -> Lambdaǥ���� : �Լ��� ���α׷� ǥ�����̴�.
			// ����) javascript ������ ���ٸ� Ŭ������ �Ѵ�.
			// �̺�Ʈ ������, �� ������ �޼��尡 �޶� 1���� ��� ����, �����͸�Ŭ������� ǥ�⸦ 
			// ��â�ϰ� ����� �ʿ䰡 �ִ°�?
			// ����� ������ ǥ����� ����������.
			// ����ǥ������ ��ü�� ��ġ �Լ�ó�� �����ϰ� ����� �� �ֵ��� �����ϴ� ǥ����̴�.
			setPage(MEMBER_REGIST);
		});
		// �α��� ��ư�� ������ ����
		bt_login.addActionListener((e)->{
			setPage(MEMBER_LOGIN);
		});
		
	}
	// 4) �� ���α׷����� ���Ǵ� ��� �������� �����ϴ� �޼��� = 1,2,3
	public void setPage(int showIndex) {
		// �����ְ� ���� ������ index�� �Ѱܹ���.
		for(int i =0;i<pages.length;i++) {
			if(i==showIndex) {
				pages[i].setVisible(true);				
			}else {
				pages[i].setVisible(false);				
			}
		}
	}
	
	// �α��� ���θ� �Ǵ��ؼ� ���� �α������� �ʾҴٸ�, �α��� ������ �����ֱ�!
	public void loginCheck(int page) {
		if(hasSession==false) {
			// �α��� ���� ���� ������!!
			JOptionPane.showMessageDialog(this, "�α����� �ʿ��� �����Դϴ�.");
			setPage(BoardApp.MEMBER_LOGIN);
		}else {
			// �α��� �� ������Դ�, ���ϴ� �������� �����ش�.
			setPage(page);
		}
	}
	
	// ������ �õ��ϴ� �޼��� ����
	public void getConnection() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass); // ���ӽõ� ��, ��ü��ȯ
			
			if(con==null) {
				JOptionPane.showMessageDialog(this, "�����ͺ��̽��� �������� ���߽��ϴ�.");
			}else {
				this.setTitle(user+"�� ���� ��");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ������ �����ϴ� �޼��� ����
	// �� �޼���� ������ â�� ���� �� ȣ��� ������..
	public void disConnection() {
		// null�� �ƴҶ��� �ݾƾ� ��, ���� �̷� Ȯ�� ������ ��ġ�� ������ NullPointerException �߻��� �� ����
		if(con !=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}	
	
	// �ٸ� ��ü���� ������ �� �ֵ��� get/set����
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	
	
	public boolean isHasSession() {
		return hasSession;
	}
	public void setHasSession(boolean hasSession) {
		this.hasSession = hasSession;
	}
	public BoardMember getBoardMember() {
		return boardMember;
	}
	public void setBoardMember(BoardMember boardMember) {
		this.boardMember = boardMember;
	}
	
	// �ٸ���Ű������ �� �������� ������ �� �ְ� �� getter ����
	public JPanel getPages(int pageName) {
		return pages[pageName];
	}
	
	public static void main(String[] args) {
		new BoardApp();
	}
}
