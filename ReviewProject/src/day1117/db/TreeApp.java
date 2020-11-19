package day1117.db;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeApp extends JFrame{

	JTree tree;
	JScrollPane scroll;
	
	public TreeApp() {
		DefaultMutableTreeNode category = new DefaultMutableTreeNode("��ǰ����");
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("�Ǽ��縮");
		DefaultMutableTreeNode shoes = new DefaultMutableTreeNode("�Ź�");
		
		createNode(top,"����","�����","�Ͱ���","����");
		createNode(shoes,"����","�ȭ","������","����");
		
		category.add(top);
		category.add(shoes);
		
		tree = new JTree(category);
		scroll = new JScrollPane(tree);
		add(scroll);
		
		setSize(400,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void createNode(DefaultMutableTreeNode top,String a,String b,String c,String d) {// �ڽĵ��� ����.
		DefaultMutableTreeNode[] node = new DefaultMutableTreeNode[4];
		node[0] = new DefaultMutableTreeNode(a);
		node[1] = new DefaultMutableTreeNode(b);
		node[2] = new DefaultMutableTreeNode(c);
		node[3] = new DefaultMutableTreeNode(d);
		
		// ���� ��忡 ����!
		for(DefaultMutableTreeNode obj : node) {
			top.add(obj);
		}
	}
	public static void main(String[] args) {
		new TreeApp();
	}
}
