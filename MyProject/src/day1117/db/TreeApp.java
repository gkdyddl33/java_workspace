package day1117.db;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/*
 	데이터간 구조화된 포함관계를 표현할 때 흔히 사용되는 Tree를 배워보자.
*/
public class TreeApp extends JFrame{

	JTree tree;
	JScrollPane scroll;
	
	public TreeApp() {// ★ 트리에 보여질 노드를 구성해보자.
		DefaultMutableTreeNode category=new DefaultMutableTreeNode("상품정보");
		DefaultMutableTreeNode top=new DefaultMutableTreeNode("엑세서리");
		DefaultMutableTreeNode shoes=new DefaultMutableTreeNode("신발");	
			
	   createNode(top,"반지","목걸이","귀걸이","팔찌");
	   createNode(shoes, "구두","운동화","슬리퍼","고무신");
	   
	   category.add(top);
	   category.add(shoes);
	   
	   
	   tree=new JTree(category);//최상위 노드를 생성자의 인수로 넣기
	   scroll= new JScrollPane(tree);
	   
	   add(scroll);
		
		setSize(400,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public void createNode(DefaultMutableTreeNode top,String a,String b,String c,String d) {
		// 넘겨받은 탑 노드에 원하는 하위 노드를 생성하여 자식노드로 붙이자.
	   DefaultMutableTreeNode[]node=new DefaultMutableTreeNode[4];
	   node[0]=new DefaultMutableTreeNode(a);
	   node[1]=new DefaultMutableTreeNode(b);
	   node[2]=new DefaultMutableTreeNode(c);
	   node[3]=new DefaultMutableTreeNode(d);
	   
	   //생성된 노드를 top노드의 하위노드로 부착!!
	   for(DefaultMutableTreeNode obj : node) {
	      top.add(obj);
	   }
	}
	public static void main(String[] args) {
		new TreeApp();
	}
}


