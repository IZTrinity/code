import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

//������Ļ
public class ScreenShot1 extends JFrame implements ActionListener {

	private ScreenCaptureUtil scrCaptureUtil = null;// ������Ļ�Ĺ�����
	private PaintCanvas canvas = null;// ���������ڻ����񵽵���Ļͼ��

	public ScreenShot1() {
		super("Screen Capture");
		init();
	}

	//��ʼ��
	private void init() {

		scrCaptureUtil = new ScreenCaptureUtil();// ����ץ�����ߣ���������
		canvas = new PaintCanvas(scrCaptureUtil);// ��������

		Container cp= this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);

		JButton capButton = new JButton("ץ ��");
		cp.add(capButton, BorderLayout.SOUTH);
		capButton.addActionListener(this);
		this.setSize(400, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
// �����ץ������ťʱ���ڻ����ϻ���Ļͼ��
	public void actionPerformed(ActionEvent e) {
		canvas.drawScreen();
	}

	public static void main(String[] args) {
		new ScreenShot1();
	}
}


// ץ��������
class ScreenCaptureUtil {
	private Robot robot = null;// ץ������Ҫ������
	private Rectangle scrRect = null;// ��Ļ�ľ���ͼ��

	public ScreenCaptureUtil() {
		try {
			robot = new Robot();// ����һ��ץ������
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		// ��ȡ��Ļ�ľ���ͼ��
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		scrRect = new Rectangle(0, 0, scrSize.width, scrSize.height);
	}

	//ץ������,����һ��ͼ�� 
	public BufferedImage captureScreen() {
		BufferedImage scrImg = null;
		try {
			scrImg = robot.createScreenCapture(scrRect);// ץ����ȫ��ͼ
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return scrImg;
	}
}

// �����࣬������ʾץ���õ���ͼ��

class PaintCanvas extends JPanel {
	private ScreenCaptureUtil scrCaptureUtil = null;// ץ������
	private BufferedImage scrImg = null;// ������ͼ��

	public PaintCanvas(ScreenCaptureUtil screenUtil) {
		this.scrCaptureUtil = screenUtil;
	}

	
	// ����JPanel��paintComponent�����ڻ�����
	protected void paintComponent(Graphics g) {
		if (scrImg != null) {
			int iWidth = this.getWidth();
			int iHeight = this.getHeight();
			g.drawImage(scrImg, 0, 0, iWidth, iHeight, 0, 0, scrImg.getWidth(),
					scrImg.getHeight(), null);
		}
	}

// ����Ļͼ��ķ���

	public void drawScreen() {
		Graphics g = this.getGraphics();
		scrImg = scrCaptureUtil.captureScreen();// ץ������ȡ��Ļͼ��
		if (scrImg != null) {
			this.paintComponent(g);// ��ͼ
		}
		g.dispose();// �ͷ���Դ
	}

}

