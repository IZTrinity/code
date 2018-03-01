package Client;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

/*
 * 截图线程
 */
public class RemoteMonitor extends Thread {
	private Dimension screenSize;
	private Rectangle rectangle;
	private Robot robot;
	OutputStream os;
	String ip = MainFrameC.ip;//"localhost" 设置成服务器IP
	public RemoteMonitor() {
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		rectangle = new Rectangle(screenSize);// 可以指定捕获屏幕区域
		try {
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	public void run() {
		os = null;
		Socket socket = null;
		while (true) {
			try {
				socket = new Socket(ip, 5001);// 连接远程IP
				BufferedImage image = robot.createScreenCapture(rectangle);// 捕获制定屏幕矩形区域
				os = socket.getOutputStream();
				ImageIO.write(image, "JPEG", os);
				/*这一段有错误
				os.setLevel(9);
				os.putNextEntry(new ZipEntry("test.jpg"));
				JPEGCodec.createJPEGEncoder(os).encode(image);// 图像编码成JPEG*(不同版本有兼容问题)*/
				os.flush();
				socket.close();
				Thread.sleep(1000);// 每秒20帧
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (os != null) {
					try {
						os.close();
					} catch (Exception ioe) {
					}
				}
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
					}
				}
			}
		}//while(true)
	}//run
}