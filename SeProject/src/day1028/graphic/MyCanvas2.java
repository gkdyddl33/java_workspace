package day1028.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MyCanvas2 extends Canvas{
	Toolkit kit;
	Image img;
	
	// 생성자 초기화에 선언
	public MyCanvas2() {
		kit = Toolkit.getDefaultToolkit(); // 이미지 인스턴스를 반환해주는 얻는 방식
		img = kit.getImage("D://workspace//java_workspace//SeProject//res//travel2//aa.jpg");
	}
	@Override
	public void paint(Graphics g) {// 그래픽 = 팔레트 = 그래픽 처리 담당자..
		g.drawLine(50, 50, 300, 350);	// 선
		g.drawOval(0, 50, 200, 200);		// 원
		g.drawRect(50, 100, 100, 200);  // 사각형
		
		// 색상 변경
		g.setColor(Color.RED);
		g.fillRect(200, 100, 50, 50); // 네모난 사각형안에 색깔이 변경됨
		
		g.setColor(Color.BLUE);
		g.drawString("이건 그림글씨야", 50, 200);
		
		// 이미지 =  추상 = Toolkit을 생각하자.
		g.drawImage(img, 20, 20, this);
	}
	
}
