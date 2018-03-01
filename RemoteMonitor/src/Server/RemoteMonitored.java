package Server;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import ServerGUI.MainFrameS;
//屏幕监控的显示框用JButton,直接用一个setImageIcon(imaegIcon)就可以显示图片，就不用重画JFrame的背景
public class  RemoteMonitored  extends JButton implements Runnable{
    private static final long serialVersionUID = 1L;  
    Dimension screenSize;  
    private ServerSocket imgss;
    private Socket imgs;
    public String ip=null;
    public  RemoteMonitored () {  
    	//this.setSize(width, height);//设置按钮的大小
    	try {
			imgss = new ServerSocket(5001);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }  
//在线程中不断获取图片
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			try {
				while((imgs = imgss.accept()) != null){
					System.out.println("端口5001有连接");

					ip = imgs.getInetAddress().getHostAddress(); 
					System.out.println("ip is:"+ip);
					
					InputStream is = imgs.getInputStream();
					Image i = ImageIO.read(is);
					if(i != null){
						System.out.println("图片接受成功");
						//对图像进行压缩，使得图片符合JButton大小
						i = i.getScaledInstance(320, 160, Image.SCALE_SMOOTH);
//						i = i.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
//						this.setIcon(new ImageIcon(i));

						//FIXME 每次收到图片，就刷新监控窗口的画面
						MainFrameS.getInstance("FarContrl").setButtonWin(ip, i);
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}  