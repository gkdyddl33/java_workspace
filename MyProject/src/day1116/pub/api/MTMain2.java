package day1116.pub.api;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class MTMain2 extends JFrame{

	JPanel p_west;
	JTextField t_name;
	JTextField t_op1;
	JTextField t_op2;
	JTextField t_op3;
	JButton bt;
		
/*
	JTable 에서 데이터 연동시 지금까지는 이차원 배열만 써왔는데, 사실 이차원 배열은
	생성시 크기를 정해야 하기 때문에 불편한 점이 많습니다.
	따라서 컬렉션 프레임웍 중 Vector를 지원하는 생성자를 이용해봅시다.
	불편했던 예시) rs.last() 후 rs.getRow()로 데이터 총 수 구하고, 다시 커서를 원상복귀..
*/
	JTable table;
	JScrollPane scroll;
/*
	JTable에서 지원하는 Vector 방식은 2차원 배열보다는 유연하지만
	여전히 2차원 배열의 구조를 유지하므로 TableModel을 이용한 벡터 및 VO를 종합해서 개발해보자.
*/	
	MountainModel mountainModel;
	
	SAXParserFactory factory;
	SAXParser saxParser;
	String apiKey = "9aN6wUTG6RTz3a7xwiq8db%2F1ivVh8M%2FVCHzbTD%2FYfNWd2Q41DpXMUwqrAvk5DdPcp4qhcLeohymnypaSEKsrIg%3D%3D";
	Thread loadThread;	// 네트워크 상에서 xml을 불러올 때 사용할 쓰레드
									// 버튼을 누를 때마다 인스턴스 생성하여 사용하자.
	
	InputStream is;	// xml 데이터를 담고 있는 스트림	
	MountainHandler mountainHandler;
	
	BufferedReader rd;		// 다 읽고 나서 파싱이 끝나면 stream을 닫아야 하므로 변수로 선언함
	HttpURLConnection conn;
	
	public MTMain2() {
		p_west = new JPanel();
		t_name = new JTextField(15);
		t_op1 = new JTextField(15);
		t_op2 = new JTextField(15);
		t_op3 = new JTextField(15);
		bt = new JButton("검색하기");
		
//		table = new JTable(mountainModel = new MountainModel());
		// TableModel 방식으로 가보자.
		table = new JTable(mountainModel = new MountainModel()); 
		scroll = new JScrollPane(table);
		
		add(p_west,BorderLayout.WEST);		
		p_west.add(t_name);
		p_west.add(t_op1);
		p_west.add(t_op2);
		p_west.add(t_op3);
		p_west.add(bt);		
		add(scroll);
		
		p_west.setPreferredSize(new Dimension(200,600));
		p_west.setBackground(Color.WHITE);
		t_name.setPreferredSize(new Dimension(190,30));
		t_op1.setPreferredSize(new Dimension(190,30));
		t_op2.setPreferredSize(new Dimension(190,30));
		t_op3.setPreferredSize(new Dimension(190,30));
		
		// 1) 파싱 시작 버튼과 리스너 연결
		bt.addActionListener((e)->{
			// 네트워크를 타고 데이터를 가져올 때 메인 쓰레드에서 진행하는게 좋다			
			//loadXML();
			// 쓰레드를 여러번 사용하기 위해서 버튼을 누를 때마다..그럴 경우 이렇게 코드를 
			// 같이 사용하게 넣어주자. = 무한대
			loadThread = new Thread() {
				public void run() {
					loadXML(); // 버튼을 누를 때 이 쓰레드 호출!!!!!!
				}
			};
			loadThread.start(); // xml 로딩 쓰레드 호출!
		});
		
		setSize(900,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void loadXML() {
		// 1) 공공데이터포털 정보 삽입 - 키 값은 멤버변수에 선언!
		// URL에서 XML을 가져오는 단계이므로 메서드명을 살짝 바꾸자. 아직 파싱 작업 아님
		try {
			StringBuilder urlBuilder = new StringBuilder("http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+apiKey); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("mntnNm","UTF-8") + "=" + URLEncoder.encode(t_name.getText(), "UTF-8")); /*산이름*/
			urlBuilder.append("&" + URLEncoder.encode("mntnHght","UTF-8") + "=" + URLEncoder.encode(t_op1.getText(), "UTF-8")); /*산높이*/
			urlBuilder.append("&" + URLEncoder.encode("mntnAdd","UTF-8") + "=" + URLEncoder.encode(t_op2.getText(), "UTF-8")); /*산소재지*/
			urlBuilder.append("&" + URLEncoder.encode("mntnInfoAraCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnInfoSsnCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			urlBuilder.append("&" + URLEncoder.encode("mntnInfoThmCd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /**/
			URL url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			    rd = new BufferedReader(new InputStreamReader(is=conn.getInputStream()));
			} else {
			    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			// 아래의 코드는 화면에 출력하기 위한 코드이므로, 테스트가 끝나면 제거해야 한다.
			// 이유? read를 여기서 해버리면 이후의 라인에서는 스트림의 끝에 도달하게 되므로,
			// 즉 사용전에 써버렸으므로.. 파싱할때 읽어와야 하는데 그 전에 사용했다는 뜻!
			// 파싱도 하기 전에 소모시키지 말자!
/*			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
			    sb.append(line);
			}
*/			
			// 제대로 불러와 지는지 sb를 콘솔에 출력해보기 -> inputstream으로 실제 출력 확인
			//System.out.println(sb.toString());
			
			parseData(); // xml이 다 읽어들인 시점에 파싱을 시작하자!!			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 2)
	public void parseData() {
		/*
		 	지금 파싱할 데이터는 주의!
		 	1) xml 파일로 존재할까요?
		 	2) 메모리 상에서 텍스트로 존재할까요?
		 	저번주 예제에서는 xml 파일로 보유한 후 파싱했지만, 공공데이터 포털의 api는 메모리상에서
		 	불러와서 처리해야 해요.. 그래서 parsing 할 때 오버로딩 메서드를 잘 선택해야 한다.
		*/
		factory = SAXParserFactory.newInstance();
		try {
			saxParser = factory.newSAXParser();	// 파서객체 생성
			saxParser.parse(is, mountainHandler = new MountainHandler());
			
			// 3) MountainHandler 완성후 파싱이 끝나면 TableModel의 벡터객체를 파싱한 결과로
			// 얻은 벡터로 대체해버리면 된다..
			mountainModel.data = mountainHandler.mtList;
			table.updateUI();
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(rd!=null) {
				try {				
					rd.close();					
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			if(conn != null)conn.disconnect();
		}
	}
	public static void main(String[] args) {
		new MTMain2();
	}
}
