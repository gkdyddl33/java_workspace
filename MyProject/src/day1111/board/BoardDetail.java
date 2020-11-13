package day1111.board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
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
				// ����
				BoardList boardList = (BoardList)boardApp.getPages(BoardApp.BOARD_LIST);
				boardList.getList();// ����Ʈ ����
				
				// ������� ���� ����
				boardApp.setPage(BoardApp.BOARD_LIST);
			}
		});
		
		// ** ������ư�� ������ ����
		bt_del.addActionListener((e)->{
			int res = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?");
			if(res==JOptionPane.OK_OPTION) {// ok�ɼ��̶��..
				int result = del(board.getBoard_id());
				if(result==0) {
					JOptionPane.showMessageDialog(this, "��������");
				}else {
					JOptionPane.showMessageDialog(this, "��������");
					BoardList boardList = (BoardList)boardApp.getPages(BoardApp.BOARD_LIST);
					boardList.getList();// ����Ʈ ����
					//������� �����ֱ�
					boardApp.setPage(BoardApp.BOARD_LIST);
				}
			}
		});
		
		// ** ������ư�� ������ ����
		bt_edit.addActionListener((e)->{
			int result = edit(board);
			if(result==0) {
				JOptionPane.showMessageDialog(this, "��������");
			}else {
				JOptionPane.showMessageDialog(this, "��������");			
				BoardList boardList = (BoardList)boardApp.getPages(BoardApp.BOARD_LIST);
				boardList.getList();// ����Ʈ ����
				//������� �����ֱ�
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
	public int del(int board_id) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "delete from board where board_id="+board_id;
		try {
			pstmt = con.prepareStatement(sql);
			result = pstmt.executeUpdate();			
/*			if(result==0) {
				JOptionPane.showMessageDialog(this, "��������");
			}else {
				JOptionPane.showMessageDialog(this, "��������");
				BoardList boardList = (BoardList)boardApp.getPages(BoardApp.BOARD_LIST);
				boardList.getList();// ����Ʈ ����
				//������� �����ֱ�
				boardApp.setPage(BoardApp.BOARD_LIST);
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public int edit(Board board) {// ������ �������� ����, 1���� �Խù��� ����ִ� �ν��Ͻ��� ��������.
		PreparedStatement pstmt=null;
		int result=0;
		String sql = "update board set title =?,content=? where board_id=?"; // ����� board_id
		
		try {
			pstmt = con.prepareStatement(sql);
			// ���� board�� �ƴ϶� �츮�� �Է��� ���� ��ü!!
			pstmt.setString(1, t_title.getText());
			pstmt.setString(2, content.getText());
			pstmt.setInt(3, board.getBoard_id());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
		
	}
	// ��ȸ�� hit ����
	public void updateHit(int board_id) {
		PreparedStatement pstmt = null;
		String sql = "update board set hit =hit+1 where board_id="+board_id;	// board_id=��������
		
		try {
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
