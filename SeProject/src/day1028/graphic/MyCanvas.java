package day1028.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MyCanvas extends Canvas{
	// (5) 이미지 - 플랫폼(os or mac..)에 따라 알맞게 자원을 가져올 수 있도록 해주는 객체
	Toolkit kit;
	Image img;	// 이 아이도 추상이지만, Toolkit으로 얻어올 수 있다.
	
	public MyCanvas() {
		// (5) toolkit은 추상이미로 인스턴스를 반환해주는 메서드를 이용하자.
		kit = Toolkit.getDefaultToolkit();	// 자체 메서드를 통해 인스턴스 얻는 방식 
		img = kit.getImage("D://workspace//java_workspace//SeProject//res//travel2//aa.jpg");	// 하드디스트 경로를 넣을 수 있음=윈도우os
	}
	
	// (4) can에 대해 재정의를 하는 공간 - 즉, 부모인 캔버스가 보유한 paint메서드를
	// 무시해버리고, 우리 상황에 맞게 재정의 하자. = 버릇없게 올라온다 해서 over ride
	@Override
	public void paint(Graphics g) {// 그래픽은 추상클래스
		// 매개변수인 Graphics 객체는 현실에서의 팔레트와 비유할 수 있지만
		// 단지, 색상만 관여하는 것이 아니라 도형,텍스트,이미지,선,점 등 
		// 다양한 그래픽 처리를 위한 기능을 지원하는 실질적 그래픽 처리 담당자이다.
		g.drawLine(50, 50, 300, 350);  // 캔버스에 선 그리기
		g.drawOval(0, 50, 200, 200);   //  원 모양 그리기
		g.drawRect(50, 100, 100, 200);	// 사각형 그리기
		
		// 붓의 페인트 색상 변경
		g.setColor(Color.RED);
		g.fillRect(200, 100, 50, 50);
		
		g.setColor(Color.BLUE);		
		g.drawString("이건 그림글씨야.", 50, 200); // 글씨가 아닌 그림이 들어간것.
		
		// 이미지 넣기
		// Image : 이미지를 표현한 객체
		//				일반,추상,인터페이스? 추상클래스다.
		//				실망하지마라. 추상클래스인 경우, 개발자가 자식클래스로 구현 후 
		//				자식을 new 하는게 원칙이지만..
		//				sun사가 제공해주는 대부분의 추상클래스는 사실, 이미 인스턴스를 생성해 놓고
		//				간접적으로 쉽게 얻는 메서드를 제공해준다.
		// 시스템에 하드디스크 경로를 이용하여 이미지를 가져올 경우엔, 플랫폼을 통해
		// 이미지 객체를 가져올 수 있는 Toolkit객체를 이용해야 하므로,
		g.drawImage(img, 20, 20, this); // 마지막은 이미지 관찰자를 뜻
	}
}
