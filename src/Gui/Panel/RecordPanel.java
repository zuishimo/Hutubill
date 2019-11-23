package Gui.Panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import Entity.Category;
import Gui.Listener.RecordListener;
import Gui.Model.CategoryComboBoxModel;
import Service.CategoryService;
import Util.ColorUtil;
import Util.GUIUtil;

public class RecordPanel extends WorkingPanel {
	
	public static RecordPanel instance = new RecordPanel();
	
	public JLabel lSpend = new JLabel("花费");
	public JLabel lCategory = new JLabel("分类");
	public JLabel lComment = new JLabel("备注");
	public JLabel lDate = new JLabel("日期");
	
	public JTextField tfSpend = new JTextField("0");
	public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
	public JComboBox<Category> cbCategory = new JComboBox<Category>(cbModel);
	public JTextField tfComment = new JTextField();
	public JXDatePicker datePicker = new JXDatePicker(new Date());
	
	public JButton bSubmit = new JButton("记一笔");
	
	private RecordPanel() {
		this.setLayout(new BorderLayout());
		
		GUIUtil.setColor(ColorUtil.grayColor, lSpend,lCategory,lComment,lDate);
		GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
		
		this.add(center(),BorderLayout.CENTER);
		this.add(south(),BorderLayout.SOUTH);
		
		addListener();
	}
	
	private JPanel center() {
		JPanel p = new JPanel();
		int gap = 60;
		p.setLayout(new GridLayout(4,2,gap,gap));
		
		p.add(lSpend);
		p.add(tfSpend);
		p.add(lCategory);
		p.add(cbCategory);
		p.add(lComment);
		p.add(tfComment);
		p.add(lDate);
		p.add(datePicker);
		
		return p;
	}
	
	private JPanel south() {
		JPanel p = new JPanel();
		p.add(bSubmit);
		return p;
	}
	
	public Category getSelectedCategory() {
		
		return (Category) cbCategory.getSelectedItem();
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		cbModel.cs = new CategoryService().list();
		cbCategory.updateUI();
		resetInput();
		tfSpend.grabFocus();
	}

	public void resetInput() {
		tfSpend.setText("0");
		tfComment.setText("");
		if(0 != cbModel.cs.size()) {
			cbCategory.setSelectedIndex(0);
		}
		datePicker.setDate(new Date());
	}
	
	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		RecordListener listener = new RecordListener();
		bSubmit.addActionListener(listener);
	}
	
}
