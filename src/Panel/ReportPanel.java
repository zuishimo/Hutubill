package Panel;

import java.awt.BorderLayout;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Entity.Record;
import Service.ReportService;
import Util.ChartUtil;
import Util.GUIUtil;

public class ReportPanel extends WorkingPanel {
	
	public static ReportPanel instance = new ReportPanel();
	public JLabel lReport = new JLabel();
	
	private ReportPanel() {
		this.setLayout(new BorderLayout());
		
		List<Record> rs = new ReportService().listThisMonthRecords();
		Image img = ChartUtil.getImage(rs,400, 300);
		ImageIcon icon = new ImageIcon(img);
		lReport.setIcon(icon);
		this.add(lReport);
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		List<Record> rs = new ReportService().listThisMonthRecords();
		Image img = ChartUtil.getImage(rs, 400, 300);
		ImageIcon icon = new ImageIcon(img);
		lReport.setIcon(icon);
		addListener();
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}

}
