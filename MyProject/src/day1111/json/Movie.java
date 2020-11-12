package day1111.json;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Movie extends JPanel implements Runnable{

	Image big;	// 전달할 큰 이미지
	Image img;	// 작은 썸네일 그려질 이미지
	BufferedImage buffImg;
	
	int width;
	int height;
	
	Thread thread;
	
	JsonGallery jsonGallery;
	
	// 5) 이 객체는 한편의 영화를 표현하는 클래스이다.	
	String url;	
	String title;
	String phase;
	String category_name;
	String release_year;
	
	
	public Movie(JsonGallery jsonGallery,int width,int height,String url,String title,String phase,String category_name,String release_year) {// 경로는 그냥 바로 변하게 설정해주자.
		this.jsonGallery=jsonGallery;
		this.width=width;
		this.height=height;
		this.url=url;
		this.title=title;
		this.phase=phase;
		this.category_name=category_name;
		this.release_year = release_year;
		
		this.setPreferredSize(new Dimension(width,height));
		
		// 3) runnable을 구현한 객체를 인수로 넣어준다.
		thread = new Thread(this);
		thread.start();//생성과 동시에 쓰레드 동작 :  run이 동작이 된다.
		
		// 4) 현재 패널과 리스너 연결
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("클릭한 저의 영화제목은 "+title);
				jsonGallery.getDetail(big,title,phase,category_name,release_year);	// 6) 해당 메소드를 가지는 주체,주인장은 jsongallery
			}
		});
	}
	
	// 이미지를 여러개 찾는 동안 나타나는 에러를 방지하기 위해 메서드 생성
	public void getErrorImage() {
		URL url = this.getClass().getClassLoader().getResource("res/error.png");
		try {
			BufferedImage buffImg = ImageIO.read(url);
			img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	}
	
	// 그림그리기
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	
	// 3) run() -> 이미지를 불러오지 말고 우리가 원할때 부르자. thread
	@Override
	public void run() {
		// 1. 이미지가 로컬 하드에 있을 경우 -> Toolkit 사용!		
		// 2. 이미지가 클래스패스상 즉, 패키지에 있을 경우 -> ClassLoader() 사용
		// 3. BufferedImage > ImageIO
		try {
			// 메모리에 올린상태..
			// "https://images-na.ssl-images-amazon.com/images/I/91qvAndeVYL._RI_.jpg"
			URL path = new URL(url);
			buffImg = ImageIO.read(path);
			big = buffImg.getScaledInstance(400, 550, Image.SCALE_SMOOTH);
			img = buffImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			jsonGallery.p_south.updateUI();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("이미지를 찾을 수 없네요");
			getErrorImage();	// 2-2) 이미지 로드하는 동안 이미지 못찾는 에러 발생처리(이미지넣기)
		}		
	}

}
