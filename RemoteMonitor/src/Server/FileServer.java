
package Server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import ServerGUI.MainFrameS;

public class FileServer {
	private ServerSocket ss;
	public static String path = null;
	Socket st;
	DataInputStream fis;
	DataOutputStream ps;

	public FileServer() {
	}

	public FileServer(Socket s) {
		this.st = s;
	}

	public void open() throws IOException {
		ss = new ServerSocket(8821);
		System.out.println("sssssSocket...");
	}

	public void choose() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jfc.showDialog(new JLabel(), "Choose");
		File file = jfc.getSelectedFile();
		if (file != null) {
			path = file.getAbsolutePath();
			System.out.println(jfc.getSelectedFile().getName());

		} else {
			jfc.setVisible(false);
		}
	}

	public Socket getsever() {
		Socket s = null;
		try {
			s = ss.accept();// �ȴ��ͻ��� ����8821�˿�
			System.out.println("����socket����");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	public void start() {
		
		try {
			while (true) {
				// ѡ����д�����ļ�
				String filePath = path;
				File fi = new File(filePath);
				System.out.println("st:" + st);
				ps = new DataOutputStream(st.getOutputStream());
				System.out.println("�ļ�����:" + (int) fi.length());
				System.out.println("�ļ�·��:" + path);

				// public Socket accept() throws
				// IOException���������ܵ����׽��ֵ����ӡ��˷����ڽ�������֮ǰһֱ������

				// DataInputStream dis = new DataInputStream(new
				// BufferedInputStream(s.getInputStream()));
				// dis.readByte();

				fis = new DataInputStream(new BufferedInputStream(
						new FileInputStream(fi/* new File(filePath) */)));

				// ���ļ��������ȴ����ͻ��ˡ�����Ҫ������������ƽ̨�������������Ĵ�������Ҫ�ӹ���������Բμ�Think In Java
				// 4th�����ֳɵĴ��롣
				ps.writeUTF(fi.getName());
				ps.flush();
				ps.writeLong((long) fi.length());
				ps.flush();

				int bufferSize = 8192;
				byte[] buf = new byte[bufferSize];

				 while (true) {
	                    int read = 0;
	                    if (fis != null) {
	                        read = fis.read(buf);
	                    }

	                    if (read == -1) {
	                        break;
	                    }
	                    ps.write(buf, 0, read);
	                }
	                ps.flush();
	                // ע��ر�socket����Ŷ����Ȼ�ͻ��˻�ȴ�server�����ݹ�����
	                // ֱ��socket��ʱ���������ݲ�������                
	                fis.close();
	                try{
	                st.close();    
	                }catch(Exception exx){
	                	 exx.printStackTrace();
	                }/***********/
	                System.out.println("�ļ��������");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}

// public static void main(String arg[]) {
// new FileServer().start();
// }
