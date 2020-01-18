package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Entity.Category;
import Panel.CategoryPanel;
import Panel.MainPanel;
import Panel.RecordPanel;
import Panel.SpendPanel;
import Service.RecordService;
import Util.GUIUtil;

public class RecordListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		RecordPanel p = RecordPanel.instance;
		
		if(0 == p.cbModel.cs.size()) {
			
			JOptionPane.showMessageDialog(p, "暂无消费分类，无法添加，请先添加消费分类");
			MainPanel.instance.workingPanel.show(CategoryPanel.instance);
			return;
		}
		
		if(GUIUtil.checkZero(p.tfSpend, "花费金额")) {
			return;
		}
		
		int spend = Integer.parseInt(p.tfSpend.getText());
		Category c = p.getSelectedCategory();
		String comment = p.tfComment.getText();
		java.util.Date d = p.datePicker.getDate();
		
		new RecordService().add(spend, c, comment, d);
		JOptionPane.showMessageDialog(p, "添加成功");
		
		MainPanel.instance.workingPanel.show(SpendPanel.instance);
	}

	
	
}
