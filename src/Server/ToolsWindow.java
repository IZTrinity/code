package Server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOError;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JWindow;

public class ToolsWindow extends JWindow {


	private static final long serialVersionUID = 1L;
	private ScreenShotWindow parent;

	public ToolsWindow(ScreenShotWindow parent, int x, int y) {
		this.parent = parent;
		this.init();
		this.setLocation(x, y);
		this.pack();
		this.setVisible(true);
	}

	private void init() {

		this.setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar("Java��ͼ");
		JButton saveButton = new JButton(new ImageIcon("src/images/SaveIcon.gif"));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					parent.saveImage();
				} catch (IOException ex1) {
					ex1.printStackTrace();

				}
			}
		});
		toolBar.add(saveButton);

		JButton closedButton = new JButton(new ImageIcon("src/images/closedIcon.gif"));
		closedButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// System.exit(0);
			}
		});
		toolBar.add(closedButton);
		this.add(toolBar, BorderLayout.NORTH);

	}
}