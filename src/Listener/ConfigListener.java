package Listener;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import Panel.ConfigPanel;
import Service.ConfigService;
import Util.GUIUtil;

public class ConfigListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ConfigPanel p = ConfigPanel.instance;
		if(GUIUtil.checkNagative(p.tfBudget, "本月预算")) {
			return;
		}
		String mysqlPath = p.tfMysqlPath.getText();
		if(0 != mysqlPath.length()) {
			File commandFile = new File(mysqlPath,"/bin/mysql.exe");
			if(!commandFile.exists()) {
				JOptionPane.showMessageDialog(p, "Mysql路径不正确");
				
				p.tfMysqlPath.grabFocus();
				return;
			}
		}
		ConfigService cs = new ConfigService();
		cs.update(ConfigService.budget, p.tfBudget.getText());
		cs.update(ConfigService.mysqlPath,mysqlPath);
		
		JOptionPane.showMessageDialog(p, "设置修改成功");
	}

	
}
