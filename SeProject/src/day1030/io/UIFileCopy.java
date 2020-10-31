package day1030.io;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UIFileCopy extends JFrame implements ActionListener{
	JLabel la_ori,la_dest;
	JTextField t_ori,t_dest;
	JButton bt;
		
	public UIFileCopy() {
		// 생성
		la_ori = new JLabel("원본경로");
		la_dest = new JLabel("복사경로");
		t_ori = new JTextField(50);
		t_dest = new JTextField(50);
		bt = new JButton("복사실행");
		
		// 스타일적용
		la_ori.setPreferredSize(new Dimension(150,30));
		la_dest.setPreferredSize(new Dimension(150,30));
		t_ori.setPreferredSize(new Dimension(500,30));
		t_dest.setPreferredSize(new Dimension(500,30));
				
		// 조립
		setLayout(new FlowLayout());
		add(la_ori);
		add(t_ori);
		add(la_dest);
		add(t_dest);
		add(bt);
		
		// 연결
		bt.addActionListener(this);
		// 파일 탐색기가 열림 파일 선택 가능
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		
		setSize(740,180);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		-> close할 기회가 없어져버림
//		해결책) 윈도우창을 닫을 때 이벤트를 구현해야 한다. windowListener		
	}
	
	public void copy() {
		// 메서드 내의 지역변수는 반드시 개발자가 초기화 해야 한다. 멤버변수가 아니기 때문		
		// 두 개의 클래스가 메모리에 올라와야하는 시점은?
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		String ori = t_ori.getText();	// 원본경로
		String dest = t_dest.getText();	// 복사본 경로
		
		try {
			// 입력,출력 스트림 생성!
			fis = new FileInputStream(ori);
			fos = new FileOutputStream(dest);
			
			// 읽고 내뱉자. 읽고 그걸 똑같이 출력해 (즉 복사)
			int data;
			while(true) {
				data = fis.read();
				if(data==-1)break;
				fos.write(data);				
			}
			JOptionPane.showMessageDialog(this, "복사 완료!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {copy();}
	
	public static void main(String[] args) {
		new UIFileCopy();
	}
}
