package day1028;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient2 extends JFrame implements KeyListener{
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	ChatClient ch;
	
	public ChatClient2(ChatClient ch) {
		// 새로시작(5)형님 탄생
		this.ch = ch;
		// 생성
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(15);
		bt = new JButton("send");
		
		// 패널조립 - 패널은 디폴트가 FlowLayout
		p_south.add(t_input);
		p_south.add(bt);
		
		add(scroll);	// 센터에 스크롤 부착
		add(p_south,BorderLayout.SOUTH);	// 남쪽에 패널 부착
		
		// 스타일 적용
		area.setBackground(Color.GREEN);
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		t_input.setPreferredSize(new Dimension(265,30));
		
		// (**) 보여주기 전에 미리 연결해놓자(컴포넌트와 리스너 연결)
		//MyActionListener listener = new MyActionListener(t_input,area);
		//bt.addActionListener(listener);
		t_input.addKeyListener(this); // 내가 리스너다. -> 내가 윈도우에 있음
													// 프레임에 엔터를 치기 때문에!!!!
		
		//setSize(300,400);
		setBounds(500,150,300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// (*) key 이벤트 진행
	@Override
	public void keyPressed(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// 엔터키를 누르면, area에 입력 데이터를 반영하자~(누적시키자)
		int key = e.getKeyCode();	// (***) 키코드값 반환
		// System.out.println(key+"키 눌렀어?");
		if(key==10) {
			// (****)
			String msg = t_input.getText(); // 내용을 반환해주는 함수
			
			// System.out.println("area에 추가");
			area.append(msg+"\n");	// (****) 텍스트 필드 값을 구해서 추가를 하자.
			t_input.setText(""); 
		}
	}
	public void send() {// 새로시작(4)
		// 나에대한 처리
		String msg = t_input.getText();
		area.append(msg+"\n");
		t_input.setText("");
		
		// 형님창에 대한 처리
		ch.area.append(msg+"\n");
		ch.t_input.setText("");
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	// (5) 동생은 main이 필요없다.
}
