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

import ServerGUI.MainFrameS;
import ServerGUI.MainFrameS;
//��Ļ��ص���ʾ����JButton,ֱ����һ��setImageIcon(imaegIcon)�Ϳ�����ʾͼƬ���Ͳ����ػ�JFrame�ı���
public class  RemoteMonitored  extends JButton implements Runnable{  
    private static final long serialVersionUID = 1L;  
    Dimension screenSize;  
    private ServerSocket imgss;
    private Socket imgs;
    public String ip=null;
    public  RemoteMonitored () {  
    	//this.setSize(width, height);//���ð�ť�Ĵ�С
    	try {
			imgss = new ServerSocket(5001);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }  
//���߳��в��ϻ�ȡͼƬ
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		while(true){
			try {
				while((imgs = imgss.accept()) != null){
					System.out.println("�˿�5001������");
					
					ip = imgs.getInetAddress().getHostAddress(); 
					System.out.println("ip is:"+ip);
					
					InputStream is = imgs.getInputStream();
					Image i = ImageIO.read(is);
					if(i != null){
						System.out.println("ͼƬ���ܳɹ�");
						//��ͼ�����ѹ����ʹ��ͼƬ����JButton��С

						i = i.getScaledInstance(320, 160, Image.SCALE_SMOOTH);
						//i = i.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
						//this.setIcon(new ImageIcon(i));
						//FIXME ÿ���յ�ͼƬ����ˢ�¼�ش��ڵĻ���
						MainFrameS.getInstance("FarContrl").setButtonWin(ip, i);
					}
				}
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}  