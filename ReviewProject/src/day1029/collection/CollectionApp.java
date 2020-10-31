package day1029.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;

public class CollectionApp {
	public void showList() {
		ArrayList<JButton> list = new ArrayList<>();
		list.add(new JButton("ù��ư"));
		list.add(new JButton("������ ��ư"));
		
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			JButton bt1 = list.get(i);
			System.out.println(bt1.getText());
		}
		
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("apple");
		list2.add("apple");
		list2.add("apple");
		System.out.println(list2.size());
	}
	public void showSet() {
		HashSet<String> set = new HashSet<>();
		set.add("banana");
		set.add("banana");
		set.add("banana");
		set.add("banana");
		System.out.println(set.size());
		
		HashSet<String> set2 = new HashSet<>();
		set2.add("�ٳ���");
		set2.add("����");
		set2.add("��纣��");
		
		// ������ ���� ������ get���� ���� �� ���� ����
		// �׷��� ������ ���� �ο� ����� �Ѵ�.
		Iterator<String> it = set2.iterator();
		while(it.hasNext()) {
			String e = it.next();
			System.out.println(e);
		}
		
	}
	public void showMap() {
		HashMap<String, String> map = new HashMap<>();
		map.put("k1", "���");
		map.put("k2", "ƫ��");
		map.put("k3", "�ҹ̲�");
		map.put("k3", "�Ȱ���");
		System.out.println(map.size());
		
		// ������ ����. �׸��� key������ value �� ���Ѵ�.
		// set�� iterator�� ���� �ϹǷ� key�� �Ϸķ� �þ�Ʈ����.
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		
		while(it.hasNext()) {
			String key = it.next();	// Ű �þ�Ʈ���Ÿ� 
			String value = map.get(key);	//���� ������ ����
			System.out.println(value);
		}

	}
	public static void main(String[] args) {
		CollectionApp app = new CollectionApp();
		//app.showList();
		//app.showSet();
		app.showMap();
	}
}
