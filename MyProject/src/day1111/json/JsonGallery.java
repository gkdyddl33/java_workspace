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

	JPanel p_center;	// �׸��带 ������ ��� �г�
	JPanel p_south;		// ������� ������ ���� �г�	
	JPanel p_can;		// ū �׸��� �׷��� �г�
	JPanel p_detail;		// �󼼳����� ��µ� �г�
	
	Thread thread;	// 2-1) �������� URL �̹����� �ε��ϴ� ����, �׷��� ó���� ������ �ǹ�����.
	Image img;
	Image big;

	JLabel[] la =  new JLabel[4];	// 7) ������ ���������� �ϱ�
	String[] la_title = {"Title","Phase","Category","Release"};
	
	public JsonGallery() {
		p_center = new JPanel();
		p_south = new JPanel();
		p_can = new JPanel() {
			// 6) ���� ū �׸� �׸���
			@Override
			public void paint(Graphics g) {
				g.drawImage(big, 0, 0, p_can);
			}
		};
		p_detail = new JPanel();
		
		for(int i=0;i<la.length;i++) {// 7) ������ ���������� �ϱ�
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
				

		// ������
		setVisible(true);
		setSize(800,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		// 2-1) ������ �� ���� �Ŀ� ��������!!
		//thread.start();
		createThumb();
	}
	
	// �����Ϸ�? �׷� ����� ���� �����ϱ� -> �׸���? �������о���� ���� ����
	public void createThumb() {
		// 1-3) ��ü ���� ������ �о����
		BufferedReader buffr = null;
		
		// 1-2) Ŭ���� �н��� �ִ� �ؽ�Ʈ ���� �б� = data.json �о����
		try {
			URL url = this.getClass().getClassLoader().getResource("res/data.json");
			URI uri = url.toURI();	// URL�� URI�� ����		
			// Ŭ�����н����� �ؽ�Ʈ���� data.json�� �ֱ�
			FileReader reader = new FileReader(new File(uri));	
		
			
			buffr = new BufferedReader(reader);
			StringBuffer sb = new StringBuffer();			
			String data=null;
			while(true) {
				data = buffr.readLine();
				if(data==null)break;
				sb.append(data);
			}			
			System.out.println(sb.toString());	// ����� ��Ʈ���� ����غ���.				
			
// --------------------> 1-3) JSON			
			JSONParser jsonParser = new JSONParser();		
			// ���ڿ��� �Ұ��ߴ� json ǥ��� ���ڿ��� ���� json��ü�� ��ȯ
			// json�ȿ� �ִ� marvel ������ ������ �ִ� �迭�� ��������
			JSONObject jsonObject = (JSONObject)jsonParser.parse(sb.toString());
			JSONArray jsonArray = (JSONArray) jsonObject.get("marvel");
			
			for(int i =0;i<jsonArray.size();i++) {
				// ���� �� �������� ��ġ ��üó�� �����Ͽ� ����� �����ϴ�. = ��ȭ�� "����"
				JSONObject obj = (JSONObject) jsonArray.get(i);
				
				System.out.println(obj.get("title"));	// �丣
				System.out.println(obj.get("phase"));	// ������
				
				// 6) ū�̹����� ����� ���� �Ű������� �޾ƿ�
				String u = (String)obj.get("url");
				String title = (String)obj.get("title");
				String phase = (String)obj.get("phase");
				String category_name = (String)obj.get("category_name");
				String release_year = ((Long)obj.get("release_year")).toString();
				
				// 1-1) �ϳ��� �� ����!
				Movie thumbnail = new Movie(this,90, 90, u,title,phase,category_name,release_year);
				// �ϳ��� ����� �߰� ����! �Ǵٸ� �ϳ��� ��Ǫ���� ���� �� 2�� ����
				p_south.add(thumbnail);
				p_south.updateUI(); // �̹����� �ϳ��ϳ� ���ŵǴ� ����� ���δ�.
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
	
	// 6) �󼼳��� ����ϱ�
	public void getDetail(Image big,String title,String phase,String category_name,String release_year) {
		// �̹��� ó��
		this.big=big;
		p_can.repaint();
		// ����,���� ��ȭ���� ���
		la[0].setText(la[0].getText()+":"+title);
		la[1].setText(la[1].getText()+":"+phase);
		la[2].setText(la[2].getText()+":"+category_name);
		la[3].setText(la[3].getText()+":"+release_year);
	}
	
	public static void main(String[] args) {
		new JsonGallery();
	}
}
