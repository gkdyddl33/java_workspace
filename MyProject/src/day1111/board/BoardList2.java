package day1111.board;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// ���Ϳ� �۸�� �Խù� ������
public class BoardList2 extends JPanel{

	JTable table;
	JScrollPane scroll;
	JButton bt;
	
	BoardApp2 boardApp;
	
	public BoardList2(BoardApp2 boardApp) {
		this.boardApp=boardApp;
		
		table = new JTable(8,5);
		scroll = new JScrollPane(table);
		bt = new JButton("�۵��");
		
		setLayout(new BorderLayout());
		add(scroll);
		add(bt,BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(780,500));		
		setVisible(true);
		
		bt.addActionListener(new ActionListener() {// �۵�� ȭ��		
			@Override
			public void actionPerformed(ActionEvent e) {
				boardApp.setPage(BoardApp2.BOARD_WRITE);
			}
		});
		table.addMouseListener(new MouseAdapter() {// �ۼ��� ȭ��
			@Override
			public void mouseReleased(MouseEvent e) {
				// ĭ�� ���콺�� ������ �Ѿ��
				boardApp.setPage(boardApp.BOARD_DETAIL);
			}
		});
	}
}
