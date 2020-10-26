package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class LoginForm extends Frame{// window�� �ø� �Ͱ� ���� is a����
// ������ grid �Ʒ� ��ư���� flow
	/*has ����� ��������� ��ü���϶��� �ǹ��Ѵ�.*/
	Label la_id;
	Label la_pass;
	TextField t_id;
	TextField t_pass;
	Button bt_login;
	Button bt_regist;	
	Panel p_center;		// ���Ϳ� �׸��带 ������ �г�
	Panel p_south;		// ���ʿ� ���� �г�(���⿡ ��ư 2�� ���Ͽ���)
	
	public LoginForm() {
		// �����ڷ� �ʱ�ȭ
		// ��ǰ����� ������ �������� ��� �ʱ�ȭ ��Ű��.(����)
		la_id = new Label("ID");
		la_pass = new Label("Password");
		t_id = new TextField(15);
		t_pass = new TextField(15);
		bt_login = new Button("Login");
		bt_regist = new Button("����");
		p_center = new Panel();
		p_south = new Panel();
		
		// ������ �� ����
		// Frame�� �����ڰ� ���̾ƿ��� �������� ������ ����Ʈ�� BorderLayout�̴�.
		// this.setLayout(new BorderLayout());
		// ó�� ���� ��ü�� ���� ������ 1. ���ϴ� ��ü���� �ľ� 2. �޸𸮿� �ø��� ��
		// p_center.setBackground(Color c);
		// p_center.setBackground(new Color(153,255,0));
		
		// ������ ����̴�. ����� �������� �ο��� �������̴�.
		// final�� �� �̻� ���� ������ �� ������, static���� �ν��Ͻ���
		// ������ �����ϸ�, public ���� �����Ͽ� ��� ��ü�� ������ �� �ֵ��� 
		// ���� ������ Ǯ�� ���� ������		
		p_center.setBackground(Color.GREEN);
		p_south.setBackground(Color.YELLOW);
		// �����г��� �������� BorderLayout ���Ϳ� ����.
		this.add(p_center,BorderLayout.CENTER);
		this.add(p_south,BorderLayout.SOUTH);
		
		// p_center�� �׸��� ����
		p_center.setLayout(new GridLayout(2,2));
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pass);
		p_center.add(t_pass);
		
		// panel�� �ƹ��� ��ġ�����ڸ� �������� ������ ����Ʈ�� flowlayout�̴�.
		p_south.add(bt_login);
		p_south.add(bt_regist);
		
		// frame�� �� �ڽ� = window = this(or ����) = ���۷��� ����
		// �� �ڽ��� �ν��Ͻ��� �ּҰ��� ���� this , �ش� �ν��Ͻ���
		// �ڱ� �ڽ��� ����ų ��..�ν��Ͻ� �ż��� ������ ����ؾ� �ȴ�.
		this.setSize(400, 150);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		new LoginForm();
	}
	
}
