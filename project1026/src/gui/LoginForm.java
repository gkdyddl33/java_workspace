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

public class LoginForm extends Frame{// window를 올린 것과 같음 is a관계
// 위에는 grid 아래 버튼란은 flow
	/*has 관계는 멤버변수가 객체형일때를 의미한다.*/
	Label la_id;
	Label la_pass;
	TextField t_id;
	TextField t_pass;
	Button bt_login;
	Button bt_regist;	
	Panel p_center;		// 센터에 그리드를 적용할 패널
	Panel p_south;		// 남쪽에 붙일 패널(여기에 버튼 2개 붙일예정)
	
	public LoginForm() {
		// 생성자로 초기화
		// 부품관계로 보유한 변수들을 모두 초기화 시키자.(생성)
		la_id = new Label("ID");
		la_pass = new Label("Password");
		t_id = new TextField(15);
		t_pass = new TextField(15);
		bt_login = new Button("Login");
		bt_regist = new Button("가입");
		p_center = new Panel();
		p_south = new Panel();
		
		// 생성만 한 상태
		// Frame은 개발자가 레이아웃을 지정하지 않으면 디폴트가 BorderLayout이다.
		// this.setLayout(new BorderLayout());
		// 처음 보는 객체에 대한 대응법 1. 뭐하는 객체인지 파악 2. 메모리에 올리는 법
		// p_center.setBackground(Color c);
		// p_center.setBackground(new Color(153,255,0));
		
		// 색깔은 상수이다. 상수는 직관성을 부여한 데이터이다.
		// final로 더 이상 값을 변경할 수 없으며, static으로 인스턴스간
		// 공유가 가능하며, public 으로 선언하여 모든 객체가 접근할 수 있도록 
		// 접근 제한을 풀어 놓은 데이터		
		p_center.setBackground(Color.GREEN);
		p_south.setBackground(Color.YELLOW);
		// 센터패널을 윈도우의 BorderLayout 센터에 넣자.
		this.add(p_center,BorderLayout.CENTER);
		this.add(p_south,BorderLayout.SOUTH);
		
		// p_center에 그리드 적용
		p_center.setLayout(new GridLayout(2,2));
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pass);
		p_center.add(t_pass);
		
		// panel은 아무런 배치관리자를 적용하지 않으면 디폴트가 flowlayout이다.
		p_south.add(bt_login);
		p_south.add(bt_regist);
		
		// frame은 나 자신 = window = this(or 생략) = 레퍼런스 변수
		// 나 자신으 인스턴스의 주소값을 가진 this , 해당 인스턴스가
		// 자기 자신을 가리킬 때..인스턴스 매서드 내에서 사용해야 된다.
		this.setSize(400, 150);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		new LoginForm();
	}
	
}
