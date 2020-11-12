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

/*2) �Խ��� �۾��� ������*/
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
		bt_regist = new JButton("�۵��");
		bt_list = new JButton("��Ϻ���");
		
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
				// ������� ���� ����
				boardApp.setPage(BoardApp.BOARD_LIST);
			}
		});
		// �۵����ϱ�
		bt_regist.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				regist();
				// ��Ϻ��� �޼��嵵 ���� ȣ���ϱ�! =  BoardList�� getList();
				BoardList boardList = (BoardList)boardApp.getPages(boardApp.BOARD_LIST);
				boardList.getList();
			}
		});
	}	
	// �� ����ϱ�
	public void regist() {
		PreparedStatement pstmt=null;
		
		String sql="insert into board(board_id,title,writer,content)";
		sql+=" values(seq_board.nextval, ?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql); //sql �غ� 
			//���ε� ���� ���� 
			pstmt.setString(1, t_title.getText());
			pstmt.setString(2, t_writer.getText());	// BoardApp�� ȸ�������� �̿�..
			pstmt.setString(3, content.getText());
			
			int result = pstmt.executeUpdate();//���� ����
			if(result==0) {
				JOptionPane.showMessageDialog(this, "��Ͻ���");
			}else {
				JOptionPane.showMessageDialog(this, "��ϼ���");
				boardApp.setPage(BoardApp.BOARD_LIST); //�Խù� ������� �̵� 
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
