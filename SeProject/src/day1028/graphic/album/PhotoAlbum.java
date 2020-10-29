package day1028.graphic.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoAlbum extends JFrame implements ActionListener{
	// paint() �޼��带 ������ �Ϸ��� Ŭ������ �����ؾ� �Ѵ�. Ŀ���͸���¡
	AlbumPanel p;
	
	JPanel p_south;
	JButton bt_prev,bt_next;
	
	public PhotoAlbum() {
		// ����
		p = new AlbumPanel();
		p.setBackground(Color.YELLOW);
		p_south = new JPanel();
		bt_prev = new JButton("���� ����");
		bt_next = new JButton("���� ����");
		
		// ����
		add(p);	// center�� �ٹ��г� �ֱ�
		p_south.add(bt_prev);
		p_south.add(bt_next);
		// ���ʿ� ��ư�гγֱ�
		add(p_south,BorderLayout.SOUTH);
		
		// --------------------------------------------------------
		
		// (2) ������ ����
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		// ������
		setSize(500,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// (2) �׼��̺�Ʈ ����!
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();	// �̺�Ʈ�� ����Ų ������Ʈ ���ϱ�
		
		if(obj==bt_prev) {
			// ���� ��ư�̸� AlbumPanel(p).n�� ����
			p.n--;
		}else if(obj==bt_next) {
			// ���� ��ư�̸� AlbumPanel.n�� ����
			p.n++;
		}
		// ȭ�� ���� -> ���� AlbumPanel�� paint ȣ�� �Ұ��ؼ� repaint()
		p.repaint(); // �ٽñ׷��ּ���~ update() -> paint()
		
	}
	public static void main(String[] args) {
		new PhotoAlbum();
	}
	
}
