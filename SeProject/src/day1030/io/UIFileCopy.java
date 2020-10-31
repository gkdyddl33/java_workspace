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
		// ����
		la_ori = new JLabel("�������");
		la_dest = new JLabel("������");
		t_ori = new JTextField(50);
		t_dest = new JTextField(50);
		bt = new JButton("�������");
		
		// ��Ÿ������
		la_ori.setPreferredSize(new Dimension(150,30));
		la_dest.setPreferredSize(new Dimension(150,30));
		t_ori.setPreferredSize(new Dimension(500,30));
		t_dest.setPreferredSize(new Dimension(500,30));
				
		// ����
		setLayout(new FlowLayout());
		add(la_ori);
		add(t_ori);
		add(la_dest);
		add(t_dest);
		add(bt);
		
		// ����
		bt.addActionListener(this);
		// ���� Ž���Ⱑ ���� ���� ���� ����
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(this);
		
		setSize(740,180);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		-> close�� ��ȸ�� ����������
//		�ذ�å) ������â�� ���� �� �̺�Ʈ�� �����ؾ� �Ѵ�. windowListener		
	}
	
	public void copy() {
		// �޼��� ���� ���������� �ݵ�� �����ڰ� �ʱ�ȭ �ؾ� �Ѵ�. ��������� �ƴϱ� ����		
		// �� ���� Ŭ������ �޸𸮿� �ö�;��ϴ� ������?
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		String ori = t_ori.getText();	// �������
		String dest = t_dest.getText();	// ���纻 ���
		
		try {
			// �Է�,��� ��Ʈ�� ����!
			fis = new FileInputStream(ori);
			fos = new FileOutputStream(dest);
			
			// �а� ������. �а� �װ� �Ȱ��� ����� (�� ����)
			int data;
			while(true) {
				data = fis.read();
				if(data==-1)break;
				fos.write(data);				
			}
			JOptionPane.showMessageDialog(this, "���� �Ϸ�!");
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