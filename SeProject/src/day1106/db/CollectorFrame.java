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

// 11) (��â)�̹��� �����ϱ� - ���ݸ�(�����ͱ��) = �̹��� �ܾ����
public class CollectorFrame extends JFrame{
	JTextField t_url;
	JButton bt;
	// 12) �̹��� �׸��� �ݿ��ϱ�
	JButton bt_apply;
	ShoppingApp shoppingApp;
	File file;		// �ν��Ͻ��� ������ ����� ����
	
	BufferedImage buffr;		// url�� ������ �̹��� ������ ���� ��ü	
	
	public CollectorFrame(ShoppingApp shoppingApp) {
		this.shoppingApp = shoppingApp;
		// ����
		setLayout(new FlowLayout());
		t_url = new JTextField();	// ���ʿ� ������.
		bt = new JButton("��������");
		bt_apply = new JButton("�ݿ��ϱ�");
		
		// ����
		t_url.setPreferredSize(new Dimension(580,40));
		add(t_url);
		add(bt);
		add(bt_apply);
		
		// �̺�Ʈ = shoppingApp�� ����
		bt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				collect();
			}
		});
		// 12) �̹����� �ݿ��ϱ⸦ ������ �ݿ��� �ǰ�, ����� ������ travel�� ����.
		bt_apply.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ShoppingApp Ŭ������ img ������ ���� ���ͳݻ� �̹��� ��ü�ϰ�
				shoppingApp.getTargetImage(file.getAbsolutePath());	// ���丮 ������ ������ Ǯ���				
				// ShoppingApp  Ŭ������ preview() �޼��� ȣ�� = �ٽ� �׷���.
				shoppingApp.preview();
			}
		});
		
		// ������
		setLocationRelativeTo(null);
		setVisible(true);
		setSize(600,250);
	}
	public void collect() {
		// ������ �����Ͱ� �̹����� ��쿣  �Ʒ��� ����� ����..
		try {
			// ��������� �޸𸮿� �����ϹǷ�, ���� ���Ϸ� ������ ����.
			// https://image.auction.co.kr/itemimage/15/5d/05/155d0590e6.jpg
			URL url = new URL(t_url.getText());
			buffr = ImageIO.read(url); // ��λ���
			
			// ������ ���ϸ��� �츮�� �������� = ��, ������ ���Ͽ� �̹��� �ֱ� .jpg(�ּ�)
			long time = System.currentTimeMillis();	// ����ð��� ��ȯ���ִ� �޼���
																		// ���ϼ��� Ȯ���� ������ ���� ����� �ش�.
			System.out.println(time);
			
/*
 	1. ���ϸ� ��Ģ
 	���ϸ� ���� ������ ������(/)�� ���� ���ڿ�~������ ���ڿ�����
 	2. ���ϸ��� .(��)�� �������� ���ڿ��� �и���Ű�� �迭�� �����ǰ�
 		�ι�° ��Ұ� �ٷ� Ȯ�����̴�.
 */
			String filename = FileManager.getFilename(t_url.getText());
			String extend = FileManager.getExtend(filename);
			// �� ���� ����
			file = new File("D:/workspace/java_workspace/SeProject/res/travel2/"+time+"."+extend);
			// �� ���Ͽ��ٰ� �̹��� �����͸� ����(���)
			ImageIO.write(buffr, extend, file);
			JOptionPane.showMessageDialog(this, "�������� �Ϸ�");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
