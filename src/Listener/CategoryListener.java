package Listener;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Entity.Category;
import Panel.CategoryPanel;
import Service.CategoryService;

public class CategoryListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CategoryPanel p = CategoryPanel.instance;
		JButton b = (JButton)e.getSource();
		
		if(b == p.bAdd) {
			String name = JOptionPane.showInputDialog(null);
			if(null == name) {
				return;
			}
			if(0==name.length()) {
				JOptionPane.showMessageDialog(p, "�������Ʋ���Ϊ��");
				return;
			}
			new CategoryService().add(name);		
		}
		
		if(b == p.bEdit) {
			Category c = p.getSelectedCateagory();
			int id = c.id;
			String name = JOptionPane.showInputDialog("�޸ķ�������",c.name);
			if(null == name) {
				return;
			}
			if(0==name.length()) {
				JOptionPane.showMessageDialog(p, "�������Ʋ���Ϊ��");
				return;
			}
			new CategoryService().update(id, name);
		}
		
		if(b == p.bDelete) {
			Category c = p.getSelectedCateagory();
			if(0 != c.recordNumber) {
				JOptionPane.showMessageDialog(p, "�������������Ѽ�¼���ڣ�����ɾ��");
				return;
			}
			if(JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "ȷ��Ҫɾ��")) {
				return;
			}
			
			int id = c.id;
			new CategoryService().delete(id);
		}
		
		p.updateData();
	}

}
