package day1029.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;

/*
 *	Collection Framework
 *	: ���� ������ ��ü���� ������� �Ѵ�.
 *
 *	- �ڹ� ������ ��ü�� ��Ƽ� ó���� �� api�� �����ϴµ�, �� api�� ������ 
 *	�÷��� �����ӿ� �̶�� �Ѵ�.
 *	�׸��� java.util ���� �����Ѵ�.
 *
 *	- �÷��� �����ӿ����� �����ϴ� ��ü�� ���� ����� ����� ������, ũ�Դ� ���� ����� ����
 *	(1) �����մ� ���� List�� : �迭�� ���� ����. [] []
 *										�ڹ��� �迭�� ���̰� �ִٸ�? 
 *										�迭�� 1) �ݵ�� �����Ҷ� ũ�⸦ ����ؾ� �ϹǷ� �������� �þ �� ����(������)
 *												 2) �ڷ����� ���� ����� �� ����.
 *													ex. int[] arr = new int[5]; // ���� int���� 
 *										List�� ũ�Ⱑ �����Ӵ�. ��ü �ڷ����� ��� ���� �� �ִ�.
 *	(2) �������� ���� Set�� :
 *	(3) key-value ���� Map�� :  
 */
public class CollectionApp {
	
	private void showList() {
		// List�� - �ֻ��� ��ü�� List�� �������̽� �̸�, List�� �⺻������ ������ �� 
		// �߻�޼��尡 ��õǾ� �ִ�.
		// ex. ArrayList, Stack, Vector
		// ����Ʈ �������� �迭�� ���� ����ϴ�. = js�� �迭�� ����) ���۹��
		// <>�� Generic Type���� �����ϸ�, �÷��� �����ӿ��� ���� �� �ִ� �ڷ����� ������ �� �ִ�.		
		// <> Ư����Ҹ� ������ �� �ִ� �ڷ�����..
		ArrayList<JButton> list = new ArrayList<JButton>();
		list.add(new JButton("ù��ư"));
//		list.add("���");
//		list.add("������");
//		list.add("���");
		list.add(new JButton("��������ư"));
		System.out.println("����Ʈ�� ������ ���� "+list.size()); // �÷��������ӿ��� ��� size()�� ���
		
		// ��� ���Ƿ�, get(��ȯ���ش�)�� ������ �� ���� ������ Object���� ��ȯ���ش�.
		for (int i = 0; i < list.size(); i++) {
			JButton bt1 = list.get(i);
			System.out.println(bt1.getText());
		}
		
		// List�� �ߺ��� �����͸� ����ұ�?
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("apple");
		list2.add("apple");
		list2.add("apple");
		System.out.println("list2�� ������ ���� "+list2.size()); // 3
		// -> ��� : �ߺ����δ� ������ �ʰ� �� �ִ´�.
	}
	private void showset() {
		HashSet<String> set = new HashSet<String>();
		set.add("banana");
		set.add("banana");
		set.add("banana");
		set.add("banana");
		System.out.println("Hashset�� ũ��� "+set.size());
		// -> ��� : List�� �ٸ��� �ȶ��ϴ�. �ߺ��� �����͸� �޾Ƶ����� �ʴ´�.

		HashSet<String> set2 = new HashSet<String>();
		set2.add("�ٳ���");
		set2.add("����");
		set2.add("��纣��");
		
		// set�� ������ x ������ ������ �ο������ �ȴ�.(iterator�ݺ���)
		Iterator<String> it = set2.iterator();
		while(it.hasNext()) {// ������ �ο��ߴ�? �׷� �ȿ� �ִ��� �Ǵ��� �ٰ�!
			// next() �δ� ������ �̾Ƴ��� �� = ��� ��ȯ
			String e = it.next();
			System.out.println(e);
		}
		
	}
	private void showmap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("k1", "���");
		map.put("k2", "ƫ��");
		map.put("k3", "�Ȱ���");
		map.put("k4", "�Ȱ���");
		System.out.println("map�� ���̴� "+map.size());
		// -> ��� : key���� �ߺ��� ������� �ʴ´�. ������, ���� �ߺ��� ����
		
		// �ݺ����� �̿��ؼ� ��� ���� ����ϱ�
		Set<String> keySet = map.keySet();	// key�� ���� ������ �׷���  value�� ���Ѵ�.
																// �׸��� Set�� Iterator�� �����ϹǷ� key�� �Ϸķ� �þ�߸���.
		Iterator<String> keyIter = keySet.iterator();
		
		while(keyIter.hasNext()) {
			String key = keyIter.next();	// <>�� ������� ������ (String)���� ����ȯ �������
			String value = map.get(key);	// ������ ��ȯ!!
			System.out.println(value);
		}
	}
	public static void main(String[] args) {
		CollectionApp app = new CollectionApp();
		app.showList();
		app.showset();
		app.showmap();
	}
}
