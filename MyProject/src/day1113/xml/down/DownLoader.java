package day1113.xml.down;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import common.file.FileManager;

public class DownLoader extends JFrame{

	JButton bt_down;
	JProgressBar bar;
	MovieHandler movieHandler;
	// 그냥 파싱상태로 하면은 멈춰버린다. 시간을 정하게 
	Thread parsingThread;
	
	public DownLoader() {
		bt_down = new JButton("다운로드");
		bar = new JProgressBar();
		
		setLayout(new FlowLayout());
		add(bt_down);
		add(bar);
		
		bar.setFont(new Font("Verdana", Font.BOLD, 25));
		bar.setStringPainted(true);	// 몇 퍼센트 숫자 보임
		//bar.setValue(35);	// 채워짐
		
		bar.setPreferredSize(new Dimension(580,45));
		bar.setForeground(Color.CYAN);
		bar.setBackground(Color.BLACK);		
		
		// 다운로드 버튼과 리스너 연결
		bt_down.addActionListener((e)->{
			parsingThread = new Thread() {			
				public void run() {
					parseData();	// 순서없이 진행되는 동기 방식
					// 총 몇건이 존재하는지 출력
					System.out.println(movieHandler.movieList.size()); // 7이 나온다 그럼 반복문 사용
					for (int i = 0; i < movieHandler.movieList.size(); i++) {
						Movie movie = movieHandler.movieList.get(i);
						download(movie.getUrl());
					}
				}
			};
			parsingThread.start();
		});
		
		setSize(600,200);		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	// ★★ 파싱 시작
	public void parseData() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();	// 파서객체 생성
			URL url = this.getClass().getClassLoader().getResource("res/marvel.xml");
			File file = new File(url.toURI());
			saxParser.parse(file, movieHandler=new MovieHandler());
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// ★ 인터넷상의 자원과 연결한 후, 스트림으로 데이터를 읽어와 로컬 하드 경로에 저장하기!!
	public void download(String path) {// 매개변수로 가져올 자원을 지정한다.
		InputStream is = null;
		FileOutputStream fos = null;	// 외부의 다운로드 파일을 저장할 가져올 스트림
		int readCount = 0;	// 현재까지 읽은 바이트 수
		int total = 0;	// 다운로드 받을 자원의 총 바이트 수 *
		bar.setValue(0);	// 초기화
		
		try {
			URL url = new URL(path);
			URLConnection con =  url.openConnection();
			
			// // ★ 웹에 특화된 커넥션 객체로 get,post 등 웹 기반의 요청이 가능
			HttpURLConnection http = (HttpURLConnection)con;	
			http.setRequestMethod("GET");
			
			// * 커넥션 객체를 이용하면, 대상 자원의 크기까지 얻어올 수 있다.
			total = con.getContentLength();	// 연결된 자원의 총 바이트 반환!
			
			
			is = http.getInputStream();	// 연결된 URL 로부터 입력스트림 얻기!!
			long time = System.currentTimeMillis();	// 파일명으로 사용하자 - 시간
			String ext = FileManager.getExtend2(path);	// * 확장자 제대로 가져오기!
			String filename = time +"."+ext;	// 최종적으로 부여된 파일명
			System.out.println("생성된 파일명은 "+ filename);
			
			fos = new FileOutputStream("D:/workspace/java_workspace/MyProject/res/download/"+filename);
			int data = -1;
			while(true) {
				data = is.read();
				readCount++;
				
				// float -> int 형변환
				bar.setValue((int)getPercent(readCount, total)); // ★ 여기서 과제!! bar를 움직여보자!!
				System.out.println((int)getPercent(readCount, total));
				if(data==-1)break;
				fos.write(data);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// * 퍼센트구하는 메서드 정의
	public float getPercent(int read, float total) {
		// 읽은 수 / 총 바이트 수 * 100		
		return (read/total)*100;
	}
	public static void main(String[] args) {
		new DownLoader();
	}
}
