
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UPLOAD extends JFrame {
	JButton button1=new JButton("�ļ�����");
	JTextArea text1=new JTextArea(10,20);
    Container cp=getContentPane(); 
	public UPLOAD() {
	    super("�ļ�����");
    	cp.setLayout(null);
    	
    	text1.setSize(230,25);
    	text1.setLocation(50,15);
    	cp.add(text1);
    	
    	button1.setSize(60,20);
    	button1.setLocation(300,15);
    	cp.add(button1);
    	
    	button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//�ļ��ϴ�
			}
			});
    	
    	
        setSize(400,100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]){
		new UPLOAD();
	}
}
