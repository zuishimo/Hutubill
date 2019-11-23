package Gui.Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import Entity.Category;
import Service.CategoryService;

public class CategoryComboBoxModel implements ComboBoxModel<Category> {
	
	public List<Category> cs = new CategoryService().list();
	
	public Category c;
	
	public CategoryComboBoxModel(){
		if(!cs.isEmpty()) {
			c = cs.get(0);
		}
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return cs.size();
	}

	@Override
	public Category getElementAt(int index) {
		// TODO Auto-generated method stub
		return cs.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectedItem(Object anItem) {
		// TODO Auto-generated method stub
		c = (Category)anItem;
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		if(!cs.isEmpty()) {
			return c;
		}
		else {
			return null;
		}
	}

	
}
