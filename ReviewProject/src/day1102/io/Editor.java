package day1102.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Editor extends JFrame implements ActionListener{
	JMenuBar bar;
	JMenu menu;
	JMenuItem item_new,item_open,item_save,item_saves;
	JTextArea area;
	
	FileReader reader;
	FileWriter writer;
	JFileChooser chooser;
	BufferedReader buffr;
	BufferedWriter buffw;
	File selectedFile;
	
	public Editor() {
		// 생성
		bar = new JMenuBar();
		menu = new JMenu("파일");
		item_new = new JMenuItem("새파일");
		item_open = new JMenuItem("열기");
		item_save = new JMenuItem("저장");
		item_saves = new JMenuItem("다른이름으로 저장");
		area = new JTextArea();
		// -> 파일탐색기가 열릴 때 보이는 창 설정!!
		chooser = new JFileChooser("D:/workspace/java_workspace/SeProject/res/data");
		
		// 조립
		setJMenuBar(bar);
		bar.add(menu);
		menu.add(item_new);
		menu.add(item_open);
		menu.add(item_save);
		menu.add(item_saves);
		add(area);
		
		// (1) 열기 = 아이템들과 리스너 연결
		item_new.addActionListener(this);
		item_open.addActionListener(this);
		item_save.addActionListener(this);
		item_saves.addActionListener(this);
		
		// 윈도우
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	// (1)
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==item_new) {
			
		}else if(obj==item_open) {
			open();
		}else if(obj==item_save) {
			save();
		}else if(obj==item_saves) {
			
		}
		
	}
	
	// (1)
	public void open() {
		// 열기를 누르면 탐색기가 보여야 한다.
		int result = chooser.showOpenDialog(this);
		
		if(result==JFileChooser.APPROVE_OPTION) {
			// 확인을 눌렀다면..
			// 내가 선택한 파일을 읽을수 있게 작업 시도하기
			// 탐색기에서 내가 선택한 파일을 담는다.
			selectedFile = chooser.getSelectedFile();
			
			// (*)내가 선택한 파일을 열었을 때 제목의 경로를 보이게 하자
			this.setTitle(selectedFile.getAbsolutePath());
			
			// 읽기
			try {
				reader = new FileReader(selectedFile);
				buffr = new BufferedReader(reader);	// 문자 -> 한줄문자가능
				
				String str = null;
				while(true) {
					str = buffr.readLine();
					if(str==null)break;
					// 읽은걸 보여줘
					area.append(str+"\n");					
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	//(2)
	public void save() {
		try {
			writer = new FileWriter(selectedFile);
			writer.write(area.getText());
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new Editor();
	}
}
