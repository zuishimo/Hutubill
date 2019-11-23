package Gui.Frame;

import javax.swing.JFrame;

import Gui.Panel.MainPanel;

public class MainFrame extends JFrame {

	public static MainFrame instance = new MainFrame();
	
	private MainFrame() {
		this.setSize(500,500);
		this.setTitle("º«’À±æ");
		this.setContentPane(MainPanel.instance);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
}
