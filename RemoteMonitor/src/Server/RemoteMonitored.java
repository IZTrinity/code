package Server;

/*����clientΪ�ͻ��ˣ����ƶˣ���servletΪ����ˣ������ƶˣ���
 * ֱ����������jar�ļ����ɡ�������jar�ļ������ڵ��԰�װjdk����
 * ���ܽ�ͼ
*/
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
//��Ļ��ص���ʾ����JButton,ֱ����һ��setImageIcon(imaegIcon)�Ϳ�����ʾͼƬ���Ͳ����ػ�JFrame�ı���
public class  RemoteMonitored  extends JButton implements Runnable{  
    private static final long serialVersionUID = 1L;  
    Dimension screenSize;  
    private ServerSocket imgss;
    private Socket imgs;
    public  RemoteMonitored () {  
    	//this.setSize(width, height);//���ð�ť�Ĵ�С
    	try {
			imgss = new ServerSocket(5001);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
        //super();  
        //screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
        //this.setSize(800, 640);  
        //Screen p = new Screen();  
        //Container c = this.getContentPane();  
        //c.setLayout(new BorderLayout());  
        //c.add(p, SwingConstants.CENTER);  
        //new Thread(p).start();  
        //SwingUtilities.invokeLater(new Runnable(){  
            //public void run() {  
            //    setVisible(true);  
          //  }});  
    }  
    /*public static void main(String[] args) {  
        new  RemoteMonitored ();  
    }  
  
    class Screen extends JPanel implements Runnable {  
  
        private static final long serialVersionUID = 1L;  
        private Image cimage;  
  
        public void run() {  
            ServerSocket ss = null;  
            try {  
                ss = new ServerSocket(5001);// ̽��5001�˿ڵ�����  
                while (true) {  
                    Socket s = null;  
                    try {  
                        s = ss.accept();  
                        ZipInputStream zis = new ZipInputStream(s  
                                .getInputStream());  
                        zis.getNextEntry();  
                        cimage = ImageIO.read(zis);// ��ZIP��ת��ΪͼƬ  
                        repaint();  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    } finally {  
                        if (s != null) {  
                            try {  
                                s.close();  
                            } catch (IOException e) {  
                                e.printStackTrace();  
                            }  
                        }  
                    }  
                }  
            } catch (Exception e) {  
            } finally {  
                if (ss != null) {  
                    try {  
                        ss.close();  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
  
        public Screen() {  
            super();  
            this.setLayout(null);  
        }  
  
        public void paint(Graphics g) {  
            super.paint(g);  
            Graphics2D g2 = (Graphics2D) g;  
            g2.drawImage(cimage, 0, 0, null);  
        }  
    }  */
//���߳��в��ϻ�ȡͼƬ
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		while(true){
			try {
				while((imgs = imgss.accept()) != null){
					System.out.println("�˿�5001������");
					InputStream is = imgs.getInputStream();
					Image i = ImageIO.read(is);
					if(i != null){
						System.out.println("ͼƬ���ܳɹ�");
						//��ͼ�����ѹ����ʹ��ͼƬ����JButton��С
						i = i.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
						this.setIcon(new ImageIcon(i));
					}
				}
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}  