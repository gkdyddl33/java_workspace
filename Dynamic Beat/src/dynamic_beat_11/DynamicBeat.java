package dynamic_beat_11;

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
	
	// ��üȭ���� ��� �ν��Ͻ� - "����ȭ��" �̹����� �ҷ��ͺ���!!
	private Image screenImage;
	private Graphics screenGraphic;
	
	// ���ӽ��� �̹��� �� ���θ޴� �̹���	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).png")).getImage();
	
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
		
	// ��ư�� ����!
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	
	// -- ���������ư
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));	
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	
	// -- �¿��ư
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));	
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
		
	// -- easy,hard��ư
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
	
	// �޴��� ����
	private int mouseX,mouseY;	
		
	private boolean isMainScreen = false;	// ���� �޴��� ����ȭ�� ȭ����ȯ �񱳰�
																// ó������ ����ȭ��!!
	private boolean isGameScreen = false;	// ����ȭ������ �Ѿ�Գ�?
	
	ArrayList<Track> trackList = new ArrayList<>();
	private Music selectedMusic;
	// ���ӽ��� �̹��� �� ���θ޴� �̹���
	private Image selectedImage;
	// �� Title �̹���
	private Image titleImage;
	// �������ڸ��� ������ ������ �ϱ� - �׸��� '�����ϱ�' ��ư�� �������� ������ �ȵ鸮���� �ϱ�
	private Music introMusic = new Music("Dizzy.mp3", true);
	private int nowSelected =0;	// ù��° ���� �ǹ�!
	
	public static Game game = new Game();
	
	public DynamicBeat() {
		// �޴��ٰ� ������ �ʰ� �ϱ� ����
		setUndecorated(true);
		
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		// �̺�Ʈ
		addKeyListener(new KeyListener());
		
		introMusic.start(); // �ε� �Ǵ� ���� ���ǳ�����.
		
		// �� ������ ����..
		trackList.add(new Track("Mighty Love Title Image.png","Mighty Love Start Image.jpg",
				"Mighty Love Game Image.jpg","Mighty Love Selected.mp3","Joakim Karud - Mighty Love.mp3"));
		
		trackList.add(new Track("Wild Flower Title Image.png","Wild Flower Start Image.jpg",
				"Wild Flower Game Image.jpg","Wild Flower Selected.mp3","Joakim Karud - Wild Flower.mp3"));
		
		trackList.add(new Track("Energy Title Image.png","Energy Start Image.jpg",
				"Energy Game Image.jpg","Energy Selected.mp3","Bensound - Energy.mp3"));
		
		
		// -----> ��ư ������ ������ �����غ���
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);		
		exitButton.setFocusPainted(false);	
		
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺 �ø���
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺 ȿ����
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// ���콺 ����
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// ���콺 ���� ��
				// ���콺 ȿ����
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				try {
					// �Ҹ��� ������ 1�ʵڿ� ����
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
			public void mouseEntered(MouseEvent e) {// ���콺 �ø���
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺 ȿ����
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// ���콺 ����
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// ���콺 ���� ��
				// ���콺 ȿ����
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				
				introMusic.close();
//				Music selectedMusic = new Music("Mighty Love Selected.mp3", true);	// true�� ���� ���ѹݺ�!
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
			public void mouseEntered(MouseEvent e) {// ���콺 �ø���
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺 ȿ����
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// ���콺 ����
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// ���콺 ���� ��
				// ���콺 ȿ����
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				try {
					// �Ҹ��� ������ 1�ʵڿ� ����
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});		
		add(quitButton);
		
		// ÷���� �Ⱥ��̴ٰ� �����ϱ� �ϸ� ���̰�
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);		
		leftButton.setFocusPainted(false);	
		
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺 �ø���
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺 ȿ����
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// ���콺 ����
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// ���콺 ���� ��
				// ���콺 ȿ����
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				// ���� ��ư �̺�Ʈ
				selectLeft();
			}
		});		
		add(leftButton);
		
		// ÷���� �Ⱥ��̴ٰ� �����ϱ� �ϸ� ���̰�
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);		
		rightButton.setFocusPainted(false);	
		
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {// ���콺 �ø���
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺 ȿ����
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// ���콺 ����
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// ���콺 ���� ��
				// ���콺 ȿ����
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				// ������ ��ư �̺�Ʈ
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
			public void mouseEntered(MouseEvent e) {// ���콺 �ø���
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺 ȿ����
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// ���콺 ����
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// ���콺 ���� ��
				// ���콺 ȿ����
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				// ���̵� ���� �̺�Ʈ
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
			public void mouseEntered(MouseEvent e) {// ���콺 �ø���
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺 ȿ����
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// ���콺 ����
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// ���콺 ���� ��
				// ���콺 ȿ����
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				// ���̵� ���� �̺�Ʈ	
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
			public void mouseEntered(MouseEvent e) {// ���콺 �ø���
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ���콺 ȿ����
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {// ���콺 ����
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {// ���콺 ���� ��
				// ���콺 ȿ����
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonPressedMusic.start();
				// ����ȭ������ ���ư��� �̺�Ʈ	
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
		// �޴��ٸ� ��� ������ �� �ְ� ���ش�!
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
		screenDraw((Graphics2D)screenGraphic); // ���ڱ�������
		g.drawImage(screenImage, 0, 0, null);
	}

	private void screenDraw(Graphics2D g) {
		g.drawImage(background,0,0,null);
		if(isMainScreen) {// ����ȭ���ϋ�? ������..
			g.drawImage(selectedImage,340,100,null);
			// �� ���� �̹��� 
			g.drawImage(titleImage,340,70,null);			
		}
		if(isGameScreen) {// ������ ������ ���� info �������� ����....
			game.screenDraw(g); // Game Ŭ������ �����ϰ� �ִ� ���� ���� ����!!
		}
		paintComponents(g);	// �̹����� ��ũ�� �̹����� �׷��ִ� ��!
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) {// �ش� ���� ������ �Ǿ��ٸ�?
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		// Track -> VO �� titleImage�� ���� �����ͼ� �ٲ��ش�.
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(),true);
		selectedMusic.start();
	}
	// �¿� ��ư �̺�Ʈ �ֱ�
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
			selectedMusic.close();	// ���� ����� ���� �ݱ�
		}
		isMainScreen = false;	// ���� ȭ���� �ƴϴ�.
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		isGameScreen = true; // ���ӽ���
		setFocusable(true); 		// ���� â�� Ű���� ��Ŀ�� â�� ������
	}
	
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
				.getImage();
		backButton.setVisible(false);	// �ڷΰ��� ��ư�� ���� �Ⱥ��̰�..
		selectTrack(nowSelected);	// ó�� ����ȭ�� �������.
		isGameScreen = false;
	}
	public void enterMain() {
		
		// * ���ӽ��� �̺�Ʈ -> ȭ����ȯ
		startButton.setVisible(false);
		quitButton.setVisible(false);
		
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		isMainScreen = true;
		
		leftButton.setVisible(true); // ȭ�� ���̱�!
		rightButton.setVisible(true);
		
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);	// ����ȭ������ ����!!!
	}
}
