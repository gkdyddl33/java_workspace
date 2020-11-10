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

/*쇼핑몰 상품관리 프로그램 제작하기*/
public class ShoppingApp extends JFrame {	
	JPanel p_west;	// 전체 중 서쪽
	JPanel p_center;	// 전체 중 중간
	JPanel p_east;	// 전체 중 동쪽
	
	JPanel c_north;	// 가운데 중 북쪽
	JPanel c_center;	// 가운데 중 중간 - 테이블 나올 영역
	
	// 등록폼 관련(서쪽)
	Choice ch_top;
	Choice ch_sub;
	JTextField t_name;
	JTextField t_brand;
	JTextField t_price;
	JButton bt_find;	// 이미지 찾아보기
	JButton bt_collect; // 11) 이미지 수집하기(나의 하드디스크로..), 이미지 자체 긁어오기
	JPanel can;	// 이미지 미리보기 그려질 곳
	JButton bt_regist;
	CollectorFrame collectorFrame; // 11) 버튼 리스너 연결을 위해
	
	// 검색관련(센터)	
	Choice ch_category;	// 검색 카테고리	
	JTextField t_keyword;	// 검색어
	JButton bt_search;	// 검색버튼
	JTable table;
	JScrollPane scroll;
	
	//  (동쪽)
	Choice ch_top2;
	Choice ch_sub2;
	JTextField t_name2;
	JTextField t_brand2;
	JTextField t_price2;
	JButton bt_find2;	// 이미지 찾아보기
	JPanel can2;	// 이미지 미리보기 그려질 곳
	JButton bt_edit;	// 수정버튼
	JButton bt_del;	// 삭제버튼
	
	// 1) 접속
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";
	
	Connection con;	// 접속이 완료됬는지 확인할 수있는 인터페이스
	HashMap<String,Integer> map = new HashMap<>();	// 컬렉션 프레임웍 중, key - value 의 쌍으로 객체를 관리해주는 객체
	HashMap<String,Integer> map2 = new HashMap<>();	// 컬렉션 프레임웍 중, key - value 의 쌍으로 객체를 관리해주는 객체
	
	JFileChooser chooser = new JFileChooser("D:/workspace/java_workspace/SeProject/res/travel2");	// 6)
	Toolkit kit= Toolkit.getDefaultToolkit();	// 플랫폼 종속적인 경로로 가져올 때는 툴킷 쓰자.
	Image img;	
	File file;
	ProductController productController;
	

	public ShoppingApp() {
		// 생성(서쪽)
		p_west = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		bt_find = new JButton("이미지 찾기");
		bt_collect = new JButton("인터넷 수집");	// 11) 생성
		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can);
			}
		};
		bt_regist = new JButton("등록");
		
		// 조립(서쪽)
		ch_top.add("choose category"); // 상위카테고리 첫 라인
		
		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(t_name);
		p_west.add(t_brand);
		p_west.add(t_price);
		p_west.add(bt_find);
		p_west.add(bt_collect);	// 11) 조립
		p_west.add(can);
		p_west.add(bt_regist);
		
		// 스타일
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
		
		// 프레임에 서쪽영역 붙이기		
		add(p_west,BorderLayout.WEST);
		
		// 생성(센터)
		c_north = new JPanel();
		c_center = new JPanel();
		ch_category = new Choice();
		t_keyword = new JTextField();
		bt_search = new JButton("검색");
		// 8) jtable을 그려줌..행과열의 표가 생긴다.
		table = new JTable(productController = new ProductController());
		scroll = new JScrollPane(table);
		
		// 조립(센터)
		c_north.add(ch_category);
		c_north.add(t_keyword);
		c_north.add(bt_search);
		
		c_center.setLayout(new BorderLayout());
		c_center.add(scroll);
		
		// 13) 검색기능출력 구현
		ch_category.add("product_name");
		ch_category.add("brand");
		
		// 스타일
		c_north.setBackground(Color.PINK);
		ch_category.setPreferredSize(new Dimension(130,30));
		t_keyword.setPreferredSize(new Dimension(400,30));
		bt_search.setPreferredSize(new Dimension(120,30));
				
		// 프레임에 센터영역 붙이기 - 두 패널 부착
		p_center = new JPanel();
		p_center.setLayout(new BorderLayout());
		p_center.add(c_north,BorderLayout.NORTH);
		p_center.add(c_center);
		
		add(p_center);
		
		// 생성(동쪽)
		p_east = new JPanel();
		ch_top2 = new Choice();
		ch_sub2 = new Choice();
		t_name2 = new JTextField();
		t_brand2 = new JTextField();
		t_price2 = new JTextField();
		bt_find2 = new JButton("이미지 찾기");
		can2 = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, can2);
			}
		};
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		
		// 조립(동쪽)
		p_east.add(ch_top2);
		p_east.add(ch_sub2);
		p_east.add(t_name2);
		p_east.add(t_brand2);
		p_east.add(t_price2);
		p_east.add(bt_find2);
		p_east.add(can2);
		p_east.add(bt_edit);
		p_east.add(bt_del);
		
		// 스타일
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
		
		// 프레임에 동쪽영역 붙이기		
		add(p_east,BorderLayout.EAST);
		
		// (*) 호출
		connect();
		getTopList();
		getProductList(); // 상품 정보 출력
		
		// 2) 윈도우 창 닫으면, 오라클과의 접속 끊고, 프로세스도 종료
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// 접속해제 + 프로세스 종료
				disconnect();
				System.exit(0);
			}
		});
		
		// 4) ch_top에 리스너 연결 왜? 하위 카테고리 보이게 하기 위해
		ch_top.addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// 바뀐 정보를 이용하여 하위 카테고리를 가져오자.
				// hashMap으로 부터 key값을 이용하여 values값 구하기
				
				// 제일 위의 아이템은 제외시켜야 한다.. 그냥 보여주기이므로
				//System.out.println("선택한 아이템의 index는 "+ch_top.getSelectedIndex());
				if(ch_top.getSelectedIndex()>0) {
					System.out.println("내가 선택한 Key는 "+ch_top.getSelectedItem());
					
					int topcategory_id = map.get(ch_top.getSelectedItem());
					System.out.println("맵으로부터 추출된 value는 "+topcategory_id);
					
//					String sql = "SELECT * FROM SUBCATEGORY WHERE TOPCATEGORY_ID ="+topcategory_id;
//					System.out.println(sql);
					getSubList(topcategory_id);
				}
			}
		});
		
		// 6) 파일찾기 버튼과 리스너 연결 = 이미지 찾기
		bt_find.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				findImage();	// 쇼핑몰에 사용할 상품 이미지 선택				
				preview();
			}
		});
		
		// 7) 등록버튼과 리스너 연결
		bt_regist.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				regist();
				// 10) UI갱신 --> product 리스트에 작성하고 난 후 바로 갱신시키기
				//						  새로고침 효과
				// 등록된 목록 가져오기 -> 갱신
				getProductList();
				table.updateUI();
			}
		});
		
		// 11) 이미지 인터넷으로 수집하기 버튼과 연결
		bt_collect.addActionListener(new ActionListener() {	
			// 또다른 창이 뜬다..Field에 URL을 넣을 것이다 
			@Override
			public void actionPerformed(ActionEvent e) {
				// 내부 익명에서 외부클래스 인스턴스를 이렇게 사용하자. 매개변수 받기
				collectorFrame = new CollectorFrame(ShoppingApp.this);
			}
		});
		
		// 13) 검색기능 구현(상위센터)
		bt_search.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String category = ch_category.getSelectedItem();//선택한 검색 분류
				String keyword = t_keyword.getText(); //입력한 키워드
						
				getSearchResult(category , keyword);
				table.updateUI();//테이블 갱신
			}
		});
		
		// 14) 마우스 리스너 연결 -> (우측) 상세보기 구현
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();	// 선택한 행
				int col = table.getSelectedColumn();	// 선택한 열
						
				String value = (String)table.getValueAt(row, col);
				System.out.println(value);
				
				
				// 15) 선택한 제품의 알맞는 카테고리 선택되어 있게..
				// topcategory_id = subcategory_id
				setCategory(row);
				setSubCategory(row);
				getDetail(row); 	// 상세보기 출력
				
				String filename = (String)table.getValueAt(row, 5);
				getTargetImage("D:/workspace/java_workspace/SeProject/res/travel2/"+filename);// 상세정보 이미지 그리기
				can2.repaint();
				
				
			}
		});
		
		// 윈도우
		setSize(1000,600);
		setLocationRelativeTo(null);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 1) 오라클 접속
	public void connect() {
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 접속
			con = DriverManager.getConnection(url,user,password);
			if(con == null) {
				JOptionPane.showMessageDialog(this, "접속실패");
				System.exit(0);
			}else {
				// 윈도우 창에 유저명으로 접속했다는 메시지
				this.setTitle(user+"로 접속 중");
			}
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3) 상위카테고리 가져오기
	public void getTopList() {
		String sql = "select * from topcategory";
		
		// 쿼리문 수행 객체 = PreparedStatement
		// 쿼리문 결과 집합을 담는 객체 = ResultSet
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql); // 쿼리문 준비
			rs = pstmt.executeQuery();	// 쿼리실행
			
			while(rs.next()) {// 1칸 전진			
				// 사용자들이 보게 될 아이템!
				ch_top.add(rs.getString("name"));	
				ch_top2.add(rs.getString("name"));	// 14) 상세보기 카테고리
				
				// 4) hashMap - key,value 쌍으로 정보넣기
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
	// 5) 하위 카테고리 가져오기
	public void getSubList(int topcategory_id) {
		String sql = "SELECT * FROM SUBCATEGORY WHERE TOPCATEGORY_ID ="+topcategory_id;
		System.out.println(sql);
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			// 서브카테고리 채우기
			// 그냥 채우면 누적으로 쌓임.. 채우기전에 모두 지우기(초기화)			
			ch_sub.removeAll(); // 모두 지우기!
			ch_sub2.removeAll();
			
			ch_sub.add("choose category");
			ch_sub2.add("choose category");
			while(rs.next()) {
				ch_sub.add(rs.getString("name"));
				ch_sub2.add(rs.getString("name"));	// 14) 상세보기 카테고리
				// 7) 입력한 값 담기
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
	// 6) 상품이미지 선택
	public void findImage() {
		if(chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
			// 확인 눌럿다면? 파일정보를 구한다.
			file = chooser.getSelectedFile();
			System.out.println("당신이 지금 선택한 파일의 정보:"+file.getAbsolutePath());
		
			// 멤버변수 img 값을 구한다.
			//img = kit.getImage(file.getAbsolutePath());
			//img = ImageUtil.getCustomSize(img, 135, 115);	// img를 가져오고 img의 크기를 조정해서 다시 넣음
			// ------------> 메서드로 만듬..12) 로 보충
			getTargetImage(file.getAbsolutePath());
		}
	}
	// 6 - 12) 그려질 이미지 구하기
	public void getTargetImage(String path) {
		img = kit.getImage(path);
		img = ImageUtil.getCustomSize(img, 135, 115);	
		
	}
	
	// 6) 미리보기 구현
	public void preview() {
		// paint로 그림처리 can
		can.repaint();
	}
	// 7) 등록버튼
	public void regist() {

		// 물음표 값 결정짓기 = hashMap
		int subcategory_id = map2.get(ch_sub.getSelectedItem());
		String product_name = t_name.getText();
		String brand = t_brand.getText();
		int price = Integer.parseInt(t_price.getText());
		String filename = file.getName();	//풀경로 말고 '이미지명' 만..
		
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
			
			// 아래의 메서드의 반환값? 이 쿼리문에 의해 영향받는 레코드 수를 반환,
			// 따라서 insert 경우엔 성공인 언제나 1
			// update, delete는 실패일 경우 0, 성공이면 1이상임..
			int result = pstmt.executeUpdate();
			if(result==0){
				JOptionPane.showMessageDialog(this, "등록실패");
			}else {
				JOptionPane.showMessageDialog(this, "등록성공");
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
	
	// 9) product 테이블의 레코드 가져오기
	public void getProductList() {
		String sql = "select * from product";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// ** PrepareStatement 생성시 인수 2개를 넘겨, 전후방향으로 자유롭게 이동가능하게 할 수 있다.
			//		rs.beforeFirst();
			// 		스크롤(커서) 움직이게 하고, 읽기전용 인수를 넣는다 = 자유로운 rs가 만들어진다.= 맨 첨으로 초기화 됨
			pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			rs = pstmt.executeQuery();
		
			rs.last();	// 커서를 마지막으로 보내기
			int currenRow = rs.getRow();
//			System.out.println("현재 커서가 가리키는 레코드 번호는 " +currenRow);
			System.out.println("마지막에 도달한 커서의 rowNum " +currenRow);
						
			// [총 레코드수][컬럼]
			String[][] data = new String[currenRow][productController.column.length];

			// 이차원 배열에 데이터를 담으려면, 커서를 다시 원상 복귀시켜야 한다.
			// 커서를 자유자재로 움직일 수 있는 ResultSet을 만들어야 된다.
			// 다시 처음의 행으로 커서를 움직여라.= first()
			// 0번째 위치로 되돌림(즉, 위치 초기화)
			rs.beforeFirst();
			int index = 0;
			while(rs.next()) {// 맨처음으로 초기화 된 상태이므로 앞으로 전진시키기
				// (1차원배열) 각 속성에 값을 넣고..2차원 배열로 행을 넣을 것임
				String[] record = new String[6];
				record[0] = rs.getString("product_id");
				record[1] = rs.getString("subcategory_id");
				record[2] = rs.getString("product_name");
				record[3] = rs.getString("brand");
				record[4] = rs.getString("price");
				record[5] = rs.getString("filename");
				
				// (2차원배열) 위 일차원 배열을 담을 이차원 배열을 만들어서 넣자.
				data[index++] = record;
				System.out.println(index);
			}
			// ----------> 완성된 이차원 배열을 productController가 보유한 data 배열 주소로 대입시키자.
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
	// 13) 검색결과 출력하기 = getProductList() 와 쿼리문 빼고 다 똑같음 
	public void getSearchResult(String category,String keyword) {
//		String sql = "select * from product where 브랜드, 상품명 like '%검색어%'";
		String sql="select * from product where "+category+" like '%"+keyword+"%'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try { 
			pstmt=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			rs = pstmt.executeQuery();
		
			rs.last();	// 커서를 마지막으로 보내기
			int currenRow = rs.getRow();
//			System.out.println("현재 커서가 가리키는 레코드 번호는 " +currenRow);
			System.out.println("마지막에 도달한 커서의 rowNum " +currenRow);
						

			String[][] data = new String[currenRow][productController.column.length];
			rs.beforeFirst();
			int index = 0;
			while(rs.next()) {// 맨처음으로 초기화 된 상태이므로 앞으로 전진시키기
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
	
	// 14) 제품 상세보기(우측)
	public void getDetail(int row) {		
		t_name2.setText((String)table.getValueAt(row, 2));// 상품명
		t_brand2.setText((String)table.getValueAt(row, 3));// 브랜드
		t_price2.setText((String)table.getValueAt(row, 4));// 상품가격
		// 이미지창
		
	}
	// 15) 선택한 제품의 알맞는 카테고리 선택되어 있게..
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
				// select 메서드는 선택될 아이템을 지정할 수 있다.
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
	// 15) 선택한 제품의 알맞는 카테고리 선택되어 있게..
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
				// select 메서드는 선택될 아이템을 지정할 수 있다.
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
	// 2) 접속해제
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
