package day1111.json;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonGallery2 extends JFrame{

	JPanel p_center;
	JPanel p_south;
	JPanel p_can;
	JPanel p_detail;
	
	Image img;
	Image big;
	Thread thread;
		
	JLabel[] la =  new JLabel[4];	// 7) 우측에 정보나오게 하기
	String[] la_title = {"Title","Phase","Category","Release"};
	
	public JsonGallery2() {
	
		p_center = new JPanel();
		p_south = new JPanel();
		p_can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(big, 0, 0, this);
			}
		};
		p_detail = new JPanel();
		
		// 우측 상세보기 나오게 하기
		for(int i=0;i<la.length;i++) {
			la[i] =  new JLabel(la_title[i]);
			la[i].setPreferredSize(new Dimension(380,50));
			la[i].setFont(new Font("Verdana", Font.BOLD, 28));
			p_detail.add(la[i]);
		}
		
		p_center.setLayout(new GridLayout(1,2));
		p_center.add(p_can);
		p_center.add(p_detail);
		add(p_center);
		add(p_south,BorderLayout.SOUTH);
		
		p_center.setBackground(Color.YELLOW);
		p_south.setPreferredSize(new Dimension(800,100));
		p_south.setBackground(Color.GREEN);
		p_can.setBackground(Color.PINK);
		p_detail.setBackground(Color.ORANGE);
				

		// 윈도우
		setVisible(true);
		setSize(800,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		createThumb();	// 수집시작
	}
	
	public void createThumb() {
		BufferedReader buffr = null;
		
		// data.json 읽어오기
		URL url = this.getClass().getClassLoader().getResource("res/data.json");
		try {
			URI uri = url.toURI();
			// 읽기
			FileReader reader = new FileReader(new File(uri));
			// 한줄 읽기
			buffr = new BufferedReader(reader);
			StringBuffer sb = new StringBuffer();
			String data = null;
			while(true) {
				data = buffr.readLine();
				if(data==null)break;
				sb.append(data);				
			}
			System.out.println(sb.toString());
			
// --------------------------------------------
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
			JSONArray jsonArray = (JSONArray) jsonObject.get("marvel");
			
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject obj = (JSONObject) jsonArray.get(i);
				System.out.println(obj.get("title"));
				System.out.println(obj.get("phase"));
				
				// 아래 매개변수를 받기 위해
				String u = (String)obj.get("url");
				String title = (String)obj.get("title");
				String phase = (String)obj.get("phase");
				String category_name = (String)obj.get("category_name");
				String release_year = ((Long)obj.get("release_year")).toString();
				
				// 하나의 셀 삽입
				Movie2 thumbnail = new Movie2(this, 90, 90,u,title,phase,category_name,release_year);
				p_south.add(thumbnail);
				p_south.updateUI();
			}
						
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		
	}
	
	public void getDetail(Image big,String title,String phase,String category_name,String release_year) {
		this.big = big;
		p_can.repaint();
		
		la[0].setText(la[0].getText()+":"+title);
		la[1].setText(la[0].getText()+":"+phase);
		la[2].setText(la[0].getText()+":"+category_name);
		la[3].setText(la[0].getText()+":"+release_year);
	}
	public static void main(String[] args) {
		new JsonGallery2();
	}	
}
