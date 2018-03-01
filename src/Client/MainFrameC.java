package Client;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.net.Socket;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTextArea;
	import javax.swing.JTextField;
/*
 * 客户端的主界面
 */

public class MainFrameC extends JFrame implements Runnable {
		private JPanel jpanel = new JPanel();
		private JLabel nameLabel = new JLabel("name:");
		private JTextField nameField = new JTextField();
		private JTextArea msgArea = new JTextArea();
		private JTextField sendField = new JTextField();
		private JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		private BufferedReader reader;
		private PrintWriter writer;
		private Socket socket;
		private JButton button1=new JButton(" ");
		private JButton button2=new JButton(" ");

		private static FileClientSocket cs = null;
		public static String ip = "172.25.51.10";//"localhost" 设置成服务器IP
		//static String ip = LoginFrame.ip;
		
	   
		public MainFrameC(String title) {
			super(title);
			this.setSize(360, 500);
			this.add(jpanel);
			jpanel.setLayout(null);
			msgArea.setEditable(false);
			
			Icon runImg1 = new ImageIcon(this.getClass().getClassLoader().getResource("images/007.jpg"));
			button1.setIcon(runImg1);
			jpanel.add(button1);
		    button1.setBounds(10, 10, 130, 132);
		    
		    Icon runImg2 = new ImageIcon(this.getClass().getClassLoader().getResource("images/007.jpg"));
			button2.setIcon(runImg2);
			jpanel.add(button2);
			button2.setBounds(180, 10, 130, 132);
			
			jpanel.add(nameLabel);
			nameLabel.setBounds(10, 160, 60, 20);
			jpanel.add(nameField);
			nameField.setBounds(60, 160, 270, 21);
			jpanel.add(sendField);
			sendField.setBounds(10, 430, 320,21);
			msgArea.setColumns(20);
			msgArea.setRows(5);
			jScrollPane1.setViewportView(msgArea);
			jpanel.add(jScrollPane1);
			jScrollPane1.setBounds(10, 190, 320, 220);
			sendField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					writer.println(nameField.getText()+"Student:" + sendField.getText());
					sendField.setText("");
				}
			});
			//FIXME 不设关闭动作，就只是默认隐藏窗口，发送图片的动作还在进行
			//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setVisible(true);
		}

		@Override
		public void run() {
			while(true){
				try{
					msgArea.append(reader.readLine()+"\n");
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		public void getSocket(){
			msgArea.append("Try to connect to server\n");
			try{
				socket = new Socket(ip,7788);
				msgArea.append("You can talk with your teacher now\n");
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(),true);
				new Thread(this).start();
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	public static void main(String args[]) {

			new RemoteMonitor().start(); // 截图线程开始
			MainFrameC clientframe = new MainFrameC("communication"); // 打开主界面
			clientframe.setVisible(true);
			clientframe.getSocket();// 放前面
			new FileClient();
		// ControlThread ct = new ControlThread("localhost",9999);
		// new Thread(ct).start();
	}
}
