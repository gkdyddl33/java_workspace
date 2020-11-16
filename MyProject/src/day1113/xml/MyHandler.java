package day1113.xml;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 	xml �� ��� ��忡�� �̺�Ʈ�� �߻��� ������ �Ʒ��� �ڵ鷯 ��ü�� �޼��带 �˸°� �������̵� �ϸ� ��. 	
*/

public class MyHandler extends DefaultHandler{
	ArrayList<Pet> petList; // pets�ȿ� �ִ� pet��(�� ��) �ڷ������� ������ ���� Ŭ���� ����
	Pet pet;
	
	boolean isType=false;
	boolean isName=false;
	boolean isAge=false;
	boolean isGender=false;
	
	// ������ �����մϴ�. �Ǹ� ȣ���
	public void startDocument() throws SAXException {
		System.out.println("������ �����մϴ�.");
	}
	
	// ���� �±� ������ �� ȣ���..<�±�>
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.print("<"+tag+">");
		
		// 1) ���� �±� �� pets�� ������ ArrayList�� ��������.
		if(tag.equals("pets")) {// pets �� ������? ���� ����� �������� pet �� �ν��Ͻ� ����. 
			petList = new ArrayList<Pet>();			
			
		}else if(tag.equals("pet")) { //�ϳ��� pet �ν��Ͻ� ������ Ÿ���̴�!!
			pet = new Pet();
		}else if(tag.equals("type")) { //???
			isType=true;//���� �±׸� ���� �������� ������ �˷�����!!
		}else if(tag.equals("name")) { //???
			isName=true;
		}else if(tag.equals("age")) { //???
			isAge=true;
		}else if(tag.equals("gender")) { //???
			isGender=true;
		}
	}
	// �±׿� �±׻����� �����͸� ������ �� ȣ�� <>?<>
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// �迭�� ��Ʈ������ ��ȯ, String Ŭ������ �����ڱ��
		String data = new String(ch, start,length);
		//System.out.print(data);
		
		if(isType) {
			pet.setType(data);
			System.out.println("type: "+pet.getType());
		}else if(isName) {
			pet.setName(data);
		}else if(isAge) {
			pet.setAge(Integer.parseInt(data));
		}else if(isGender) {
			pet.setGender(data);
		}
	}
	
	// �ݴ� �±׸� ������ �� ȣ���..</�±�>
	public void endElement(String uri, String localName, String tag) throws SAXException {
		//System.out.println("</"+tag+">");
		// �ϳ��� ���� �� ���������..arrayList�� �������.
		if(tag.equals("pet")) { //�� ������ �ϳ��� pet�� �ϼ��� �����̹Ƿ�, ����Ʈ�� ��Ƶ���!!
			petList.add(pet);
			//����ΰ� �������� �ִ� ��ġ�� �˷��ִ� ��� �������� �ٽ� �ʱ�ȭ 
		}else if(tag.equals("type")) {
			isType=false;
		}else if(tag.equals("name")) {
			isName=false;
		}else if(tag.equals("age")) {
			isAge=false;
		}else if(tag.equals("gender")) {
			isGender=false;
		}
	}
	
	// ������ ������ ȣ��
	@Override
	public void endDocument() throws SAXException {
		System.out.println("������ �����մϴ�.");
		System.out.println("��� ����: �� "+petList.size()+"�� �����մϴ�.");
		for(Pet pet : petList) {
			System.out.println("type:"+pet.getType());
			System.out.println("name:"+pet.getName());
			System.out.println("age:"+pet.getAge());
			System.out.println("gender:"+pet.getGender());
			System.out.println("------------------------------------------");
		}
	}
}
