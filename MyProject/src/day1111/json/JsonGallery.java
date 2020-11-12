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
import java.io.FileInputStream;
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

import com.mysql.cj.xdevapi.JsonArray;

public class JsonGallery extends JFrame{

	JPanel p_center;	// 그리드를 적용할 가운데 패널
	JPanel p_south;		// 썸네일을 부착할 남쪽 패널	
	JPanel p_can;		// 큰 그림이 그려질 패널
	JPanel p_detail;		// 상세내용이 출력될 패널
	
	Thread thread;	// 2-1) 원격자의 URL 이미지를 로드하는 동안, 그래픽 처리가 먹통이 되버린다.
	Image img;
	Image big;

	JLabel[] la =  new JLabel[4];	// 7) 우측에 정보나오게 하기
	String[] la_title = {"Title","Phase","Category","Release"};
	
	public JsonGallery() {
		p_center = new JPanel();
		p_south = new JPanel();
		p_can = new JPanel() {
			// 6) 왼쪽 큰 그림 그리기
			@Override
			public void paint(Graphics g) {
				g.drawImage(big, 0, 0, p_can);
			}
		};
		p_detail = new JPanel();
		
		for(int i=0;i<la.length;i++) {// 7) 우측에 정보나오게 하기
			la[i] = new JLabel(la_title[i]);
			la[i].setPreferredSize(new Dimension(380,50));
			la[i].setFont(new Font("Verdana",Font.BOLD,28));
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
		
		// 2-1) 윈도우 다 끝난 후에 수집시작!!
		//thread.start();
		createThumb();
	}
	
	// 조립완료? 그럼 썸네일 먼저 생성하기 -> 그리고? 데이터읽어오게 빨대 꽂자
	public void createThumb() {
		// 1-3) 전체 문장 데이터 읽어오기
		BufferedReader buffr = null;
		
		// 1-2) 클래스 패스상에 있는 텍스트 파일 읽기 = data.json 읽어오기
		try {
			URL url = this.getClass().getClassLoader().getResource("res/data.json");
			URI uri = url.toURI();	// URL을 URI로 변경		
			// 클래스패스상의 텍스트파일 data.json을 넣기
			FileReader reader = new FileReader(new File(uri));	
		
			
			buffr = new BufferedReader(reader);
			StringBuffer sb = new StringBuffer();			
			String data=null;
			while(true) {
				data = buffr.readLine();
				if(data==null)break;
				sb.append(data);
			}			
			System.out.println(sb.toString());	// 모아진 스트림을 출력해본다.				
			
// --------------------> 1-3) JSON			
			JSONParser jsonParser = new JSONParser();		
			// 문자열에 불과했던 json 표기법 문자열을 실제 json객체로 반환
			// json안에 있는 marvel 변수가 가지고 있는 배열을 가져오기
			JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());
			JSONArray jsonArray = (JSONArray) jsonObject.get("marvel");
			
			for(int i =0;i<jsonArray.size();i++) {
				// 따라서 이 시점부터 마치 객체처럼 접근하여 사용이 가능하다. = 영화의 "한편"
				JSONObject obj = (JSONObject) jsonArray.get(i);
				
				System.out.println(obj.get("title"));	// 토르
				System.out.println(obj.get("phase"));	// 어셈블드
				
				// 6) 큰이미지를 만들기 위해 매개변수로 받아옴
				String u = (String)obj.get("url");
				String title = (String)obj.get("title");
				String phase = (String)obj.get("phase");
				String category_name = (String)obj.get("category_name");
				String release_year = ((Long)obj.get("release_year")).toString();
				
				// 1-1) 하나의 셀 부착!
				Movie thumbnail = new Movie(this,90, 90, u,title,phase,category_name,release_year);
				// 하나의 썸네일 추가 부착! 또다른 하나는 거푸집에 있음 총 2개 생성
				p_south.add(thumbnail);
				p_south.updateUI(); // 이미지가 하나하나 갱신되는 모습이 보인다.
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
	
	// 6) 상세내용 출력하기
	public void getDetail(Image big,String title,String phase,String category_name,String release_year) {
		// 이미지 처리
		this.big=big;
		p_can.repaint();
		// 제목,등의 영화정보 출력
		la[0].setText(la[0].getText()+":"+title);
		la[1].setText(la[1].getText()+":"+phase);
		la[2].setText(la[2].getText()+":"+category_name);
		la[3].setText(la[3].getText()+":"+release_year);
	}
	
	public static void main(String[] args) {
		new JsonGallery();
	}
}
