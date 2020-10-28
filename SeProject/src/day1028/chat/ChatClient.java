package day1028.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements KeyListener,ActionListener{
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt;
	JButton bt_open; // 새로시작(1) 대화상대방을 띄우는 버튼
	ChatClient2  ch2; // 새로시작(4)
	
	public ChatClient() {
		// 새로시작(3) 나 자신이 프레임 = 나보다 부모가 먼저 태어나야 함..super()
		super("난 부모창");
		// 생성
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(10);
		bt = new JButton("send");
		bt_open = new JButton("open");	// 새로시작(1) 대화상대방을 띄우는 버튼
		
		
		// 패널조립 - 패널은 디폴트가 FlowLayout
		p_south.add(t_input);
		p_south.add(bt);
		p_south.add(bt_open); // 새로시작(1) 아래창에 삽입!!
		
		add(scroll);	// 센터에 스크롤 부착
		add(p_south,BorderLayout.SOUTH);	// 남쪽에 패널 부착
		
		// 스타일 적용
		area.setBackground(Color.YELLOW);
		t_input.setBackground(Color.BLUE);
		t_input.setForeground(Color.WHITE);
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		t_input.setPreferredSize(new Dimension(250,30));
		
		// (**) 보여주기 전에 미리 연결해놓자(컴포넌트와 리스너 연결)
		//MyActionListener listener = new MyActionListener(t_input,area);
		//bt.addActionListener(listener);
		t_input.addKeyListener(this); // 내가 리스너다. -> 내가 윈도우에 있음
													// 프레임에 엔터를 치기 때문에!!!!
		
		// 새로시작(2) send버튼, open 버튼에 리스너 연결
		bt.addActionListener(this); // 현재 프레임이 곧 리스너이다.
		bt_open.addActionListener(this);
		
		
		//setSize(300,400); -> 해당 코드를 아래코드로 수정 = 내가 원하는 위치에 띄게
		setBounds(200,150,300,400);
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
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	// 새로시작(2) 버튼 눌렀을 때 이벤트를 구현해주는 메소드
	@Override
	public void actionPerformed(ActionEvent e) {
		// 광범위한 이벤트.. 어떤걸 이벤트 할지 모르니.. 이벤트를 반환해주는 메소드 사용
		Object obj = e.getSource();
		JButton btn = (JButton)obj; // 필수는 아니지만 가능하다를 보여주는 것!
		
		// 둘다 결과는 같다(=)
		if(btn==bt) {// 누른버튼과 send버튼의 주소값이 같다면?
			System.out.println("send 버튼 눌렀어");		
			send();
		}else if(btn.equals(bt_open)) {// 누른버튼과 open버튼의 내용이 같다면?
			System.out.println("open 버튼 눌렀어");	
			open();
		}		
	}
	// 새로시작(3) 메시지창에 대화 내용 누적하기 = 메소드 호출 만들기
	public void send() {
		// (4) 나에대한 채팅처리
		String msg = t_input.getText();		
		area.append(msg+"\n");
		t_input.setText(""); 
		
		// (4) 너에대한 채팅창.. -> 형님도 처리.class
		// msg = ch2.t_input.getText();	// 너의. 라는게 들어가야됨	
		ch2.area.append(msg+"\n");
		ch2.t_input.setText("");

	}
	// 새로시작(3) 메시지창에 상대방 윈도우 띄우기 = 메소드 호출 만들기
	public void open() {
		// ChatClient2 를 화면에 띄우기
		ch2 = new ChatClient2(this);	 // (5)생성자만 띄우면 다시는 쓸수 없기에 변수 선언	
	}
	public static void main(String[] args) {
		new ChatClient();
	}


}
