package day1111.board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/*2) 게시판 글쓰기 페이지*/
public class BoardWrite extends JPanel {
	
	JTextField t_title;
	JTextField t_writer;
	JTextField content;
	JScrollPane scroll;
	
	JButton bt_regist;
	JButton bt_list;
	
	BoardApp boardApp;
	Connection con;
	
	public BoardWrite(BoardApp boardApp) {
		this.boardApp=boardApp;
		con = boardApp.getCon();
		
		t_title = new JTextField();
		t_writer = new JTextField();
		content = new JTextField();
		scroll = new JScrollPane(content);
		bt_regist = new JButton("글등록");
		bt_list = new JButton("목록보기");
		
		t_title.setPreferredSize(new Dimension(780,35));
		t_writer.setPreferredSize(new Dimension(780,35));
		scroll.setPreferredSize(new Dimension(780,200));
				
		add(t_title);
		add(t_writer);
		add(scroll);
		add(bt_regist);
		add(bt_list);
		
		setPreferredSize(new Dimension(780,500));
		setVisible(true);
		
		bt_list.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 목록으로 가기 구현
				boardApp.setPage(BoardApp.BOARD_LIST);
			}
		});
		// 글동록하기
		bt_regist.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				regist();
				// 목록보기 메서드도 같이 호출하기! =  BoardList의 getList();
				BoardList boardList = (BoardList)boardApp.getPages(boardApp.BOARD_LIST);
				boardList.getList();
			}
		});
	}	
	// 글 등록하기
	public void regist() {
		PreparedStatement pstmt=null;
		
		String sql="insert into board(board_id,title,writer,content)";
		sql+=" values(seq_board.nextval, ?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql); //sql 준비 
			//바인드 변수 지정 
			pstmt.setString(1, t_title.getText());
			pstmt.setString(2, t_writer.getText());	// BoardApp의 회원정보를 이용..
			pstmt.setString(3, content.getText());
			
			int result = pstmt.executeUpdate();//쿼리 실행
			if(result==0) {
				JOptionPane.showMessageDialog(this, "등록실패");
			}else {
				JOptionPane.showMessageDialog(this, "등록성공");
				boardApp.setPage(BoardApp.BOARD_LIST); //게시물 목록으로 이동 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
