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
 	BoardApp 가 모든 페이지들을 보유하고 있는 최상위 컨테이너 이므로.. 	
*/

public class BoardApp extends JFrame{

	JPanel p_north;
	JButton bt_board,bt_regist,bt_login;
	JPanel p_center;	
	private JPanel[] pages = new JPanel[5];	// 1) 화면전환에 사용될 패널을 담게 될
	BoardList boardList;
	
	// 5) 상수란? 변하지 않는 데이터에 의미를 부여하여 직관성은 높이기 위해 사용한다.
	public static final int BOARD_LIST=0;
	public static final int BOARD_WRITE=1;
	public static final int BOARD_DETAIL=2;
	public static final int MEMBER_REGIST=3;
	public static final int MEMBER_LOGIN=4;
	
	// 모든 페이지들이 사용할 접속정보 객체를 공통으로 선언
	// 이 커넥션 객체는 프로그램이 가동과 동시에 접속을 얻어다 놓자.
	private Connection con;
	
	// 아래 선언은 접속에 필요한 정보들!!!
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "user1104";
	private String pass = "user1104";
	
	// 로그인 상태 여부를 보관할 변수 -> Login을 한 후, 게시판 목록으로 갔을 때..
	// 로그인을 하지 않으면 게시판 목록이 보이지 않게 하자.
	private boolean hasSession = false;	// 로그인 안한 것..
	
	// 회원정보를 보관할 자료형? 클래스로 묶어서 사용해보자!!
	private BoardMember boardMember;	// 이 변수에 데이터가 채워지는 시점은?
												// 지금은 null.. 로그인 성공할 때 인스턴스 생성하자.
	
	public BoardApp() {
		// (*)프레임을 보여주기 직전에 데이터베이스 접속 성공해놓자 -> 그리고 윈도우 띄우기
		// con 이 null을 가지게 되므로 null을 가지지 않게 하기 위해서는 접속을 먼저 하고 실행하게끔
		this.getConnection();
		
		p_north = new JPanel();
		bt_board = new JButton(ImageUtil.getIcon(this.getClass(), "res/board.png", 100, 45));
		bt_regist = new JButton(ImageUtil.getIcon(this.getClass(), "res/register.png", 100, 45));
		bt_login = new JButton(ImageUtil.getIcon(this.getClass(), "res/login.png", 100, 45));
		p_center = new JPanel();
		
		pages[0] = new BoardList(this);	// 1) 게시판 목록페이지
		pages[1] = new BoardWrite(this);	// 2) 게시판 글쓰기페이지
		pages[2] = new BoardDetail(this);	// 3) 게시판 글쓰기페이지
		pages[3] = new RegistForm(this);	// 4) 회원관리
		pages[4] = new Login(this);	// 4) 회원관리
		
		// 조립
		p_north.add(bt_board);
		p_north.add(bt_regist);
		p_north.add(bt_login);
		
		p_center.add(pages[0]);  // 1) 중앙패널에 게시판 목록 붙여넣기
		p_center.add(pages[1]);  // 2) 중앙패널에 게시판 목록 붙여넣기
		p_center.add(pages[2]);  // 3) 중앙패널에 게시판 목록 붙여넣기
		p_center.add(pages[3]);  // 4) 중앙패널에 게시판 목록 붙여넣기
		p_center.add(pages[4]);  // 4) 중앙패널에 게시판 목록 붙여넣기
//		pages[0].setVisible(false);
//		pages[1].setVisible(false);
		
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		
		// 디폴트로 보여질 페이지와 안보이게 될 페이지에 대한 처리
		// 로그인을 하지 않으면 게시판 목록이 보이지 않게 하자.
		loginCheck(BoardApp.BOARD_LIST);
		
		setVisible(true);
		setSize(800,620);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				disConnection();// (*)접속해제
			}
		});
		
		// 게시판 버튼과 리스너연결
		bt_board.addActionListener((e)->{
			setPage(BOARD_LIST);
		});
		
		// 가입버튼과 리스너 연결
		bt_regist.addActionListener((e)->{
			// 해당 표현식 -> Lambda표현식 : 함수형 프로그램 표현식이다.
			// 참고) javascript 에서는 람다를 클로저라 한다.
			// 이벤트 구현시, 재 정의할 메서드가 달랑 1개인 경우 굳이, 내부익명클래스라는 표기를 
			// 거창하게 사용할 필요가 있는가?
			// 기능은 같지만 표기법이 간결해진다.
			// 람다표현식은 객체를 마치 함수처럼 간결하게 사용할 수 있도록 지원하는 표기법이다.
			setPage(MEMBER_REGIST);
		});
		// 로그인 버튼과 리스너 연결
		bt_login.addActionListener((e)->{
			setPage(MEMBER_LOGIN);
		});
		
	}
	// 4) 이 프로그램에서 사용되는 모든 페이지를 제어하는 메서드 = 1,2,3
	public void setPage(int showIndex) {
		// 보여주고 싶은 페이지 index를 넘겨받자.
		for(int i =0;i<pages.length;i++) {
			if(i==showIndex) {
				pages[i].setVisible(true);				
			}else {
				pages[i].setVisible(false);				
			}
		}
	}
	
	// 로그인 여부를 판단해서 만일 로그인하지 않았다면, 로그인 페이지 보여주기!
	public void loginCheck(int page) {
		if(hasSession==false) {
			// 로그인 하지 않은 상태임!!
			JOptionPane.showMessageDialog(this, "로그인이 필요한 서비스입니다.");
			setPage(BoardApp.MEMBER_LOGIN);
		}else {
			// 로그인 한 사람에게는, 원하는 페이지를 보여준다.
			setPage(page);
		}
	}
	
	// 접속을 시도하는 메서드 정의
	public void getConnection() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass); // 접속시도 후, 객체반환
			
			if(con==null) {
				JOptionPane.showMessageDialog(this, "데이터베이스에 접속하지 못했습니다.");
			}else {
				this.setTitle(user+"로 접속 중");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 접속을 해제하는 메서드 정의
	// 이 메서드는 윈도우 창을 닫을 때 호출될 예정임..
	public void disConnection() {
		// null이 아닐때만 닫아야 함, 만일 이런 확인 절차를 거치지 않으면 NullPointerException 발생할 수 있음
		if(con !=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}	
	
	// 다른 객체들이 접근할 수 있도록 get/set제공
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
	
	// 다른패키지에서 각 페이지를 접근할 수 있게 끔 getter 생성
	public JPanel getPages(int pageName) {
		return pages[pageName];
	}
	
	public static void main(String[] args) {
		new BoardApp();
	}
}
