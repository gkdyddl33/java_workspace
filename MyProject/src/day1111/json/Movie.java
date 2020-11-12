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

	Image big;	// ������ ū �̹���
	Image img;	// ���� ����� �׷��� �̹���
	BufferedImage buffImg;
	
	int width;
	int height;
	
	Thread thread;
	
	JsonGallery jsonGallery;
	
	// 5) �� ��ü�� ������ ��ȭ�� ǥ���ϴ� Ŭ�����̴�.	
	String url;	
	String title;
	String phase;
	String category_name;
	String release_year;
	
	
	public Movie(JsonGallery jsonGallery,int width,int height,String url,String title,String phase,String category_name,String release_year) {// ��δ� �׳� �ٷ� ���ϰ� ����������.
		this.jsonGallery=jsonGallery;
		this.width=width;
		this.height=height;
		this.url=url;
		this.title=title;
		this.phase=phase;
		this.category_name=category_name;
		this.release_year = release_year;
		
		this.setPreferredSize(new Dimension(width,height));
		
		// 3) runnable�� ������ ��ü�� �μ��� �־��ش�.
		thread = new Thread(this);
		thread.start();//������ ���ÿ� ������ ���� :  run�� ������ �ȴ�.
		
		// 4) ���� �гΰ� ������ ����
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Ŭ���� ���� ��ȭ������ "+title);
				jsonGallery.getDetail(big,title,phase,category_name,release_year);	// 6) �ش� �޼ҵ带 ������ ��ü,�������� jsongallery
			}
		});
	}
	
	// �̹����� ������ ã�� ���� ��Ÿ���� ������ �����ϱ� ���� �޼��� ����
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
	
	// �׸��׸���
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	
	// 3) run() -> �̹����� �ҷ����� ���� �츮�� ���Ҷ� �θ���. thread
	@Override
	public void run() {
		// 1. �̹����� ���� �ϵ忡 ���� ��� -> Toolkit ���!		
		// 2. �̹����� Ŭ�����н��� ��, ��Ű���� ���� ��� -> ClassLoader() ���
		// 3. BufferedImage > ImageIO
		try {
			// �޸𸮿� �ø�����..
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
			System.out.println("�̹����� ã�� �� ���׿�");
			getErrorImage();	// 2-2) �̹��� �ε��ϴ� ���� �̹��� ��ã�� ���� �߻�ó��(�̹����ֱ�)
		}		
	}

}
