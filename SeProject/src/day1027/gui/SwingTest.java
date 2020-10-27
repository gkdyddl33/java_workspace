package day1027.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/*AWT는 플랫폼에 따라 디자인이 다르게 표현되는 문제가 있기에, AWT를 개선한 API-Swing을 사용해본다.*/
/*Swing은 새롭게 배울필요 없으며, 기존 우리가 사용해오던 AWT컴포넌트 대부분 J접두어가 붙는다.*/
/*주의)AWT가 완전히 불필요한 패키지가 아니다. AWT 패키지의 event와 배치관리자는 여전히 AWT를 이용한다.*/
public class SwingTest extends JFrame{
	JCheckBox ch;
	JButton bt;
	
	public SwingTest() {
		ch = new JCheckBox("영화");
		bt = new JButton("나버튼");
		add(ch);
		add(bt);
		setLayout(new FlowLayout());
		setVisible(true);	// swing은 윈도우 창을 닫을 수 있다. false로 바뀌기 때문에
		// 위에 코드에 대한 해결책) 윈도우 창 닫으면 프로세스도 함께 종료된다.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(300,400);
	}
	public static void main(String[] args) {
		new SwingTest();
	}
	
}
