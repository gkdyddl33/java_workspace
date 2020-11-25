package dynamic_beat_8;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
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
	
	private JButton exitButton = new JButton(exitButtonBasicImage);	
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	
	// 메뉴바 변경
	private int mouseX,mouseY;	
		
	private boolean isMainScreen = false;	// 메인 메뉴와 시작화면 화면전환 비교값
																// 처음에는 시작화면!!
	
	ArrayList<Track> trackList = new ArrayList<>();
	private Music selectedMusic;
	// 게임시작 이미지 와 메인메뉴 이미지
	private Image selectedImage;
	// 곡 Title 이미지
	private Image titleImage;
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
		
		// 시작하자마자 음악이 나오게 하기 - 그리고 '시작하기' 버튼을 누르면은 음악이 안들리도록 하기
		Music introMusic = new Music("Dizzy.mp3", true);
		introMusic.start();
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
				
				selectTrack(0);	// 메인화면으로 가자!!!
				
				// * 게임시작 이벤트 -> 화면전환
				startButton.setVisible(false);
				quitButton.setVisible(false);
				
				leftButton.setVisible(true); // 화면 보이기!
				rightButton.setVisible(true);
				
				easyButton.setVisible(true);
				hardButton.setVisible(true);
				
				background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
				isMainScreen = true;
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
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	private void screenDraw(Graphics g) {
		g.drawImage(background,0,0,null);
		if(isMainScreen) {// 메인화면일떄? 보여줘..
			g.drawImage(selectedImage,340,100,null);
			// 곡 제목 이미지 
			g.drawImage(titleImage,340,70,null);			
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
	}
}
