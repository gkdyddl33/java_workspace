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
	JTable ���� ������ ������ ���ݱ����� ������ �迭�� ��Դµ�, ��� ������ �迭��
	������ ũ�⸦ ���ؾ� �ϱ� ������ ������ ���� �����ϴ�.
	���� �÷��� �����ӿ� �� Vector�� �����ϴ� �����ڸ� �̿��غ��ô�.
	�����ߴ� ����) rs.last() �� rs.getRow()�� ������ �� �� ���ϰ�, �ٽ� Ŀ���� ���󺹱�..
*/
	JTable table;
	JScrollPane scroll;
/*
	JTable���� �����ϴ� Vector ����� 2���� �迭���ٴ� ����������
	������ 2���� �迭�� ������ �����ϹǷ� TableModel�� �̿��� ���� �� VO�� �����ؼ� �����غ���.
*/	
	MountainModel mountainModel;
	
	SAXParserFactory factory;
	SAXParser saxParser;
	String apiKey = "9aN6wUTG6RTz3a7xwiq8db%2F1ivVh8M%2FVCHzbTD%2FYfNWd2Q41DpXMUwqrAvk5DdPcp4qhcLeohymnypaSEKsrIg%3D%3D";
	Thread loadThread;	// ��Ʈ��ũ �󿡼� xml�� �ҷ��� �� ����� ������
									// ��ư�� ���� ������ �ν��Ͻ� �����Ͽ� �������.
	
	InputStream is;	// xml �����͸� ��� �ִ� ��Ʈ��	
	MountainHandler mountainHandler;
	
	BufferedReader rd;		// �� �а� ���� �Ľ��� ������ stream�� �ݾƾ� �ϹǷ� ������ ������
	HttpURLConnection conn;
	
	public MTMain2() {
		p_west = new JPanel();
		t_name = new JTextField(15);
		t_op1 = new JTextField(15);
		t_op2 = new JTextField(15);
		t_op3 = new JTextField(15);
		bt = new JButton("�˻��ϱ�");
		
//		table = new JTable(mountainModel = new MountainModel());
		// TableModel ������� ������.
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
		
		// 1) �Ľ� ���� ��ư�� ������ ����
		bt.addActionListener((e)->{
			// ��Ʈ��ũ�� Ÿ�� �����͸� ������ �� ���� �����忡�� �����ϴ°� ����			
			//loadXML();
			// �����带 ������ ����ϱ� ���ؼ� ��ư�� ���� ������..�׷� ��� �̷��� �ڵ带 
			// ���� ����ϰ� �־�����. = ���Ѵ�
			loadThread = new Thread() {
				public void run() {
					loadXML(); // ��ư�� ���� �� �� ������ ȣ��!!!!!!
				}
			};
			loadThread.start(); // xml �ε� ������ ȣ��!
		});
		
		setSize(900,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void loadXML() {
		// 1) �������������� ���� ���� - Ű ���� ��������� ����!
		// URL���� XML�� �������� �ܰ��̹Ƿ� �޼������ ��¦ �ٲ���. ���� �Ľ� �۾� �ƴ�
		try {
			StringBuilder urlBuilder = new StringBuilder("http://openapi.forest.go.kr/openapi/service/trailInfoService/getforeststoryservice"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+apiKey); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("mntnNm","UTF-8") + "=" + URLEncoder.encode(t_name.getText(), "UTF-8")); /*���̸�*/
			urlBuilder.append("&" + URLEncoder.encode("mntnHght","UTF-8") + "=" + URLEncoder.encode(t_op1.getText(), "UTF-8")); /*�����*/
			urlBuilder.append("&" + URLEncoder.encode("mntnAdd","UTF-8") + "=" + URLEncoder.encode(t_op2.getText(), "UTF-8")); /*�������*/
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
			// �Ʒ��� �ڵ�� ȭ�鿡 ����ϱ� ���� �ڵ��̹Ƿ�, �׽�Ʈ�� ������ �����ؾ� �Ѵ�.
			// ����? read�� ���⼭ �ع����� ������ ���ο����� ��Ʈ���� ���� �����ϰ� �ǹǷ�,
			// �� ������� ��������Ƿ�.. �Ľ��Ҷ� �о�;� �ϴµ� �� ���� ����ߴٴ� ��!
			// �Ľ̵� �ϱ� ���� �Ҹ��Ű�� ����!
/*			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
			    sb.append(line);
			}
*/			
			// ����� �ҷ��� ������ sb�� �ֿܼ� ����غ��� -> inputstream���� ���� ��� Ȯ��
			//System.out.println(sb.toString());
			
			parseData(); // xml�� �� �о���� ������ �Ľ��� ��������!!			
			
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
		 	���� �Ľ��� �����ʹ� ����!
		 	1) xml ���Ϸ� �����ұ��?
		 	2) �޸� �󿡼� �ؽ�Ʈ�� �����ұ��?
		 	������ ���������� xml ���Ϸ� ������ �� �Ľ�������, ���������� ������ api�� �޸𸮻󿡼�
		 	�ҷ��ͼ� ó���ؾ� �ؿ�.. �׷��� parsing �� �� �����ε� �޼��带 �� �����ؾ� �Ѵ�.
		*/
		factory = SAXParserFactory.newInstance();
		try {
			saxParser = factory.newSAXParser();	// �ļ���ü ����
			saxParser.parse(is, mountainHandler = new MountainHandler());
			
			// 3) MountainHandler �ϼ��� �Ľ��� ������ TableModel�� ���Ͱ�ü�� �Ľ��� �����
			// ���� ���ͷ� ��ü�ع����� �ȴ�..
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
