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
		// ����
		bar = new JMenuBar();
		menu = new JMenu("����");
		item_new = new JMenuItem("������");
		item_open = new JMenuItem("����");
		item_save = new JMenuItem("����");
		item_saves = new JMenuItem("�ٸ��̸����� ����");
		area = new JTextArea();
		// -> ����Ž���Ⱑ ���� �� ���̴� â ����!!
		chooser = new JFileChooser("D:/workspace/java_workspace/SeProject/res/data");
		
		// ����
		setJMenuBar(bar);
		bar.add(menu);
		menu.add(item_new);
		menu.add(item_open);
		menu.add(item_save);
		menu.add(item_saves);
		add(area);
		
		// (1) ���� = �����۵�� ������ ����
		item_new.addActionListener(this);
		item_open.addActionListener(this);
		item_save.addActionListener(this);
		item_saves.addActionListener(this);
		
		// ������
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
		// ���⸦ ������ Ž���Ⱑ ������ �Ѵ�.
		int result = chooser.showOpenDialog(this);
		
		if(result==JFileChooser.APPROVE_OPTION) {
			// Ȯ���� �����ٸ�..
			// ���� ������ ������ ������ �ְ� �۾� �õ��ϱ�
			// Ž���⿡�� ���� ������ ������ ��´�.
			selectedFile = chooser.getSelectedFile();
			
			// (*)���� ������ ������ ������ �� ������ ��θ� ���̰� ����
			this.setTitle(selectedFile.getAbsolutePath());
			
			// �б�
			try {
				reader = new FileReader(selectedFile);
				buffr = new BufferedReader(reader);	// ���� -> ���ٹ��ڰ���
				
				String str = null;
				while(true) {
					str = buffr.readLine();
					if(str==null)break;
					// ������ ������
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
