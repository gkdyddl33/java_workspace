package day1106.db;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import common.file.FileManager;

// 11) (새창)이미지 수집하기 - 스콜링(빅데이터기술) = 이미지 긁어오기
public class CollectorFrame extends JFrame{
	JTextField t_url;
	JButton bt;
	// 12) 이미지 그리기 반영하기
	JButton bt_apply;
	ShoppingApp shoppingApp;
	File file;		// 인스턴스로 수집된 저장된 파일
	
	BufferedImage buffr;		// url로 가져온 이미지 정보를 담을 객체	
	
	public CollectorFrame(ShoppingApp shoppingApp) {
		this.shoppingApp = shoppingApp;
		// 생성
		setLayout(new FlowLayout());
		t_url = new JTextField();	// 북쪽에 붙이자.
		bt = new JButton("수집실행");
		bt_apply = new JButton("반영하기");
		
		// 조립
		t_url.setPreferredSize(new Dimension(580,40));
		add(t_url);
		add(bt);
		add(bt_apply);
		
		// 이벤트 = shoppingApp에 연결
		bt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				collect();
			}
		});
		// 12) 이미지가 반영하기를 누르면 반영이 되고, 등록을 누르면 travel에 들어간다.
		bt_apply.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ShoppingApp 클래스의 img 변수의 값을 인터넷상 이미지 교체하고
				shoppingApp.getTargetImage(file.getAbsolutePath());	// 디렉토리 포함한 파일의 풀경로				
				// ShoppingApp  클래스의 preview() 메서드 호출 = 다시 그려라.
				shoppingApp.preview();
			}
		});
		
		// 윈도우
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(600,250);
	}
	public void collect() {
		// 가져올 데이터가 이미지인 경우엔  아래의 방법이 유용..
		try {
			// 현재까지는 메모리에 존재하므로, 실제 파일로 저장해 놓자.
			// https://image.auction.co.kr/itemimage/15/5d/05/155d0590e6.jpg
			URL url = new URL(t_url.getText());
			buffr = ImageIO.read(url); // 경로삽입
			
			// 저장할 파일명은 우리가 지정하자 = 즉, 저장할 파일에 이미지 넣기 .jpg(주소)
			long time = System.currentTimeMillis();	// 현재시간을 반환해주는 메서드
																		// 유일성을 확보한 고유한 값을 만들어 준다.
			System.out.println(time);
			
/*
 	1. 파일명 규칙
 	파일명 가장 마지막 슬래시(/)의 다음 문자열~마지막 문자열까지
 	2. 파일명에서 .(점)을 기준으로 문자열을 분리시키면 배열이 생성되고
 		두번째 요소가 바로 확장자이다.
 */
			String filename = FileManager.getFilename(t_url.getText());
			String extend = FileManager.getExtend(filename);
			// 빈 파일 생성
			file = new File("D:/workspace/java_workspace/SeProject/res/travel2/"+time+"."+extend);
			// 빈 파일에다가 이미지 데이터를 쓰자(출력)
			ImageIO.write(buffr, extend, file);
			JOptionPane.showMessageDialog(this, "가져오기 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
