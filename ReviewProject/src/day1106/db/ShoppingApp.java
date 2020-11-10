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

public class ShoppingApp extends JFrame{
	JPanel p_west;
	JPanel p_center;
	JPanel p_east;
	
	JPanel c_north;
	JPanel c_center;
	
	// ����� ����
	Choice ch_top;
	Choice ch_sub;
	JTextField t_name;
	JTextField t_brand;
	JTextField t_price;
	JButton bt_find;	// �̹��� ã�ƺ���
	JPanel can;	// �̹��� ���̴� ��
	JButton bt_regist;
		
	Choice ch_category;
	JTextField t_keyword;
	JButton bt_search;
	JTable table;
	JScrollPane scroll;
	
	// ����
	Choice ch_top2;
	Choice ch_sub2;
	JTextField t_name2;
	JTextField t_brand2;
	JTextField t_price2;
	JButton bt_find2;	// �̹��� ã�ƺ���
	JPanel can2;	// �̹��� �̸����� �׷��� ��
	JButton bt_edit;	// ������ư
	JButton bt_del;	// ������ư
	
	// ����
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";
	Connection con;
	
	HashMap<String, Integer> map = new HashMap<>();
	HashMap<String, Integer> map2 = new HashMap<>();
	
	JFileChooser chooser = new JFileChooser("D:/workspace/java_workspace/SeProject/res/travel2");
	Toolkit kit = Toolkit.getDefaultToolkit();	
	Image img;
	File file;
	ProductController productController;
	public ShoppingApp() {
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		bt_find = new JButton("�̹��� ã��");
		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, this);
			}
		};
		bt_regist = new JButton("���");
		
		ch_top.add("choose category");
		
		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(t_name);
		p_west.add(t_brand);
		p_west.add(t_price);
		p_west.add(bt_find);
		p_west.add(can);
		p_west.add(bt_regist);
		
		p_west.setPreferredSize(new Dimension(150,600));
		add(p_west,BorderLayout.WEST);
		
		ch_top.setPreferredSize(new Dimension(135,40));
		ch_sub.setPreferredSize(new Dimension(135,40));
		
		t_name.setPreferredSize(new Dimension(135,30));
		t_brand.setPreferredSize(new Dimension(135,30));
		t_price.setPreferredSize(new Dimension(135,30));
		
		bt_find.setPreferredSize(new Dimension(135,30));
		can.setPreferredSize(new Dimension(135,115));
		
		c_north = new JPanel();
		c_center = new JPanel();
		ch_category = new Choice();
		t_keyword = new JTextField();
		bt_search = new JButton("�˻�");
		table = new JTable(ProductController = new ProductController());
		scroll = new JScrollPane(table);
		
		c_north.add(ch_category);
		c_north.add(t_keyword);
		c_north.add(bt_search);
		
		c_center.setLayout(new BorderLayout());
		c_center.add(scroll);
		
		c_north.setBackground(Color.PINK);
		ch_category.setPreferredSize(new Dimension(130,30));
		t_keyword.setPreferredSize(new Dimension(400,30));
		bt_search.setPreferredSize(new Dimension(120,30));
		
		p_center = new JPanel();
		p_center.setLayout(new BorderLayout());
		p_center.add(c_north,BorderLayout.NORTH);
		p_center.add(c_center);
		
		add(p_center);
		
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
		
		p_east.add(ch_top2);
		p_east.add(ch_sub2);
		p_east.add(t_name2);
		p_east.add(t_brand2);
		p_east.add(t_price2);
		p_east.add(bt_find2);
		p_east.add(can2);
		p_east.add(bt_edit);
		p_east.add(bt_del);
		
		p_east.setPreferredSize(new Dimension(150,600));
		ch_top2.setPreferredSize(new Dimension(135,40));
		ch_sub2.setPreferredSize(new Dimension(135,40));
		
		t_name2.setPreferredSize(new Dimension(135,30));
		t_brand2.setPreferredSize(new Dimension(135,30));
		t_price2.setPreferredSize(new Dimension(135,30));
		
		can2.setPreferredSize(new Dimension(135,115));
		bt_find2.setPreferredSize(new Dimension(135,30));
		bt_edit.setPreferredSize(new Dimension(135,30));
		bt_del.setPreferredSize(new Dimension(135,30));
		
		add(p_east,BorderLayout.EAST);
		
		// ȣ��
		connect();
		getTopList();
		
		// 2) ������â ������ ����Ŭ���� ���� ����, ���μ����� ����
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});
		
		// 4) ch_top ������
		ch_top.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(ch_top.getSelectedIndex()>0) {
					int topcategory_id = map.get(ch_top.getSelectedItem());
					getSubList(topcategory_id);
				}			
			}
		});
		
		// 6) ����ã�� ��ư�� ������ ����
		bt_find.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				
				findImage();
				preview();
			}
		});
		
		// 7) ��Ϲ�ư
		bt_regist.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				regist();
				getProductList();
				table.updateUI();
			}
		});
		setSize(1000,600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	//1) ����Ŭ ����
	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
			
			if(con==null) {
				JOptionPane.showMessageDialog(this, "���ӽ���");
				System.exit(0);
			}else {
				this.setTitle(user+"�� ������");
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "����̹��� ã�� �� �����ϴ�.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3) ����ī�װ�
	public void getTopList() {
		String sql = "select * from topcategory";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ch_top.add(rs.getString("name"));
			
			map.put(rs.getString("name"), rs.getInt("topcategory_id"));
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
	
	// 5) ���� ī�װ� ��������
	public void getSubList(int topcategory_id) {
		String sql = "select * from subcategory where topcategory_id="+topcategory_id;
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// ī�װ� �ʱ�ȭ
			ch_sub.removeAll();
			ch_sub.add("choose category");
			
			while(rs.next()) {
				ch_sub.add(rs.getString("name"));
				map2.put(rs.getString("name"), rs.getInt("subcategory_id"));
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
	// 6) ��ǰ�̹��� �� �̸����� ����
	public void findImage() {
		if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			getTargetImage(file.getAbsolutePath());
		}
	}
	public void getTargetImage(String path) {
		img = kit.getImage(path);
		img = ImageUtil.getCustomSize(img, 135, 115);
	}
	public void preview() {
		can.repaint();
	}
	
	// 7) ��Ϲ�ư
	public void regist() {
		int subcategory_id = map2.get(ch_sub.getSelectedItem());
		String product_name = t_name.getText();
		String brand = t_brand.getText();
		int price = Integer.parseInt(t_price.getText());
		String filename = file.getName();	// �̹�����..
		
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
			
			int result = pstmt.executeUpdate();
			if(result ==0) {
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
	
	// 9) product ���̺� ���ڵ� ��������
	public void getProductList() {
		String sql = "select * from product";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			
			rs.last();
			int currenRow = rs.getRow();
			
			// ������ -> ������
			String[][] data = new String[currenRow][productController.column.length];
			rs.beforeFirst();
			int index = 0;
			while(rs.next()) {
				String[] record = new String[6];
				record[0] = rs.getString("product_id");
				record[1] = rs.getString("subcategory_id");
				record[2] = rs.getString("product_name");
				record[3] = rs.getString("brand");
				record[4] = rs.getString("price");
				record[5] = rs.getString("filename");
				data[index++]=record;
				productController.data = data;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}if(rs !=null) {
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
