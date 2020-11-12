package day1111.json;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Movie2 extends JPanel implements Runnable{

	Image big;
	Image img;
	int width;
	int height;
	
	Thread thread;
	
	JsonGallery2 jsonGallery2;
	BufferedImage buffImg;
	String url;
	String title;
	String phase;
	String category_name;
	String release_year;
		
	public Movie2(JsonGallery2 jsonGallery2,int width,int height,String url,String title,String phase,String category_name,String release_year) {
		this.jsonGallery2=jsonGallery2;
		this.width=width;
		this.height=height;
		this.url=url;
		this.title=title;
		this.phase=phase;
		this.category_name=category_name;
		this.release_year = release_year;
		
		this.setPreferredSize(new Dimension(width,height));
		
		// 이미지 불러오기
		thread = new Thread(this);
		thread.start();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("클릭한 저의 영화 제목은 "+title);
				jsonGallery2.getDetail(big,title,phase,category_name,release_year);
			}
		});
	}
	
	public void getErrorImage() {
		URL url = this.getClass().getClassLoader().getResource("res/error.png");
		try {
			BufferedImage buffImg = ImageIO.read(url);
			img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				
		} catch (IOException e) {
			e.printStackTrace();			
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	public void run() {
		URL path;
		try {
			path = new URL(url);
			buffImg = ImageIO.read(path);
			big = buffImg.getScaledInstance(400, 550, Image.SCALE_SMOOTH);
			img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			jsonGallery2.p_south.updateUI();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("이미지를 찾을 수 없네요.");
			getErrorImage();
			
		}

	}
}
