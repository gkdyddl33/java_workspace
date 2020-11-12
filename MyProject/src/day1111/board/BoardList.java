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

/*1) �Խù� ��� ������*/
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
		bt = new JButton("�۵��");
		
		getList();		// ----------�Խù� �ֱ� ȣ��!!
		
		setLayout(new BorderLayout());
		add(scroll);
		add(bt,BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(780,500));		
		setVisible(true);
		
		bt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �۾��� ���� �ۼ��ڿ� ���̵� �־�α�
				BoardWrite page = (BoardWrite)boardApp.getPages(BoardApp.BOARD_WRITE);
				page.t_writer.setText(boardApp.getBoardMember().getM_id());
				
				// �۾��� �� �����ֱ�
				boardApp.setPage(BoardApp.BOARD_WRITE);
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// �󼼺��Ⱑ ������ getDetail() �޼��� ȣ���ϱ�!!
				// �Խø���� ĭ�� Ŭ���ϸ� �󼼺���� �Ѿ��! ���� �ȿ� ������ �����ָ� �ȴ�.
				// �װ� Detail Ŭ�������� �ذ�����
				BoardDetail boardDetail = (BoardDetail)boardApp.getPages(boardApp.BOARD_DETAIL);
				String board_id = (String)table.getValueAt(table.getSelectedRow(), 0); // ������ row, ��� �÷� 0
				boardDetail.getDetail(Integer.parseInt(board_id));
				
				// ĭ�� ���콺�� ������ �Ѿ��
				boardApp.setPage(boardApp.BOARD_DETAIL);
			}
		});
	}
	
	// �Խù� ��������!
	// rs�� ����� �����͸� TableModel�� ������ ������ �迭 data�� ����!
	public void getList() {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
				
		String sql = "select * from board";
		try {
			pstmt = con.prepareStatement(sql,
					// ��ũ���� �����ϰ�, �б� ������ rs�� ����� ���� �ɼ�!!
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();	// �� ���ڵ� �� ��ȯ
			
			// rs�� ����ִµ����͸� ������ �迭�� �Ű� �ɾ��.
			// �׷��� ���ؼ��� ������ �迭�� �غ��س���.
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
				
				records[index++]=arr;	// �� ä�� ������ �迭�� �������迭�� �濡 ����!				
			}
			// ������ �迭�� ��� �ϼ��Ǿ����Ƿ�, TableModel�� ������ data ������ �迭�� ����!
			boardModel.data = records;
			table.updateUI(); // ����
			
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
