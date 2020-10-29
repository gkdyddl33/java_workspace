package day1029.graphic.image;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

// 상세이미지가 그려질 패널, 이 패널을 클래스로 별도로 정의하는 이유는?
// paint 메서드를 재정의 하기 위함
public class DetailPanel extends JPanel{
	private Image img;
	
	// 누군가가 이 메서드를 호출한 자는 어떤 이미지를 보여줄지를 매개변수로 넘기면 된다
	// 은닉화 = 캡슐화 private
	public void setImg(Image img) {
		this.img = img;
		//(3마지막) 그림 크기 조절	-> 조정된 이미지를 다시 재 대입
		this.img=this.img.getScaledInstance(770, 500, Image.SCALE_SMOOTH);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

}
