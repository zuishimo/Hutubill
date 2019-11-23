package Gui.Panel;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Util.ChartUtil;
import Util.GUIUtil;

public class ReportPanel extends JPanel {
	
	public static ReportPanel instance = new ReportPanel();
	public JLabel lReport = new JLabel();
	
	private ReportPanel() {
		this.setLayout(new BorderLayout());
		
		Image img = ChartUtil.getImage(400, 300);
		ImageIcon icon = new ImageIcon(img);
		lReport.setIcon(icon);
		this.add(lReport);
	}

}
