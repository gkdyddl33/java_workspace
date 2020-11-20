package common.image;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtil {

	// URL �� ���� �̹��� ��������(���ͳ��� ����Ǿ� �־�� ��)
	public static Image getImageFromURL(String path) {
		Image img = null;
		try {
			URL url = new URL(path);
			img=ImageIO.read(url);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return img;
	}
	
	// �������� ��ȯ���ִ� �޼���
	public static ImageIcon getIcon(Class target,String path,int width, int height) {
		// �̹��� ���� ��ҿ� ���
		ImageIcon icon = null;		
		// target.getclass()
		icon = new ImageIcon(target.getClassLoader().getResource(path));
		
		// ũŰ�� ������ �̹��� ����
		Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}
	
	
	// �̹����� �Ѱܹ޾�, ���ϴ� ũ���� �̹����� ��ȯ�ϴ� �޼���
	public static Image getCustomSize(Image img, int width, int height){
		return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
}