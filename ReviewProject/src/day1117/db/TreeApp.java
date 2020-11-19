package day1117.db;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeApp extends JFrame{

	JTree tree;
	JScrollPane scroll;
	
	public TreeApp() {
		DefaultMutableTreeNode category = new DefaultMutableTreeNode("상품정보");
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("악세사리");
		DefaultMutableTreeNode shoes = new DefaultMutableTreeNode("신발");
		
		createNode(top,"반지","목걸이","귀걸이","팔찌");
		createNode(shoes,"구두","운동화","슬리퍼","고무신");
		
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
	
	public void createNode(DefaultMutableTreeNode top,String a,String b,String c,String d) {// 자식들을 넣자.
		DefaultMutableTreeNode[] node = new DefaultMutableTreeNode[4];
		node[0] = new DefaultMutableTreeNode(a);
		node[1] = new DefaultMutableTreeNode(b);
		node[2] = new DefaultMutableTreeNode(c);
		node[3] = new DefaultMutableTreeNode(d);
		
		// 하위 노드에 부착!
		for(DefaultMutableTreeNode obj : node) {
			top.add(obj);
		}
	}
	public static void main(String[] args) {
		new TreeApp();
	}
}
