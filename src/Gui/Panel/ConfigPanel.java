package Gui.Panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gui.Listener.ConfigListener;
import Service.ConfigService;
import Util.ColorUtil;
import Util.GUIUtil;

public class ConfigPanel extends WorkingPanel {
	
	public static ConfigPanel instance = new ConfigPanel();
	
	public JLabel lMonthBudget = new JLabel("本月预算");
	public JTextField tfBudget = new JTextField();
	public JLabel lMySqlAdress = new JLabel("MySql安装目录");
	public JTextField tfMysqlPath = new JTextField();
	public JButton bUpdate = new JButton("更新");
	
	private ConfigPanel() {
		this.setLayout(new BorderLayout());
		
		GUIUtil.setColor(ColorUtil.grayColor, lMonthBudget,lMySqlAdress);
		GUIUtil.setColor(ColorUtil.blueColor, bUpdate);
		
		this.add(center(),BorderLayout.NORTH);
		this.add(south(),BorderLayout.CENTER);
		addListener();
	}
	
	public void addListener() {
		ConfigListener l = new ConfigListener();
		bUpdate.addActionListener(l);
	}
	
	private JPanel center() {
		JPanel p = new JPanel();
		int gap = 40;
		p.setLayout(new GridLayout(4,1,gap,gap));
		
		p.add(lMonthBudget);
		p.add(tfBudget);
		p.add(lMySqlAdress);
		p.add(tfMysqlPath);
		
		return p;
	}
	
	private JPanel south() {
		JPanel p = new JPanel();
		p.add(bUpdate);
		return p;
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		String budget = new ConfigService().get(ConfigService.budget);
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		
		tfBudget.setText(budget);
		tfMysqlPath.setText(mysqlPath);
		tfBudget.grabFocus();
	}

}
