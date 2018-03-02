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
 * 瀹㈡埛绔殑涓荤晫闈�
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
		public static String ip = "172.25.51.10";//"localhost" 璁剧疆鎴愭湇鍔″櫒IP
		//static String ip = LoginFrame.ip;
		
	   
		public MainFrameC(String title) {
			super(title);
			this.setSize(360, 500);
			this.add(jpanel);
			jpanel.setLayout(null);
			msgArea.setEditable(false);
			
			Icon runImg1 = new ImageIcon(this.getClass().getClassLoader().getResource("images/001.jpg"));
			button1.setIcon(runImg1);
			jpanel.add(button1);
		    button1.setBounds(10, 10, 130, 132);
		    
		    Icon runImg2 = new ImageIcon(this.getClass().getClassLoader().getResource("images/001.jpg"));
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
			//FIXME 涓嶈鍏抽棴鍔ㄤ綔锛屽氨鍙槸榛樿闅愯棌绐楀彛锛屽彂閫佸浘鐗囩殑鍔ㄤ綔杩樺湪杩涜
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

			new RemoteMonitor().start(); // 鎴浘绾跨▼寮�濮�
			MainFrameC clientframe = new MainFrameC("communication"); // 鎵撳紑涓荤晫闈�
			clientframe.setVisible(true);
			clientframe.getSocket();// 鏀惧墠闈�
			new FileClient();
		// ControlThread ct = new ControlThread("localhost",9999);
		// new Thread(ct).start();
	}
}
