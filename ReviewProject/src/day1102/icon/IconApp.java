package day1102.icon;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class IconApp extends JFrame{
	//버튼안에 이미지 넣기
	JButton bt;
	ImageIcon icon;
	
	public IconApp() {
		URL url = this.getClass().getClassLoader().getResource("res/twi.png");
		icon = new ImageIcon(url);	// icon경로 
		bt = new JButton(icon);
		
		Image img = icon.getImage();
		img = img.getScaledInstance(100, 45, Image.SCALE_SMOOTH);
		icon.setImage(img); // 크기를 조정해서 다시 넣는것임
		
		
		setLayout(new FlowLayout());
		add(bt);
		bt.setPreferredSize(new Dimension(100,45));
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new IconApp();
	}
}
