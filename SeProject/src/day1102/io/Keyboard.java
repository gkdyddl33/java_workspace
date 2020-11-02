package day1102.io;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Keyboard extends JFrame implements KeyListener{
	FileReader reader;
	FileWriter writer;
	
	BufferedReader buffr;
	BufferedWriter buffw;
	JTextArea area;
	
	public Keyboard() {
		// 생성
		area = new JTextArea();
		
		// 조립
		add(area);
		
		// 이벤트
		area.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Object obj = e.getKeyCode();
				//System.out.println(obj);
				if(obj=="10") {
					save();
				}
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// 윈도우
		setSize(500,500);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void save() {
		String str = null;
		try {
			reader = new FileReader(str);
			buffr = new BufferedReader(reader);
			writer = new FileWriter(str);
			
			str = buffr.readLine();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		new Keyboard();		
	}
}

