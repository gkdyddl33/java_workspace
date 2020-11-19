
/*��ǰ��� ���� �����Ѵ�.(����ϱ� ��ư ����)*/
package com.swingmall.admin.product;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.swingmall.admin.AdminMain;

public class RegistForm extends JPanel {
	Product product;

	JPanel p_container; // �׸��� ���� ����(7,2)
	String[] title = { "����ī�װ�", "����ī�װ�", "��ǰ��", "�귣��", "����", "�̹���", "�󼼼���" };
	JLabel[] la_title = new JLabel[title.length];

	Choice ch_top; // �ֻ���,���� ī�װ�
	Choice ch_sub;
	// ��ǰ��, �귣��, ����, �̹���, �󼼼���
	JTextField t_product_name;
	JTextField t_brand;
	JTextField t_price;
	JTextField t_filename;
	JTextField t_detail;
	JScrollPane s1; // �󼼼��� ��ũ��
	JButton bt_regist;
	JButton bt_list;
	
	public RegistForm(Product product) {
		this.product = product;
		
		// ����
		p_container = new JPanel();
		for (int i = 0; i < la_title.length; i++) {
			la_title[i] = new JLabel(title[i]);
		}

		ch_top = new Choice();
		ch_sub = new Choice();
		t_product_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		t_filename = new JTextField();
		t_detail = new JTextField();
		s1 = new JScrollPane(t_detail);
		bt_regist = new JButton("���");
		bt_list = new JButton("���");
		
		// 6) �ֻ��� ī�װ� ä���(DB���� X, ���� ������ ��������)
		for(String name : product.topList) {
			ch_top.add(name);
		}
		
		// ��Ÿ��
		Dimension d = new Dimension(320, 25);

		p_container.setBackground(Color.WHITE);
		p_container.setPreferredSize(new Dimension(AdminMain.WIDTH - 500, AdminMain.HEIGHT - 400));
		for (int i = 0; i < title.length; i++) {
			la_title[i].setPreferredSize(d);
		}
		ch_top.setPreferredSize(d);
		ch_sub.setPreferredSize(d);
		t_product_name.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_filename.setPreferredSize(d);
		t_detail.setPreferredSize(new Dimension(320, 300));
		bt_regist.setPreferredSize(new Dimension(300, 40));
		bt_list.setPreferredSize(new Dimension(300, 40));

		// ����

		p_container.add(la_title[0]);
		p_container.add(ch_top);
		p_container.add(la_title[1]);
		p_container.add(ch_sub);
		p_container.add(la_title[2]);
		p_container.add(t_product_name);
		p_container.add(la_title[3]);
		p_container.add(t_brand);
		p_container.add(la_title[4]);
		p_container.add(t_price);
		p_container.add(la_title[5]);
		p_container.add(t_filename);
		p_container.add(la_title[6]);
		p_container.add(t_detail);
		p_container.add(s1);

		this.add(p_container);
		this.add(bt_regist);
		this.add(bt_list);

		// 5) ȭ����ȯ(������) + 7) ��Ϲ�ư ���� �߰�
		bt_regist.addActionListener((e)->{
			if(regist()==0) {
				JOptionPane.showMessageDialog(RegistForm.this, "��Ͻ���");
			}else {
				JOptionPane.showMessageDialog(RegistForm.this, "��ϼ���");
				product.getProductList(null);
			}
		});
		
		bt_list.addActionListener((e)->{// ������� ���ư���
			product.addRemoveContent(this, product.p_center); // ���� ����, �ٽ� ���ƿ���
		});
		
		ch_top.addItemListener((e)->{// 6) ����ī�װ� ��������
			//System.out.println("�� �مf��?");			
			// ������ ��ȣ�� �˸�? ���°�� �������� �� ���� �մ�.			
			getSubCategory(ch_top.getSelectedIndex());
		});
	}
	// 6) ���� ������ ������ �ֻ��� ī�װ��� �Ҽӵ� ����ī�װ��� ��������!
	public void getSubCategory(int index) {
		ArrayList<String>  list = product.subList.get(index);
		ch_sub.removeAll(); // ä���ֱ� ����, ���� �����۵��� ����..
		for(String item: list) {
			ch_sub.add(item);
		}
	}
	// 7) ������ ������ ���������� ���� pk�� �������� - subcategory
	public int getSubId(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int subcategory_id=0;
		String sql = "select * from subcategory where name=?";
		try {
			pstmt = product.getAdminMain().getCon().prepareStatement(sql);
			pstmt.setString(1, name); // �Ű����� �μ��� ���޵� �������� ���ε� ������ ����
			rs=pstmt.executeQuery();
			if(rs.next()) {
				subcategory_id = rs.getInt("subcategory_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			product.getAdminMain().getDbManager().close(pstmt, rs);
		}
		
		return subcategory_id;
	}
	
	public int regist() {// 7) ���� ���и� �� �� �ִ� ��ȯ�� = int
		PreparedStatement pstmt = null;
		int result = 0;	// DML ������ �����ߴ��� ���θ� �� �� �ִ� ����
		
		String sql = "insert into product(product_id,subcategory_id,product_name";
		sql += ",brand,price,filename,detail) values(seq_product.nextval,?,?,?,?,?,?)";
		
		try {
			pstmt=product.getAdminMain().getCon().prepareStatement(sql);
			// ������ �������� pk�� ���Ͽ� ���ε� ������ ����
			pstmt.setInt(1, getSubId(ch_sub.getSelectedItem()));
			pstmt.setString(2, t_product_name.getText());
			pstmt.setString(3, t_brand.getText());
			pstmt.setInt(4, Integer.parseInt(t_price.getText()));
			pstmt.setString(5, t_filename.getText()); // ���� url
			pstmt.setString(6, t_detail.getText());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			product.getAdminMain().getDbManager().close(pstmt, null);
		}
		
		return result;
	}
}
