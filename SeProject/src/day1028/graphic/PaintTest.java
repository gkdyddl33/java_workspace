package day1028.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
/*
 * 지금까지는 sun사의 개발자가 제공해준 그대로 컴포넌트들을 보아왔지만,
 * 이 예제에서는 우리가 컴포넌트의 그려지는 방식을 간섭하여, 원하는 그림으로 
 * 컴포넌트가 보여지도록 처리해보자!!
 */
public class PaintTest extends JFrame {
	MyCanvas can;	// (2) 도화지를 표현한 컴포넌트 -> (4) Canvas에서 수정
	
	public PaintTest() {
		can = new MyCanvas();
		
		// (3) 안보이니 백그라운드로 표현해보자.
		can.setBackground(Color.YELLOW);
		// (4) 캔버스에 그림을 그리려면, 캔버스가 스스로 그리는 메서드인 .paint()를 재정의해야한다.
		
		add(can);	// (2) 캔버스를 프레임에 부착
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	// 현재 클래스인 PaintTest가 JFrame의 paint.메소드를 오버라이드하면
	// 실행시 자식이 재정의한 메서드를 우선순위로 호출해준다.
	// 버튼은 방해하면 안되요 등..하려면 제대로 하자..그냥 방해하지마..
	// 그런데 컴포넌트 중 개발자가 주도해서 그림을 그릴 수 있는 컴포넌트..대상이나 등..
	// Canvas(AWT), JPanel(텅 비어있는 컴포넌트)
	
//	(1) @Override
//		public void paint(Graphics g) {//			
//			System.out.println("저 지금 직접 스스로를 그려요");
//		}
	public static void main(String[] args) {
		new PaintTest();
	}
}
