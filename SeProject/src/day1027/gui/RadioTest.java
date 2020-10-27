package day1027.gui;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.Frame;

/*
 *	1) 단축키 모두보기 - ctrl + shift + L 
 * 2) 자동 임포트 : 해당 클래스에 커서 올려놓고, ctrl + shift + O
 * 3) 해당 객체의 api 문서 바로가기 : 해당 클래스 커서 올린 후 shift + F2
 * 												  인터넷 연결이 되있다면..
 */
public class RadioTest extends Frame{
	// 자바에서는 체크박스를 "라디오" 로 사용
	CheckboxGroup group = new CheckboxGroup();

	public RadioTest() {
		group = new CheckboxGroup();
		setLayout(new FlowLayout());
		this.add(new Checkbox("운동",group,false));
		this.add(new Checkbox("독서",group,false));
		this.add(new Checkbox("컴퓨터",group,false));
		this.add(new Checkbox("영화",group,false));
		this.add(new Checkbox("요리",group,false));
		this.add(new Checkbox("애견돌보기",group,true));
		
		setSize(300,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new RadioTest();
	}

}
