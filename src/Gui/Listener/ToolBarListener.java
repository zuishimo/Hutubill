package Gui.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Gui.Panel.BackupPanel;
import Gui.Panel.CategoryPanel;
import Gui.Panel.ConfigPanel;
import Gui.Panel.MainPanel;
import Gui.Panel.RecordPanel;
import Gui.Panel.RecoverPanel;
import Gui.Panel.ReportPanel;
import Gui.Panel.SpendPanel;

public class ToolBarListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		MainPanel p = MainPanel.instance;
		
		JButton b = (JButton)e.getSource();
		
		if(b==p.bReport)
			p.workingPanel.show(ReportPanel.instance);
		
		if(b==p.bCategory)
			p.workingPanel.show(CategoryPanel.instance);
		
		if(b==p.bSpend)
			p.workingPanel.show(SpendPanel.instance);
		
		if(b==p.bRecord)
			p.workingPanel.show(RecordPanel.instance);
			
		if(b==p.bConfig)
			p.workingPanel.show(ConfigPanel.instance);
			
		if(b==p.bBackup)
			p.workingPanel.show(BackupPanel.instance);
		
		if(b==p.bRecover)
			p.workingPanel.show(RecoverPanel.instance);
	}

	
	
}
