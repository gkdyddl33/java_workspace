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

/*3) �Խ��� ���� ������*/
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
	Board board=null;	// ����,�����ÿ��� �� �ȿ� ����ִ� �������� Ȱ���ϱ� ���� ������ �ƴ� ����� ������

	public BoardDetail(BoardApp boardApp) {
		this.boardApp=boardApp;
		con = boardApp.getCon();
		
		t_title = new JTextField();
		t_writer = new JTextField();
		content = new JTextField();
		scroll = new JScrollPane(content);
		bt_list = new JButton("��Ϻ���");
		bt_edit = new JButton("�ۼ���");
		bt_del = new JButton("�ۻ���");
		
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
				// ������� ���� ����
				boardApp.setPage(BoardApp.BOARD_LIST);
			}
		});
	}	
	
	// �Ѱ� ��������!! -> �Խø�� ������ Ŭ���ϸ� �󼼺���
	public void getDetail(int board_id) {
		// �� ���̺���� Ŭ���� ��������
//		Board board;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//String sql = "select * from board where board_id = ������ ������ board_id";
		String sql = "select * from board where board_id ="+board_id;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// ���ڵ尡 �ִٸ�?
				// create Empty object
				board = new Board();
				board.setBoard_id(rs.getInt("board_id"));				
				board.setTitle(rs.getString("title"));				
				board.setWriter(rs.getString("writer"));				
				board.setContent(rs.getString("content"));				
				board.setRegdate(rs.getString("regdate"));				
				board.setHit(rs.getInt("hit")); // ��ȸ���� ���ڴ�!	
				
				// ������ ä��� -> ����, �ۼ���, �󼼺���
				t_title.setText(board.getTitle());// �ν��Ͻ����� ��������
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
