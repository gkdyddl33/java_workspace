package day1111.board;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*1) 게시물 목록 페이지*/
public class BoardList extends JPanel{

	JTable table;
	JScrollPane scroll;
	JButton bt;
	
	BoardApp boardApp;
	BoardModel boardModel;
	
	Connection con;
	
	public BoardList(BoardApp boardApp) {
		this.boardApp=boardApp;
		con = boardApp.getCon();
		
		table = new JTable(boardModel=new BoardModel());
		scroll = new JScrollPane(table);
		bt = new JButton("글등록");
		
		getList();		// ----------게시물 넣기 호출!!
		
		setLayout(new BorderLayout());
		add(scroll);
		add(bt,BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(780,500));		
		setVisible(true);
		
		bt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 글쓰기 폼의 작성자에 아이디 넣어두기
				BoardWrite page = (BoardWrite)boardApp.getPages(BoardApp.BOARD_WRITE);
				page.t_writer.setText(boardApp.getBoardMember().getM_id());
				
				// 글쓰기 폼 보여주기
				boardApp.setPage(BoardApp.BOARD_WRITE);
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// 상세보기가 보유한 getDetail() 메서드 호출하기!!
				// 게시목록의 칸을 클릭하면 상세보기로 넘어간다! 이제 안에 내용을 보여주면 된다.
				// 그건 Detail 클래스에서 해결하자
				BoardDetail boardDetail = (BoardDetail)boardApp.getPages(boardApp.BOARD_DETAIL);
				String board_id = (String)table.getValueAt(table.getSelectedRow(), 0); // 선택한 row, 모든 컬럼 0
				boardDetail.getDetail(Integer.parseInt(board_id));
				
				// 칸을 마우스로 누르면 넘어가기
				boardApp.setPage(boardApp.BOARD_DETAIL);
			}
		});
	}
	
	// 게시물 가져오기!
	// rs에 담겨진 데이터를 TableModel이 보유한 이차원 배열 data에 대입!
	public void getList() {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
				
		String sql = "select * from board";
		try {
			pstmt = con.prepareStatement(sql,
					// 스크롤이 가능하고, 읽기 전용의 rs를 만들기 위한 옵션!!
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();	// 총 레코드 수 반환
			
			// rs에 들어있는데이터를 이차원 배열로 옮겨 심어보자.
			// 그러기 위해서는 이차원 배열을 준비해놓자.
			String[][] records = new String[total][5];			
			rs.beforeFirst();
			int index = 0;
			while(rs.next()) {
				String[] arr = new String[5];
				arr[0] = rs.getString("board_id");
				arr[1] = rs.getString("title");
				arr[2] = rs.getString("writer");
				arr[3] = rs.getString("regdate");
				arr[4] = rs.getString("hit");
				
				records[index++]=arr;	// 다 채운 일차원 배열을 이차원배열의 방에 담음!				
			}
			// 이차원 배열이 모두 완성되었으므로, TableModel이 보유한 data 이차원 배열에 대입!
			boardModel.data = records;
			table.updateUI(); // 갱신
			
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
