package event;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;

public class MyWin extends Frame{
	Button bt;
	TextField t;	// event�߰�
	Choice ch;		// html������ select �ڽ��� ���� , onchange
	
	public MyWin() {
		bt= new Button("�� ������");
		t =  new TextField(15);
		ch = new Choice();
		
		this.setLayout(new FlowLayout());
		// ��ư�� �����쿡 ���� - �������� ����Ʈ�� BorderLayout
		this.add(bt);
		this.add(t);  
		this.add(ch);
		
		// (4) ch�� ������ ä���
		ch.add("Java Programming");
		ch.add("Jsp Programming");
		ch.add("Android Programming");
		ch.add("Spring Programming");
		ch.add("Mybatis Programming");
		
		ch.addItemListener(new MyItem()); // ���̽��� �����ʿ� ����
		
		// (5) windowListener
		addWindowListener(new MyWindowListner()); // �����Ӱ� �������� ����
		
		// (1)��ư�� �����ʿ� ���� ex. bt.addEventListener()
		bt.addActionListener(new MyListner()); // event�߰�
		
		// (2) �ؽ�Ʈ�ڽ��� ������ ����
		t.addKeyListener(new MyKey());
		
		// (3) ���콺
		// frame�� ���콺 ������ ����(��ü)
		this.addMouseListener(new MyMouse());
		
		this.setSize(300,400);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyWin();
	}
}
