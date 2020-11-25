package dynamic_beat_10;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame{
	
	// 전체화면을 담는 인스턴스 - "시작화면" 이미지를 불러와보기!!
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png"))
			.getImage();
	private Image judgmentLineImage = new ImageIcon(Main.class.getResource("../images/judgmentLine.png"))
			.getImage();
	private Image noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png"))
			.getImage();
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png"))
			.getImage();
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png"))
			.getImage();
	
	// 게임시작 이미지 와 메인메뉴 이미지	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).png")).getImage();
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
		
	// 버튼들 집합!
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	
	// -- 시작종료버튼
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));	
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	
	// -- 좌우버튼
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));	
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
		
	// -- easy,hard버튼
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));

	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);	
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	// 메뉴바 변경
	private int mouseX,mouseY;	
		
	private boolean isMainScreen = false;	// 메인 메뉴와 시작화면 화면전환 비교값
																// 처음에는 시작화면!!
	private boolean isGameScreen = false;	// 게임화면으로 넘어왔나?
	
	ArrayList<Track> trackList = new ArrayList<>();
	private Music selectedMusic;
	// 게임시작 이미지 와 메인메뉴 이미지
	private Image selectedImage;
	// 곡 Title 이미지
	private Image titleImage;
	// 시작하자마자 음악이 나오게 하기 - 그리고 '시작하기' 버튼을 누르면은 음악이 안들리도록 하기
	private Music introMusic = new Music("Dizzy.mp3", true);
	private int nowSelected =0;	// 첫번째 곡을 의미!
	
	public DynamicBeat() {
		// 메뉴바가 보이지 않게 하기 위해
		setUndecorated(true);
		
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		introMusic.start(); // 로드 되는 순간 음악나오게.
		
		// 곡 정보를 담자..
		trackList.add(new Track("Mighty Love Title Image.png","Mighty Love Start Image.jpg",
				"Mighty Love Game Image.jpg","Mighty Love Selected.mp3","Joakim Karud - Mighty Love.mp3"));
		
		trackList.add(new Track("Wild Flower Title Image.png","Wild Flower Start Image.jpg",
				"Wild Flower Game Image.jpg","Wild Flower Selected.mp3","Joakim Karud - Wild Flower.mp3"));
		
		trackList.add(new Track("Energy Title Image.png","Energy Start Image.jpg",
				"Energy Game Image.jpg","Energy Selected.mp3","Bensound - Energy.mp3"));
		
		
		// -----> 버튼 아이콘 사이즈 조절해보기
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);		
		exitButton.setFocusPainted(false);	
		
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스 올리기
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스 효과음
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// 마우스 뗄때
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// 마우스 누를 때
				// 마우스 효과음
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				try {
					// 소리가 나오고 1초뒤에 종료
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});		
		add(exitButton);		
		
		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);		
		startButton.setFocusPainted(false);	
		
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스 올리기
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스 효과음
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// 마우스 뗄때
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// 마우스 누를 때
				// 마우스 효과음
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				
				introMusic.close();
//				Music selectedMusic = new Music("Mighty Love Selected.mp3", true);	// true로 곡이 무한반복!
//				selectedMusic.start();
				enterMain();		
			}
		});		
		add(startButton);
		
		quitButton.setBounds(40, 330, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);		
		quitButton.setFocusPainted(false);	
		
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스 올리기
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스 효과음
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// 마우스 뗄때
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// 마우스 누를 때
				// 마우스 효과음
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				try {
					// 소리가 나오고 1초뒤에 종료
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});		
		add(quitButton);
		
		// 첨에는 안보이다가 시작하기 하면 보이게
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);		
		leftButton.setFocusPainted(false);	
		
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스 올리기
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스 효과음
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// 마우스 뗄때
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// 마우스 누를 때
				// 마우스 효과음
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				// 왼쪽 버튼 이벤트
				selectLeft();
			}
		});		
		add(leftButton);
		
		// 첨에는 안보이다가 시작하기 하면 보이게
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);		
		rightButton.setFocusPainted(false);	
		
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스 올리기
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스 효과음
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// 마우스 뗄때
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// 마우스 누를 때
				// 마우스 효과음
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				// 오른쪽 버튼 이벤트
				selectRight();
			}
		});		
		add(rightButton);
		
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);		
		easyButton.setFocusPainted(false);	
		
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스 올리기
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스 효과음
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// 마우스 뗄때
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// 마우스 누를 때
				// 마우스 효과음
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				// 난이도 쉬운 이벤트
				gameStart(nowSelected,"easy");
			}
		});		
		add(easyButton);
		
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);		
		hardButton.setFocusPainted(false);	
		
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스 올리기
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스 효과음
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// 마우스 뗄때
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// 마우스 누를 때
				// 마우스 효과음
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				// 난이도 쉬운 이벤트	
				gameStart(nowSelected,"hard");
			}
		});		
		add(hardButton);
		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);		
		backButton.setFocusPainted(false);	
		
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// 마우스 올리기
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 마우스 효과음
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// 마우스 뗄때
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// 마우스 누를 때
				// 마우스 효과음
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				// 메인화면으로 돌아가는 이벤트	
				backMain();
			}
		});		
		add(backButton);
		
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		// 메뉴바를 잡고 움직일 수 있게 해준다!
		menuBar.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y-mouseY); 
			}
		});
		add(menuBar);
			
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic); // 글자깨짐보완
		g.drawImage(screenImage, 0, 0, null);
	}

	private void screenDraw(Graphics2D g) {
		g.drawImage(background,0,0,null);
		if(isMainScreen) {// 메인화면일떄? 보여줘..
			g.drawImage(selectedImage,340,100,null);
			// 곡 제목 이미지 
			g.drawImage(titleImage,340,70,null);			
		}
		if(isGameScreen) {// 게임의 정보가 담기는 info 디자인이 들어간다....
			g.drawImage(noteRouteImage,228,30,null);
			g.drawImage(noteRouteImage,332,30,null);
			g.drawImage(noteRouteImage,436,30,null);
			g.drawImage(noteRouteImage,540,30,null);
			g.drawImage(noteRouteImage,640,30,null);
			g.drawImage(noteRouteImage,744,30,null);
			g.drawImage(noteRouteImage,848,30,null);
			g.drawImage(noteRouteImage,952,30,null);
			g.drawImage(noteRouteLineImage,224,30,null);
			g.drawImage(noteRouteLineImage,328,30,null);
			g.drawImage(noteRouteLineImage,432,30,null);
			g.drawImage(noteRouteLineImage,536,30,null);
			g.drawImage(noteRouteLineImage,740,30,null);
			g.drawImage(noteRouteLineImage,844,30,null);
			g.drawImage(noteRouteLineImage,948,30,null);
			g.drawImage(noteRouteLineImage,1052,30,null);
			g.drawImage(gameInfoImage,0,660,null);
			g.drawImage(judgmentLineImage,0,580,null);
			g.drawImage(noteBasicImage,228,120,null); // 경로위에 노트가 올라가 있음
			g.drawImage(noteBasicImage,332,580,null);
			g.drawImage(noteBasicImage,436,500,null);
			g.drawImage(noteBasicImage,540,340,null);
			g.drawImage(noteBasicImage,640,340,null);
			g.drawImage(noteBasicImage,744,325,null);
			g.drawImage(noteBasicImage,848,305,null);
			g.drawImage(noteBasicImage,952,305,null);
			// 게임화면 하단에 노래 제목을 넣기..
			g.setColor(Color.WHITE);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.drawString("Joakim Karud - Mighty Love",20,702);
			g.drawString("Easy",1190,702);
			g.setFont(new Font("Arial",Font.PLAIN,26));
			g.setColor(Color.DARK_GRAY);
			g.drawString("S",270,609);
			g.drawString("D",374,609);
			g.drawString("F",478,609);
			g.drawString("Space Bar",580,609);
			g.drawString("J",784,609);
			g.drawString("K",889,609);
			g.drawString("L",993,609);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("Elephant",Font.BOLD,30));
			g.drawString("000000",565,702);
		}
		paintComponents(g);	// 이미지를 스크린 이미지로 그려주는 것!
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) {// 해당 곡이 선택이 되었다면?
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		// Track -> VO 에 titleImage의 값을 가져와서 바꿔준다.
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(),true);
		selectedMusic.start();
	}
	// 좌우 버튼 이벤트 주기
	public void selectLeft() {
		if(nowSelected == 0) {
			nowSelected = trackList.size()-1;
		}else {
			nowSelected--;
		}
		selectTrack(nowSelected);
	}
	public void selectRight() {
		if(nowSelected == trackList.size()-1) {
			nowSelected = 0;
		}else {
			nowSelected++;
		}
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected,String difficulty) {
		if(selectedMusic != null) {
			selectedMusic.close();	// 현재 재생중 음악 닫기
		}
		isMainScreen = false;	// 메인 화면이 아니다.
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		isGameScreen = true; // 게임시작
	}
	
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
				.getImage();
		backButton.setVisible(false);	// 뒤로가기 버튼은 이제 안보이게..
		selectTrack(nowSelected);	// 처음 시작화면 보여줘라.
		isGameScreen = false;
	}
	public void enterMain() {
		
		// * 게임시작 이벤트 -> 화면전환
		startButton.setVisible(false);
		quitButton.setVisible(false);
		
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		
		leftButton.setVisible(true); // 화면 보이기!
		rightButton.setVisible(true);
		
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);	// 메인화면으로 가자!!!
	}
}
