package day1028.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MyCanvas extends Canvas{// 빈 도화지
	Toolkit kit;
	Image img;
	
	public MyCanvas() {
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage("D://workspace//java_workspace//SeProject//res//travel2//aa.jpg");
		
	}
	@Override
	public void paint(Graphics g) {
		g.drawLine(50, 50, 300, 350);
		g.drawOval(0, 50, 200, 200);
		
		g.setColor(Color.RED);
		g.fillRect(200, 100, 50, 50);
		
		g.setColor(Color.BLUE);
		g.drawString("이건 그림글씨야", 50, 200);
		
		g.drawImage(img, 0, 0, this);
	}
}
