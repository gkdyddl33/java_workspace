package dynamic_beat_5;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame{
	
	// ��üȭ���� ��� �ν��Ͻ� - "����ȭ��" �̹����� �ҷ��ͺ���!!
	private Image screenImage;
	private Graphics screenGraphic;
	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	// ��ư�� ����!
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));	
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
		
	private JButton exitButton = new JButton(exitButtonBasicImage);	
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	
	// �޴��� ����
	private int mouseX,mouseY;	
		
	
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
				// * ���ӽ��� �̺�Ʈ -> ȭ����ȯ
				startButton.setVisible(false);
				quitButton.setVisible(false);
				background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
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
		
		Music introMusic = new Music("Dizzy.mp3", true);
		introMusic.start();
	}
	
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	private void screenDraw(Graphics g) {
		g.drawImage(background,0,0,null);
		paintComponents(g);	// �̹����� ��ũ�� �̹����� �׷��ִ� ��!
		this.repaint();
	}
}
