package day1111.board;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BoardWrite2 extends JPanel{

	JTextField t_title;
	JTextField t_writer;
	JTextField content;
	JScrollPane scroll;
	
	JButton bt_regist;
	JButton bt_list;
	
	BoardApp2 boardApp;
	
	public BoardWrite2(BoardApp2 boardApp) {
		this.boardApp=boardApp;
		
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
		
		bt_list.addActionListener(new ActionListener() {// 글 목록 으로 되돌아가기		
			@Override
			public void actionPerformed(ActionEvent e) {
				boardApp.setPage(BoardApp.BOARD_LIST);				
			}
		});
	}
}
