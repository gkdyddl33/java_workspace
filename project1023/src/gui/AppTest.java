package gui;
/*
 *	지금까지는 콘솔에서만 자바를 실행시켰으나,
 *	자바도 그래픽 API를 지원한다.(인터페이스)
 *	오늘은 맛만 보자.. 자바의 윈도우와 버튼을 구경해보자. 
 */
import java.awt.Frame;
import java.awt.Button;

public class AppTest {
	public static void main(String[] args) {
		// 자바에서는 윈도우의 역할을 해주는 클래스로 Frame을 지원
		Frame frame;
		frame = new Frame();	// 윈도우 생성
		frame.setSize(300, 400);// 생성된 윈도우에 너비,높이 주기
		frame.setVisible(true); // 프레임의 속성을 보이게 처리
		
		// 버튼생성하여 프레임에 붙이기
		Button bt = new Button("왕버튼");
		frame.add(bt);
	}
}
