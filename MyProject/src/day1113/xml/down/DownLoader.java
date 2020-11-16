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
	// �׳� �Ľ̻��·� �ϸ��� ���������. �ð��� ���ϰ� 
	Thread parsingThread;
	
	public DownLoader() {
		bt_down = new JButton("�ٿ�ε�");
		bar = new JProgressBar();
		
		setLayout(new FlowLayout());
		add(bt_down);
		add(bar);
		
		bar.setFont(new Font("Verdana", Font.BOLD, 25));
		bar.setStringPainted(true);	// �� �ۼ�Ʈ ���� ����
		//bar.setValue(35);	// ä����
		
		bar.setPreferredSize(new Dimension(580,45));
		bar.setForeground(Color.CYAN);
		bar.setBackground(Color.BLACK);		
		
		// �ٿ�ε� ��ư�� ������ ����
		bt_down.addActionListener((e)->{
			parsingThread = new Thread() {			
				public void run() {
					parseData();	// �������� ����Ǵ� ���� ���
					// �� ����� �����ϴ��� ���
					System.out.println(movieHandler.movieList.size()); // 7�� ���´� �׷� �ݺ��� ���
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
	
	// �ڡ� �Ľ� ����
	public void parseData() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();	// �ļ���ü ����
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
	// �� ���ͳݻ��� �ڿ��� ������ ��, ��Ʈ������ �����͸� �о�� ���� �ϵ� ��ο� �����ϱ�!!
	public void download(String path) {// �Ű������� ������ �ڿ��� �����Ѵ�.
		InputStream is = null;
		FileOutputStream fos = null;	// �ܺ��� �ٿ�ε� ������ ������ ������ ��Ʈ��
		int readCount = 0;	// ������� ���� ����Ʈ ��
		int total = 0;	// �ٿ�ε� ���� �ڿ��� �� ����Ʈ �� *
		bar.setValue(0);	// �ʱ�ȭ
		
		try {
			URL url = new URL(path);
			URLConnection con =  url.openConnection();
			
			// // �� ���� Ưȭ�� Ŀ�ؼ� ��ü�� get,post �� �� ����� ��û�� ����
			HttpURLConnection http = (HttpURLConnection)con;	
			http.setRequestMethod("GET");
			
			// * Ŀ�ؼ� ��ü�� �̿��ϸ�, ��� �ڿ��� ũ����� ���� �� �ִ�.
			total = con.getContentLength();	// ����� �ڿ��� �� ����Ʈ ��ȯ!
			
			
			is = http.getInputStream();	// ����� URL �κ��� �Է½�Ʈ�� ���!!
			long time = System.currentTimeMillis();	// ���ϸ����� ������� - �ð�
			String ext = FileManager.getExtend2(path);	// * Ȯ���� ����� ��������!
			String filename = time +"."+ext;	// ���������� �ο��� ���ϸ�
			System.out.println("������ ���ϸ��� "+ filename);
			
			fos = new FileOutputStream("D:/workspace/java_workspace/MyProject/res/download/"+filename);
			int data = -1;
			while(true) {
				data = is.read();
				readCount++;
				
				// float -> int ����ȯ
				bar.setValue((int)getPercent(readCount, total)); // �� ���⼭ ����!! bar�� ����������!!
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
	// * �ۼ�Ʈ���ϴ� �޼��� ����
	public float getPercent(int read, float total) {
		// ���� �� / �� ����Ʈ �� * 100		
		return (read/total)*100;
	}
	public static void main(String[] args) {
		new DownLoader();
	}
}
