package day1116.pub.api;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/*
 	SAX Parsing �� ��� node(���, �ؽ�Ʈ �� xml �� �̷�� ��ҵ��� ������) ����
 	�̺�Ʈ�� �߻������ִ� ��ü
 	���������������� ���� �����͸� �������� ���ؼ��� Parsing�� �ؾ� ��..
*/
public class MountainHandler extends DefaultHandler{
	// �߰ߵǴ� ���� ���� �� VO�� ������, �� VO�� ��� �� ����
	Vector<Mountain> mtList;
	Mountain mt;	// ���� ���� 1���� �ӽ������� ���� ����
	
	// ���� ����ΰ� ��� ��ġ�� ���������� �˱� ���� ����
	boolean isMntnid;
	boolean isMntnnm;
	boolean isMntninfopoflc;
	boolean isMntninfohght;
	
	public void startDocument() throws SAXException {
		// ������ ���۵Ǹ� ���͸� �����Ѵ�.		
	}
	
	// �����±�
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.println("<"+tag+">");
		
		if(tag.equals("items")) {// ���⼭ ���� ����
			mtList = new Vector<Mountain>();
		}else if(tag.equals("item")) {// ���⼭ VO ����
			mt = new Mountain();
		}else if(tag.equals("mntnid")) {// ����ΰ� ��������.
			isMntnid=true;
		}else if(tag.equals("mntnnm")) {
			isMntnnm=true;
		}else if(tag.equals("mntninfopoflc")) {
			isMntninfopoflc=true;
		}else if(tag.equals("mntninfohght")) {
			isMntninfohght=true;
		}
	}
	
	// �߰��±�
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		System.out.println(data);
		
		//�±���ġ�� ���� ������ ��� 
		if(isMntnid) {
			mt.setMntnid(Integer.parseInt(data)); //���� ���̵� ���
		}else if(isMntnnm) {
			mt.setMntnnm(data);//���� �̸�
		}else if(isMntninfopoflc) {
			mt.setMntninfopoflc(data);//���� ��ġ
		}else if(isMntninfohght) {
			mt.setMntninfohght(Integer.parseInt(data)); //���ǳ���
		}
	}
	
	// �ݴ��±�
	public void endElement(String uri, String localName, String tag) throws SAXException {
		System.out.println("</"+tag+">");	
		if(tag.equals("mntnid")) {// ����ΰ� ��������.
			isMntnid=false;
		}else if(tag.equals("mntnnm")) {
			isMntnnm=false;
		}else if(tag.equals("mntninfopoflc")) {
			isMntninfopoflc=false;
		}else if(tag.equals("mntninfohght")) {
			isMntninfohght=false;
		}else if(tag.equals("item")) {
			mtList.add(mt);			
		}
	}	
	
	public void endDocument() throws SAXException {
		System.out.println("�˻��� ���� ������ "+mtList.size());
		
		for(int i=0;i<mtList.size();i++) {
			Mountain obj = mtList.get(i);
			System.out.println("��ID"+obj.getMntnid());
			System.out.println("���̸�"+obj.getMntnnm());
			System.out.println("����ġ"+obj.getMntninfopoflc());
			System.out.println("�����"+obj.getMntninfohght());
			System.out.println("------------------------------------------------");
		}
	}
}
