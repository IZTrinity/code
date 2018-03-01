package Server;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Screenshots {
	public Screenshots() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenShotWindow ssw = new ScreenShotWindow();
					ssw.setVisible(true);
				} catch (AWTException e) {
					e.printStackTrace();
				}
			}
		});
	}
}

class ScreenShotWindow extends JWindow {

	private static final long serialVersionUID = 1L;
	private int orgx, orgy, endx, endy;
	private BufferedImage image = null;
	private BufferedImage tempImage = null;
	private BufferedImage saveImage = null;
	private ToolsWindow tools = null;

	public ScreenShotWindow() throws AWTException {

		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice screen = environment.getDefaultScreenDevice();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0, 0, d.width, d.height);
		Robot robot = new Robot(screen);
		image = robot.createScreenCapture(new Rectangle(0, 0, d.width, d.height));

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				orgx = e.getX();
				orgy = e.getY();
				if (tools != null) {
					tools.setVisible(false);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (tools == null) {
					tools = new ToolsWindow(ScreenShotWindow.this, e.getX(), e.getY());
				} else {
					tools.setLocation(e.getX(), e.getY());
				}
				tools.setVisible(true);
				tools.toFront();
			}
		});


		this.addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent e) {
				endx = e.getX();
				endy = e.getY();
				Image tempImage2 = createImage(ScreenShotWindow.this.getWidth(), ScreenShotWindow.this.getHeight());
				Graphics g = tempImage2.getGraphics();
				g.drawImage(tempImage, 0, 0, null);
				int x = Math.min(orgx, endx);
				int y = Math.min(orgy, endy);
				int width = Math.abs(endx - orgx) + 1;
				int height = Math.abs(endy - orgy) + 1;

				g.setColor(Color.RED);
				g.drawRect(x - 1, y - 1, width + 1, height + 1);
				saveImage = image.getSubimage(x, y, width, height);

				g.drawImage(saveImage, x, y, null);
				ScreenShotWindow.this.getGraphics().drawImage(tempImage2, 0, 0, ScreenShotWindow.this);
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {

		RescaleOp ro = new RescaleOp(0.8f, 0, null);
		tempImage = ro.filter(image, null);
		g.drawImage(tempImage, 0, 0, this);

	}
	public void saveImage() throws IOException {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Screenshots");

		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "jpg");
		jfc.setFileFilter(filter);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
		String filename = sdf.format(new Date());

		File filePath = FileSystemView.getFileSystemView().getHomeDirectory();
		File defaultFile = new File(filePath + File.separator + filename + ".jpg");
		jfc.setSelectedFile(defaultFile);

		int flag = jfc.showSaveDialog(this);
		if (flag == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			String path = file.getPath();

			if (!(path.endsWith(".jpg") || path.endsWith("JPG"))) {
				path += ".jpg";
			}

			ImageIO.write(saveImage, "jpg", new File(path));
			// System.exit(0);
		}
		this.setVisible(false);
	}

}
