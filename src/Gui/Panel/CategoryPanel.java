package Gui.Panel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Entity.Category;
import Gui.Listener.CategoryListener;
import Gui.Model.CategoryTableModel;
import Service.CategoryService;
import Util.ColorUtil;
import Util.GUIUtil;

public class CategoryPanel extends WorkingPanel {
	
	public static CategoryPanel instance = new CategoryPanel();
	CategoryTableModel ctModel = new CategoryTableModel();
	public JTable categoryTable = new JTable(ctModel);
	
	public JButton bAdd = new JButton("新增");
	public JButton bEdit = new JButton("编辑");
	public JButton bDelete = new JButton("删除");
	
	private CategoryPanel() {
		this.setLayout(new BorderLayout());
		//设置JTable表头不可
		categoryTable.getTableHeader().setReorderingAllowed(false);
		GUIUtil.setColor(ColorUtil.blueColor, bAdd,bEdit,bDelete);
		
		this.add(center(),BorderLayout.CENTER);
		this.add(south(),BorderLayout.SOUTH);
		
		addListener();
	}
	
	//获取选择的类别
	public Category getSelectedCateagory() {
		int index = categoryTable.getSelectedRow();
		return ctModel.cs.get(index);
	}
	
	public void updateData() {
		ctModel.cs = new CategoryService().list();
		categoryTable.updateUI();
		categoryTable.getSelectionModel().setSelectionInterval(0, 0);
		
		if(0 == ctModel.cs.size()) {
			bEdit.setEnabled(false);
			bDelete.setEnabled(false);
		}
		else {
			bEdit.setEnabled(true);
			bDelete.setEnabled(true);
		}
		
	}
	
	public void addListener() {
		CategoryListener listener = new CategoryListener();
		
		bAdd.addActionListener(listener);
		bEdit.addActionListener(listener);
		bDelete.addActionListener(listener);
		updateData();
	}
	
	private JScrollPane center() {
		JScrollPane sp = new JScrollPane(categoryTable);
		return sp;
	}
	
	private JPanel south() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(bAdd);
		p.add(bEdit);
		p.add(bDelete);
		
		return p;
	}
	
}
