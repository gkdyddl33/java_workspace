package day1030.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class GalleyApp extends JFrame implements ActionListener{
	// (1) ���ʿ� ���� �����
	JPanel p_west;
	JPanel p_center;
	JScrollPane scroll;
	
	// (4) ���Ϳ� ���� ���� �󺧰� ������ �� �׸��� �̹���
	JLabel la_name;
	XCanvas can;
	JPanel p_south;
	JButton bt_prev;
	JButton bt_next;
	
	// (2) ����� �迭����
	ArrayList<Thumb> list = new ArrayList<>();
	
	// (3) �̹��� �迭
	String dir = "D:/workspace/java_workspace/SeProject/res/travel2/";
	String[] src = {
			"aa.jpg","ab.jpg","ax.jpg","bm.jpg","et.jpg","kg.jpg","mx.jpg",
			"pk.jpg","ub.jpg","ya.jpg"
	};

	// (5) 
	int n = 0; //�迭�� index
	
	public GalleyApp() {
		super("�ڹ� ������");
		
		// (1)
		p_west = new JPanel();
		p_center = new JPanel();
		scroll = new JScrollPane(p_west);
		
		// (4) 
		la_name = new JLabel(src[n],SwingConstants.CENTER); // ��� ����
		can = new XCanvas(dir+src[n]);
		p_south = new JPanel();
		bt_prev = new JButton("����");
		bt_next = new JButton("����");
				
		
		// (2) ����� ���� -> * �̹������ �Ű������� �޾Ƽ� �Ѱ��� �Ű����� �ֱ�
		for (int i = 0; i < src.length; i++) { // 10->src.length
			Thumb thumb=null;
			list.add(thumb = new Thumb(dir+src[i],this)); // ������ ����� �װ� ���ʿ� ����
			p_west.add(thumb); // -> �̹����� �ؿ� ��� scroll�� ���̰� ����
		}
		
		// (1)
		p_west.setPreferredSize(new Dimension(100,600));
		p_center.setPreferredSize(new Dimension(700,600));
		p_west.setBackground(Color.YELLOW);
		p_center.setBackground(Color.GREEN);
		
//		add(p_west,BorderLayout.WEST);
		add(scroll,BorderLayout.WEST);
		add(p_center);
		
		//(4)
		la_name.setPreferredSize(new Dimension(700,50));
		la_name.setBackground(Color.YELLOW);
		la_name.setFont(new Font("Verdana", Font.BOLD, 25));
		p_south.add(bt_prev); // ���� �ζ��� ���� -> ������ ����Ʈ���� ����� �÷���
		p_south.add(bt_next);		
		p_center.add(la_name);
		p_center.add(can);
		p_center.add(p_south);
		
		// (5)
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		// (7) 
		
				
		// �����츦 ȭ�� �߾ӿ� ���� ��
		setSize(800,600);
		setVisible(true);
		setLocationRelativeTo(null);  // ����� ��ġ�� ���� ���̴�.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//updateData();
	}
	
	// (5) ��ư �̺�Ʈ!!! ->XCanvas
@Override 
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bt_prev) {
			prev();
		}else if(obj==bt_next) {
			next();
		}
	}
// (6) �ߺ��Ǵ� ���� �ƿ� ������ �������.
	public void updateData() {
		can.setSrc(dir+src[n]);
		can.repaint();
		// (6) ���񺯰�
		la_name.setText(src[n]+"("+(n+1)+"/"+src.length+")");
	}
	public void prev() {
		if(n>=1) {
			n--;			
		}else {
			JOptionPane.showMessageDialog(this, "���� �̹����� �����ϴ�.");
		}
		updateData();
	}
	public void next() {
		// �׸��� XCanvas�� ����ϹǷ�, �׷��� img�� ��������ְ� �ٽ� �׸���� �ϸ��.
		// �迭�� �Ѿ�� �ʴ� ���������� ++���
		if(n<src.length-1) {
			n++;			
		}else {
			JOptionPane.showMessageDialog(this, "������ �̹����Դϴ�.");
		}		
	}

	public static void main(String[] args) {
		new GalleyApp();
	}
}
