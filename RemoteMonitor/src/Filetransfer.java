
package Server;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UPLOAD extends JPanel {
	JButton button1=new JButton("�ļ�����");
	JTextArea text1=new JTextArea(10,20);

	public UPLOAD() {

    	text1.setSize(230,25);
    	text1.setLocation(50,15);
    	
    	button1.setSize(60,20);
    	button1.setLocation(300,15);
    	
    	button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//�ļ��ϴ�
			}
			});
    	
        setSize(400,100);
        setVisible(true);
	}
	
	public static void main(String args[]){
		new UPLOAD();
	}
}
