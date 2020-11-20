package day1113.xml.down;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

public class DowmLoader2 extends JFrame{

	JButton bt_down;
	JProgressBar bar;
	
	MovieHandler movieHandler;
	Thread parsingThread;
	
	public DowmLoader2() {
		bt_down = new JButton("다운로드");
		bar = new JProgressBar();
		
		setLayout(new FlowLayout());
		add(bt_down);
		add(bar);
		
		bar.setFont(new Font("Verdana", Font.BOLD, 25));
		bar.setPreferredSize(new Dimension(580,45));
		bar.setForeground(Color.CYAN);
		bar.setBackground(Color.BLACK);
		bar.setStringPainted(true);		
		
		bt_down.addActionListener((e)->{
			parsingThread = new Thread() {
				public void run() {
					parseData();	// 파싱	
					int len = movieHandler.movieList.size();
					for(int i=0;i<len;i++) {
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
	// 파싱 읽어들이기 -> 다 읽었다면? 다운로드 시작~
	public void parseData() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			// 무엇을 파싱?
			URL url = this.getClass().getClassLoader().getResource("res/marvel.xml");
			File file = new File(url.toURI());
			saxParser.parse(file, movieHandler = new MovieHandler());
			
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
	
	public void download(String path) {
		InputStream is = null;
		FileOutputStream fos = null;
		
		int readCount=0;
		int total = 0;
		bar.setValue(0);
		
		try {
			URL url = new URL(path);
			URLConnection con = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) con;
			http.setRequestMethod("GET");
			
			total = con.getContentLength();	// 연결된 자원의 총 바이트 반환!
			is = http.getInputStream();
			long time = System.currentTimeMillis();
			String ext = FileManager.getExtend2(path);
			String filename = time+"."+ext;
			
			fos = new FileOutputStream("D:/workspace/java_workspace/MyProject/res/download/"+filename);
			
			int data = -1;
			while(true) {
				data=is.read();
				readCount++;	// bar 가 ++
				bar.setValue((int)getPercent(readCount, total));
				if(data==-1)break;
				fos.write(data);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	public float getPercent(int read,int total) {
		return (read/total)*100;
	}
	
	public static void main(String[] args) {
		new DowmLoader2();
	}
}
