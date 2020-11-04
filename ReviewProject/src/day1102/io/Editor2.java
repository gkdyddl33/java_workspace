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

public class Editor2 extends JFrame implements ActionListener{
	JMenuBar bar;
	JMenu menu;
	JMenuItem item_new,item_open,item_save,item_saves;
	JTextArea area;
	JFileChooser chooser;
	
	FileReader reader;
	FileWriter writer;
	BufferedReader buffr;
	BufferedWriter buffw;
	File selectedFile;
	public Editor2() {
		// 생성
		bar = new JMenuBar();
		menu = new JMenu("파일");
		item_new = new JMenuItem("새파일");
		item_open = new JMenuItem("열기");
		item_save = new JMenuItem("저장");
		item_saves = new JMenuItem("다른이름으로 저장");
		area = new JTextArea();
		chooser = new JFileChooser("D:/workspace/java_workspace/SeProject/res/data");
		
		// 조립
		setJMenuBar(bar);
		bar.add(menu);
		menu.add(item_new);
		menu.add(item_open);
		menu.add(item_save);
		menu.add(item_saves);
		add(area);
		
		// 연결
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
	public void open() {
		int result = chooser.showOpenDialog(this);
		if(result==JFileChooser.APPROVE_OPTION) {
			// 확인을 눌렀다면..
			selectedFile = chooser.getSelectedFile();
			this.setTitle(selectedFile.getAbsolutePath());
						
			try {
				reader = new FileReader(selectedFile);
				buffr = new BufferedReader(reader);
				
				String str =null;
				while(true) {
					str = buffr.readLine();
					if(str==null)break;
					area.append(str+"\n");					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
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
		new Editor2();
	}
}
