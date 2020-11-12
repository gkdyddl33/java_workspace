package day1111.board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BoardDetail2 extends JPanel{

	JTextField t_title;
	JTextField t_writer;
	JTextField content;
	JScrollPane scroll;
	
	
	JButton bt_list;
	JButton bt_edit;
	JButton bt_del;
	
	BoardApp2 boardApp;
	
	public BoardDetail2(BoardApp2 boardApp) {
this.boardApp=boardApp;
		
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
}
