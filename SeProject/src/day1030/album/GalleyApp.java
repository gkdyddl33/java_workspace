package day1030.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class GalleyApp extends JFrame implements ActionListener{
	// (1) 서쪽에 나올 썸네일
	JPanel p_west;
	JPanel p_center;
	JScrollPane scroll;
	
	// (4) 센터에 나올 제목 라벨과 컨버스 에 그리는 이미지
	JLabel la_name;
	XCanvas can;
	JPanel p_south;
	JButton bt_prev;
	JButton bt_next;
	
	// (2) 썸네일 배열선언
	ArrayList<Thumb> list = new ArrayList<>();
	
	// (3) 이미지 배열
	String dir = "D:/workspace/java_workspace/SeProject/res/travel2/";
	String[] src = {
			"aa.jpg","ab.jpg","ax.jpg","bm.jpg","et.jpg","kg.jpg","mx.jpg",
			"pk.jpg","ub.jpg","ya.jpg"
	};

	// (5) 
	int n = 0; //배열의 index
	
	public GalleyApp() {
		super("자바 갤러리");
		
		// (1)
		p_west = new JPanel();
		p_center = new JPanel();
		scroll = new JScrollPane(p_west);
		
		// (4) 
		la_name = new JLabel(src[n],SwingConstants.CENTER); // 가운데 맞춤
		can = new XCanvas(dir+src[n]);
		p_south = new JPanel();
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
				
		
		// (2) 썸네일 생성 -> * 이미지경로 매개변수를 받아서 넘겨줄 매개변수 넣기
		for (int i = 0; i < src.length; i++) { // 10->src.length
			Thumb thumb=null;
			list.add(thumb = new Thumb(dir+src[i],this)); // 기존에 만들고 그걸 서쪽에 넣자
			p_west.add(thumb); // -> 이미지가 밑에 숨어서 scroll로 보이게 하자
		}
		
		// (1)
		p_west.setPreferredSize(new Dimension(100,600));
		p_center.setPreferredSize(new Dimension(700,600));
		p_west.setBackground(Color.YELLOW);
		p_center.setBackground(Color.GREEN);
		
//		add(p_west,BorderLayout.WEST);
		add(scroll,BorderLayout.WEST);
		add(p_center);
		
		//(4)
		la_name.setPreferredSize(new Dimension(700,50));
		la_name.setBackground(Color.YELLOW);
		la_name.setFont(new Font("Verdana", Font.BOLD, 25));
		p_south.add(bt_prev); // 현재 인라인 상태 -> 블럭으로 떨어트리자 사이즈를 늘려서
		p_south.add(bt_next);		
		p_center.add(la_name);
		p_center.add(can);
		p_center.add(p_south);
		
		// (5)
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		// (7) 
		
				
		// 윈도우를 화면 중앙에 띄우는 법
		setSize(800,600);
		setVisible(true);
		setLocationRelativeTo(null);  // 상대의 위치를 놓을 것이다.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//updateData();
	}
	
	// (5) 버튼 이벤트!!! ->XCanvas
@Override 
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bt_prev) {
			prev();
		}else if(obj==bt_next) {
			next();
		}
	}
// (6) 중복되는 것은 아예 빼놓고 사용하자.
	public void updateData() {
		can.setSrc(dir+src[n]);
		can.repaint();
		// (6) 제목변경
		la_name.setText(src[n]+"("+(n+1)+"/"+src.length+")");
	}
	public void prev() {
		if(n>=1) {
			n--;			
		}else {
			JOptionPane.showMessageDialog(this, "이전 이미지가 없습니다.");
		}
		updateData();
	}
	public void next() {
		// 그림은 XCanvas가 담당하므로, 그려질 img를 변경시켜주고 다시 그리라고 하면됨.
		// 배열을 넘어서지 않는 범위내에서 ++허용
		if(n<src.length-1) {
			n++;			
		}else {
			JOptionPane.showMessageDialog(this, "마지막 이미지입니다.");
		}		
	}

	public static void main(String[] args) {
		new GalleyApp();
	}
}
