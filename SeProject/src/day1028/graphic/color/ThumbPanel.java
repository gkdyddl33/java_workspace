package day1028.graphic.color;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


/*나만의 패널 정의하기 - 기존 패널의 커스터마이징(우리입맛에바꾸자) custom*/
public class ThumbPanel extends JPanel implements MouseListener{
	JPanel p_center;
	// 생성자 함수에 지역변수로 매개변수로 있기 때문에 사용 불가함으로 따로 보관
	Color color;
	
	public ThumbPanel(JPanel p_center,Color color) {// 새로 만들지 말고 기존의 만들어 놓은거를 가져오자
		this.p_center=p_center;
		this.color=color;
		
		// 너비,높이,색상을 가진 패널로 태어나게!
		this.setPreferredSize(new Dimension(100,100));	// 하나의 셀 크기
		this.setBackground(color); // 7가지 색깔을 줘야 하기 때문에 변수로 선언!
		//(2) 색상버튼  마우스이벤트!!
		this.addMouseListener(this); // 현재패널과 리스너와의 연결		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("클릭했어?");
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		// center 영역 패널의 배경색을 나(현재패널)와 같은 색상으로 설정하자!
		p_center.setBackground(color); // (나의 색상)		
	}

}
