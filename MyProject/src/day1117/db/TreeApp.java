package day1117.db;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 	�����Ͱ� ����ȭ�� ���԰��踦 ǥ���� �� ���� ���Ǵ� Tree�� �������.
*/
public class TreeApp extends JFrame{

	JTree tree;
	JScrollPane scroll;
	
	public TreeApp() {// �� Ʈ���� ������ ��带 �����غ���.
		DefaultMutableTreeNode category=new DefaultMutableTreeNode("��ǰ����");
		DefaultMutableTreeNode top=new DefaultMutableTreeNode("��������");
		DefaultMutableTreeNode shoes=new DefaultMutableTreeNode("�Ź�");	
			
	   createNode(top,"����","�����","�Ͱ���","����");
	   createNode(shoes, "����","�ȭ","������","����");
	   
	   category.add(top);
	   category.add(shoes);
	   
	   
	   tree=new JTree(category);//�ֻ��� ��带 �������� �μ��� �ֱ�
	   scroll= new JScrollPane(tree);
	   
	   add(scroll);
		
		setSize(400,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public void createNode(DefaultMutableTreeNode top,String a,String b,String c,String d) {
		// �Ѱܹ��� ž ��忡 ���ϴ� ���� ��带 �����Ͽ� �ڽĳ��� ������.
	   DefaultMutableTreeNode[]node=new DefaultMutableTreeNode[4];
	   node[0]=new DefaultMutableTreeNode(a);
	   node[1]=new DefaultMutableTreeNode(b);
	   node[2]=new DefaultMutableTreeNode(c);
	   node[3]=new DefaultMutableTreeNode(d);
	   
	   //������ ��带 top����� �������� ����!!
	   for(DefaultMutableTreeNode obj : node) {
	      top.add(obj);
	   }
	}
	public static void main(String[] args) {
		new TreeApp();
	}
}


