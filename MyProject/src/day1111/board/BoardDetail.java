package day1111.board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/*3) 게시판 수정 페이지*/
public class BoardDetail extends JPanel {
	
	JTextField t_title;
	JTextField t_writer;
	JTextField content;
	JScrollPane scroll;
	
	
	JButton bt_list;
	JButton bt_edit;
	JButton bt_del;
	
	BoardApp boardApp;
	Connection con;
	Board board=null;	// 수정,삭제시에도 이 안에 들어있는 정보들을 활용하기 위해 지역이 아닌 멤버를 선택함

	public BoardDetail(BoardApp boardApp) {
		this.boardApp=boardApp;
		con = boardApp.getCon();
		
		t_title = new JTextField();
		t_writer = new JTextField();
		content = new JTextField();
		scroll = new JScrollPane(content);
		bt_list = new JButton("목록보기");
		bt_edit = new JButton("글수정");
		bt_del = new JButton("글삭제");
		
		t_title.setPreferredSize(new Dimension(780,35));
		t_writer.setPreferredSize(new Dimension(780,35));
		scroll.setPreferredSize(new Dimension(780,200));
				
		add(t_title);
		add(t_writer);
		add(scroll);
		add(bt_list);
		add(bt_edit);
		add(bt_del);
		
		setPreferredSize(new Dimension(780,500));
		setVisible(true);
		
		bt_list.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 목록으로 가기 구현
				boardApp.setPage(BoardApp.BOARD_LIST);
			}
		});
	}	
	
	// 한건 가져오기!! -> 게시목록 내용을 클릭하면 상세보기
	public void getDetail(int board_id) {
		// ★ 테이블생성 클래스 가져오기
//		Board board;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//String sql = "select * from board where board_id = 유저가 선택한 board_id";
		String sql = "select * from board where board_id ="+board_id;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 레코드가 있다면?
				// create Empty object
				board = new Board();
				board.setBoard_id(rs.getInt("board_id"));				
				board.setTitle(rs.getString("title"));				
				board.setWriter(rs.getString("writer"));				
				board.setContent(rs.getString("content"));				
				board.setRegdate(rs.getString("regdate"));				
				board.setHit(rs.getInt("hit")); // 조회수는 숫자다!	
				
				// 데이터 채우기 -> 제목, 작성자, 상세보기
				t_title.setText(board.getTitle());// 인스턴스에서 가져오기
				t_writer.setText(board.getWriter());
				content.setText(board.getContent());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
