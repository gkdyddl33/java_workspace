package day1106.db;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import common.image.ImageUtil;

/*���θ� ��ǰ���� ���α׷� �����ϱ�*/
public class ShoppingApp extends JFrame {	
	JPanel p_west;	// ��ü �� ����
	JPanel p_center;	// ��ü �� �߰�
	JPanel p_east;	// ��ü �� ����
	
	JPanel c_north;	// ��� �� ����
	JPanel c_center;	// ��� �� �߰� - ���̺� ���� ����
	
	// ����� ����(����)
	Choice ch_top;
	Choice ch_sub;
	JTextField t_name;
	JTextField t_brand;
	JTextField t_price;
	JButton bt_find;	// �̹��� ã�ƺ���
	JButton bt_collect; // 11) �̹��� �����ϱ�(���� �ϵ��ũ��..), �̹��� ��ü �ܾ����
	JPanel can;	// �̹��� �̸����� �׷��� ��
	JButton bt_regist;
	CollectorFrame collectorFrame; // 11) ��ư ������ ������ ����
	
	// �˻�����(����)	
	Choice ch_category;	// �˻� ī�װ���	
	JTextField t_keyword;	// �˻���
	JButton bt_search;	// �˻���ư
	JTable table;
	JScrollPane scroll;
	
	//  (����)
	Choice ch_top2;
	Choice ch_sub2;
	JTextField t_name2;
	JTextField t_brand2;
	JTextField t_price2;
	JButton bt_find2;	// �̹��� ã�ƺ���
	JPanel can2;	// �̹��� �̸����� �׷��� ��
	JButton bt_edit;	// ������ư
	JButton bt_del;	// ������ư
	
	// 1) ����
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";
	
	Connection con;	// ������ �Ϸ����� Ȯ���� ���ִ� �������̽�
	HashMap<String,Integer> map = new HashMap<>();	// �÷��� �����ӿ� ��, key - value �� ������ ��ü�� �������ִ� ��ü
	HashMap<String,Integer> map2 = new HashMap<>();	// �÷��� �����ӿ� ��, key - value �� ������ ��ü�� �������ִ� ��ü
	
	JFileChooser chooser = new JFileChooser("D:/workspace/java_workspace/SeProject/res/travel2");	// 6)
	Toolkit kit= Toolkit.getDefaultToolkit();	// �÷��� �������� ��η� ������ ���� ��Ŷ ����.
	Image img;	
	File file;
	ProductController productController;
	

	public ShoppingApp() {
		// ����(����)
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		bt_find = new JButton("�̹��� ã��");
		bt_collect = new JButton("���ͳ� ����");	// 11) ����
		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can);
			}
		};
		bt_regist = new JButton("���");
		
		// ����(����)
		ch_top.add("choose category"); // ����ī�װ��� ù ����
		
		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(t_name);
		p_west.add(t_brand);
		p_west.add(t_price);
		p_west.add(bt_find);
		p_west.add(bt_collect);	// 11) ����
		p_west.add(can);
		p_west.add(bt_regist);
		
		// ��Ÿ��
		p_west.setPreferredSize(new Dimension(150,600));
		//p_west.setBackground(Color.YELLOW);
		
		ch_top.setPreferredSize(new Dimension(135,40));
		ch_sub.setPreferredSize(new Dimension(135,40));
		
		t_name.setPreferredSize(new Dimension(135,30));
		t_brand.setPreferredSize(new Dimension(135,30));
		t_price.setPreferredSize(new Dimension(135,30));
		
		bt_find.setPreferredSize(new Dimension(135,30));
		bt_collect.setPreferredSize(new Dimension(135,30));
		can.setPreferredSize(new Dimension(135,115));
		
		// �����ӿ� ���ʿ��� ���̱�		
		add(p_west,BorderLayout.WEST);
		
		// ����(����)
		c_north = new JPanel();
		c_center = new JPanel();
		ch_category = new Choice();
		t_keyword = new JTextField();
		bt_search = new JButton("�˻�");
		// 8) jtable�� �׷���..������� ǥ�� �����.
		table = new JTable(productController = new ProductController());
		scroll = new JScrollPane(table);
		
		// ����(����)
		c_north.add(ch_category);
		c_north.add(t_keyword);
		c_north.add(bt_search);
		
		c_center.setLayout(new BorderLayout());
		c_center.add(scroll);
		
		// 13) �˻������� ����
		ch_category.add("product_name");
		ch_category.add("brand");
		
		// ��Ÿ��
		c_north.setBackground(Color.PINK);
		ch_category.setPreferredSize(new Dimension(130,30));
		t_keyword.setPreferredSize(new Dimension(400,30));
		bt_search.setPreferredSize(new Dimension(120,30));
				
		// �����ӿ� ���Ϳ��� ���̱� - �� �г� ����
		p_center = new JPanel();
		p_center.setLayout(new BorderLayout());
		p_center.add(c_north,BorderLayout.NORTH);
		p_center.add(c_center);
		
		add(p_center);
		
		// ����(����)
		p_east = new JPanel();
		ch_top2 = new Choice();
		ch_sub2 = new Choice();
		t_name2 = new JTextField();
		t_brand2 = new JTextField();
		t_price2 = new JTextField();
		bt_find2 = new JButton("�̹��� ã��");
		can2 = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can2);
			}
		};
		bt_edit = new JButton("����");
		bt_del = new JButton("����");
		
		// ����(����)
		p_east.add(ch_top2);
		p_east.add(ch_sub2);
		p_east.add(t_name2);
		p_east.add(t_brand2);
		p_east.add(t_price2);
		p_east.add(bt_find2);
		p_east.add(can2);
		p_east.add(bt_edit);
		p_east.add(bt_del);
		
		// ��Ÿ��
		p_east.setPreferredSize(new Dimension(150,600));
		//p_east.setBackground(Color.YELLOW);
		
		ch_top2.setPreferredSize(new Dimension(135,40));
		ch_sub2.setPreferredSize(new Dimension(135,40));
		
		t_name2.setPreferredSize(new Dimension(135,30));
		t_brand2.setPreferredSize(new Dimension(135,30));
		t_price2.setPreferredSize(new Dimension(135,30));
		
		can2.setPreferredSize(new Dimension(135,115));
		bt_find2.setPreferredSize(new Dimension(135,30));
		bt_edit.setPreferredSize(new Dimension(135,30));
		bt_del.setPreferredSize(new Dimension(135,30));
		
		// �����ӿ� ���ʿ��� ���̱�		
		add(p_east,BorderLayout.EAST);
		
		// (*) ȣ��
		connect();
		getTopList();
		getProductList(); // ��ǰ ���� ���
		
		// 2) ������ â ������, ����Ŭ���� ���� ����, ���μ����� ����
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// �������� + ���μ��� ����
				disconnect();
				System.exit(0);
			}
		});
		
		// 4) ch_top�� ������ ���� ��? ���� ī�װ��� ���̰� �ϱ� ����
		ch_top.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// �ٲ� ������ �̿��Ͽ� ���� ī�װ����� ��������.
				// hashMap���� ���� key���� �̿��Ͽ� values�� ���ϱ�
				
				// ���� ���� �������� ���ܽ��Ѿ� �Ѵ�.. �׳� �����ֱ��̹Ƿ�
				//System.out.println("������ �������� index�� "+ch_top.getSelectedIndex());
				if(ch_top.getSelectedIndex()>0) {
					System.out.println("���� ������ Key�� "+ch_top.getSelectedItem());
					
					int topcategory_id = map.get(ch_top.getSelectedItem());
					System.out.println("�����κ��� ����� value�� "+topcategory_id);
					
//					String sql = "SELECT * FROM SUBCATEGORY WHERE TOPCATEGORY_ID ="+topcategory_id;
//					System.out.println(sql);
					getSubList(topcategory_id);
				}
			}
		});
		
		// 6) ����ã�� ��ư�� ������ ���� = �̹��� ã��
		bt_find.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				findImage();	// ���θ��� ����� ��ǰ �̹��� ����				
				preview();
			}
		});
		
		// 7) ��Ϲ�ư�� ������ ����
		bt_regist.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				regist();
				// 10) UI���� --> product ����Ʈ�� �ۼ��ϰ� �� �� �ٷ� ���Ž�Ű��
				//						  ���ΰ�ħ ȿ��
				// ��ϵ� ��� �������� -> ����
				getProductList();
				table.updateUI();
			}
		});
		
		// 11) �̹��� ���ͳ����� �����ϱ� ��ư�� ����
		bt_collect.addActionListener(new ActionListener() {	
			// �Ǵٸ� â�� ���..Field�� URL�� ���� ���̴� 
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� �͸����� �ܺ�Ŭ���� �ν��Ͻ��� �̷��� �������. �Ű����� �ޱ�
				collectorFrame = new CollectorFrame(ShoppingApp.this);
			}
		});
		
		// 13) �˻���� ����(��������)
		bt_search.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String category = ch_category.getSelectedItem();//������ �˻� �з�
				String keyword = t_keyword.getText(); //�Է��� Ű����
						
				getSearchResult(category , keyword);
				table.updateUI();//���̺� ����
			}
		});
		
		// 14) ���콺 ������ ���� -> (����) �󼼺��� ����
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();	// ������ ��
				int col = table.getSelectedColumn();	// ������ ��
						
				String value = (String)table.getValueAt(row, col);
				System.out.println(value);
				
				
				// 15) ������ ��ǰ�� �˸´� ī�װ��� ���õǾ� �ְ�..
				// topcategory_id = subcategory_id
				setCategory(row);
				setSubCategory(row);
				getDetail(row); 	// �󼼺��� ���
				
				String filename = (String)table.getValueAt(row, 5);
				getTargetImage("D:/workspace/java_workspace/SeProject/res/travel2/"+filename);// ������ �̹��� �׸���
				can2.repaint();
				
				
			}
		});
		
		// ������
		setSize(1000,600);
		setLocationRelativeTo(null);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 1) ����Ŭ ����
	public void connect() {
		try {
			// ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ����
			con = DriverManager.getConnection(url,user,password);
			if(con == null) {
				JOptionPane.showMessageDialog(this, "���ӽ���");
				System.exit(0);
			}else {
				// ������ â�� ���������� �����ߴٴ� �޽���
				this.setTitle(user+"�� ���� ��");
			}
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3) ����ī�װ��� ��������
	public void getTopList() {
		String sql = "select * from topcategory";
		
		// ������ ���� ��ü = PreparedStatement
		// ������ ��� ������ ��� ��ü = ResultSet
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql); // ������ �غ�
			rs = pstmt.executeQuery();	// ��������
			
			while(rs.next()) {// 1ĭ ����			
				// ����ڵ��� ���� �� ������!
				ch_top.add(rs.getString("name"));	
				ch_top2.add(rs.getString("name"));	// 14) �󼼺��� ī�װ���
				
				// 4) hashMap - key,value ������ �����ֱ�
				map.put(rs.getString("name"), rs.getInt("topcategory_id"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}		
	}
	// 5) ���� ī�װ��� ��������
	public void getSubList(int topcategory_id) {
		String sql = "SELECT * FROM SUBCATEGORY WHERE TOPCATEGORY_ID ="+topcategory_id;
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			// ����ī�װ��� ä���
			// �׳� ä��� �������� ����.. ä������� ��� �����(�ʱ�ȭ)			
			ch_sub.removeAll(); // ��� �����!
			ch_sub2.removeAll();
			
			ch_sub.add("choose category");
			ch_sub2.add("choose category");
			while(rs.next()) {
				ch_sub.add(rs.getString("name"));
				ch_sub2.add(rs.getString("name"));	// 14) �󼼺��� ī�װ���
				// 7) �Է��� �� ���
				map2.put(rs.getString("name"),rs.getInt("subcategory_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}		
	}
	// 6) ��ǰ�̹��� ����
	public void findImage() {
		if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
			// Ȯ�� �����ٸ�? ���������� ���Ѵ�.
			file = chooser.getSelectedFile();
			System.out.println("����� ���� ������ ������ ����:"+file.getAbsolutePath());
		
			// ������� img ���� ���Ѵ�.
			//img = kit.getImage(file.getAbsolutePath());
			//img = ImageUtil.getCustomSize(img, 135, 115);	// img�� �������� img�� ũ�⸦ �����ؼ� �ٽ� ����
			// ------------> �޼���� ����..12) �� ����
			getTargetImage(file.getAbsolutePath());
		}
	}
	// 6 - 12) �׷��� �̹��� ���ϱ�
	public void getTargetImage(String path) {
		img = kit.getImage(path);
		img = ImageUtil.getCustomSize(img, 135, 115);	
		
	}
	
	// 6) �̸����� ����
	public void preview() {
		// paint�� �׸�ó�� can
		can.repaint();
	}
	// 7) ��Ϲ�ư
	public void regist() {

		// ����ǥ �� �������� = hashMap
		int subcategory_id = map2.get(ch_sub.getSelectedItem());
		String product_name = t_name.getText();
		String brand = t_brand.getText();
		int price = Integer.parseInt(t_price.getText());
		String filename = file.getName();	//Ǯ��� ���� '�̹�����' ��..
		
//		System.out.println("subcategory_id"+subcategory_id);
//		System.out.println("product_name"+product_name);
//		System.out.println("brand"+brand);
//		System.out.println("price"+price);
//		System.out.println("filename"+filename);
		
		String sql = "insert into product(product_id,subcategory_id,product_name,brand,price,filename)";
		sql += " values(seq_product.nextval, ?,?,?,?,?)";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, subcategory_id);
			pstmt.setString(2, product_name);
			pstmt.setString(3, brand);
			pstmt.setInt(4, price);
			pstmt.setString(5, filename);
			
			// �Ʒ��� �޼����� ��ȯ��? �� �������� ���� ����޴� ���ڵ� ���� ��ȯ,
			// ���� insert ��쿣 ������ ������ 1
			// update, delete�� ������ ��� 0, �����̸� 1�̻���..
			int result = pstmt.executeUpdate();
			if(result==0){
				JOptionPane.showMessageDialog(this, "��Ͻ���");
			}else {
				JOptionPane.showMessageDialog(this, "��ϼ���");
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 9) product ���̺��� ���ڵ� ��������
	public void getProductList() {
		String sql = "select * from product";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// ** PrepareStatement ������ �μ� 2���� �Ѱ�, ���Ĺ������� �����Ӱ� �̵������ϰ� �� �� �ִ�.
			//		rs.beforeFirst();
			// 		��ũ��(Ŀ��) �����̰� �ϰ�, �б����� �μ��� �ִ´� = �����ο� rs�� ���������.= �� ÷���� �ʱ�ȭ ��
			pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			rs = pstmt.executeQuery();
		
			rs.last();	// Ŀ���� ���������� ������
			int currenRow = rs.getRow();
//			System.out.println("���� Ŀ���� ����Ű�� ���ڵ� ��ȣ�� " +currenRow);
			System.out.println("�������� ������ Ŀ���� rowNum " +currenRow);
						
			// [�� ���ڵ��][�÷�]
			String[][] data = new String[currenRow][productController.column.length];

			// ������ �迭�� �����͸� ��������, Ŀ���� �ٽ� ���� ���ͽ��Ѿ� �Ѵ�.
			// Ŀ���� ��������� ������ �� �ִ� ResultSet�� ������ �ȴ�.
			// �ٽ� ó���� ������ Ŀ���� ��������.= first()
			// 0��° ��ġ�� �ǵ���(��, ��ġ �ʱ�ȭ)
			rs.beforeFirst();
			int index = 0;
			while(rs.next()) {// ��ó������ �ʱ�ȭ �� �����̹Ƿ� ������ ������Ű��
				// (1�����迭) �� �Ӽ��� ���� �ְ�..2���� �迭�� ���� ���� ����
				String[] record = new String[6];
				record[0] = rs.getString("product_id");
				record[1] = rs.getString("subcategory_id");
				record[2] = rs.getString("product_name");
				record[3] = rs.getString("brand");
				record[4] = rs.getString("price");
				record[5] = rs.getString("filename");
				
				// (2�����迭) �� ������ �迭�� ���� ������ �迭�� ���� ����.
				data[index++] = record;
				System.out.println(index);
			}
			// ----------> �ϼ��� ������ �迭�� productController�� ������ data �迭 �ּҷ� ���Խ�Ű��.
			productController.data=data;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	// 13) �˻���� ����ϱ� = getProductList() �� ������ ���� �� �Ȱ��� 
	public void getSearchResult(String category,String keyword) {
//		String sql = "select * from product where �귣��, ��ǰ�� like '%�˻���%'";
		String sql="select * from product where "+category+" like '%"+keyword+"%'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			rs = pstmt.executeQuery();
		
			rs.last();	// Ŀ���� ���������� ������
			int currenRow = rs.getRow();
//			System.out.println("���� Ŀ���� ����Ű�� ���ڵ� ��ȣ�� " +currenRow);
			System.out.println("�������� ������ Ŀ���� rowNum " +currenRow);
						

			String[][] data = new String[currenRow][productController.column.length];
			rs.beforeFirst();
			int index = 0;
			while(rs.next()) {// ��ó������ �ʱ�ȭ �� �����̹Ƿ� ������ ������Ű��
				String[] record = new String[6];
				record[0] = rs.getString("product_id");
				record[1] = rs.getString("subcategory_id");
				record[2] = rs.getString("product_name");
				record[3] = rs.getString("brand");
				record[4] = rs.getString("price");
				record[5] = rs.getString("filename");
								
				data[index++] = record;
				System.out.println(index);
			}
			
			productController.data=data;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	// 14) ��ǰ �󼼺���(����)
	public void getDetail(int row) {		
		t_name2.setText((String)table.getValueAt(row, 2));// ��ǰ��
		t_brand2.setText((String)table.getValueAt(row, 3));// �귣��
		t_price2.setText((String)table.getValueAt(row, 4));// ��ǰ����
		// �̹���â
		
	}
	// 15) ������ ��ǰ�� �˸´� ī�װ��� ���õǾ� �ְ�..
	public void setCategory(int row) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String subcategory_id = (String)table.getValueAt(row, 1);
		
		String sql="select * from topcategory where topcategory_id=(";
		sql+="select topcategory_id  from subcategory where subcategory_id ="+subcategory_id;		
		sql+=")";
		System.out.println(sql);
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// select �޼���� ���õ� �������� ������ �� �ִ�.
				ch_top2.select(rs.getString("name"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	// 15) ������ ��ǰ�� �˸´� ī�װ��� ���õǾ� �ְ�..
	public void setSubCategory(int row) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String subcategory_id = (String)table.getValueAt(row, 1);
		
		String sql="select * from subcategory where subcategory_id="+subcategory_id;
		System.out.println(sql);
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// select �޼���� ���õ� �������� ������ �� �ִ�.
				ch_sub2.select(rs.getString("name"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	// 2) ��������
	public void disconnect() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new ShoppingApp();
	}
}