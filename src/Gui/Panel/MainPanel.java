package Gui.Panel;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Gui.Listener.ToolBarListener;
import Util.CenterPanel;
import Util.GUIUtil;

public class MainPanel extends JPanel {
	
	public static MainPanel instance = new MainPanel();
	public JToolBar tb = new JToolBar();
	public JButton bSpend = new JButton();
	public  JButton bRecord = new JButton();
	public JButton bCategory = new JButton();
	public JButton bReport = new JButton();
	public JButton bConfig = new JButton();
	public JButton bBackup = new JButton();
	public JButton bRecover = new JButton();
	public String imgFolder = "C://Users/xiewe/Desktop/workplace/Cashbook/img/";
	
	public CenterPanel workingPanel;
	
	private MainPanel(){
		GUIUtil.setImageIcon(bSpend, imgFolder+"home.png", "消费一览");
		GUIUtil.setImageIcon(bRecord, imgFolder+"record.png", "记一笔");
		GUIUtil.setImageIcon(bCategory, imgFolder+"category2.png", "消费分类");
		GUIUtil.setImageIcon(bReport, imgFolder+"report.png", "月消费报表");
		GUIUtil.setImageIcon(bConfig, imgFolder+"config.png", "设置");
		GUIUtil.setImageIcon(bBackup, imgFolder+"backup.png", "备份"); 
		GUIUtil.setImageIcon(bRecover, imgFolder+"restore.png", "恢复");
		workingPanel = new CenterPanel(0.8);
		
		tb.add(bSpend);
		tb.add(bRecord);
		tb.add(bCategory);
		tb.add(bReport);
		tb.add(bConfig);
		tb.add(bBackup);
		tb.add(bRecover);
		tb.setFloatable(false);
		
		setLayout(new BorderLayout());
		add(tb,BorderLayout.NORTH);	
		add(workingPanel,BorderLayout.CENTER);
		addListener();
	}
	
	private void addListener() {
		
		ToolBarListener listener = new ToolBarListener();
		
		bSpend.addActionListener(listener);
		bRecord.addActionListener(listener);
		bCategory.addActionListener(listener);
		bReport.addActionListener(listener);
		bConfig.addActionListener(listener);
		bBackup.addActionListener(listener);
		bRecover.addActionListener(listener);

	}
	
}
