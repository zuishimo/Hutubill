package Util;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Panel.WorkingPanel;

public class CenterPanel extends JPanel {

	private double rate;       //拉伸比例
	private JComponent c;        //显示的的组件
	private boolean stretch;      //是否拉伸
	
	public CenterPanel(double rate,boolean stretch) {
		this.setLayout(null);
		this.rate = rate;
		this.stretch = stretch;
	}
	
	public CenterPanel(double rate) {
		this(rate,true);
	}
	
	
	public void show(JComponent p) {
		this.c = p;
		Component cs[] = getComponents();
		for (Component c : cs) {
			remove(c);         //继承自JPanel，可以直接调用
		}
		add(p);
		
		if(p instanceof WorkingPanel) {
		
			((WorkingPanel)p).updateData();
		}
		
		this.updateUI();
	}
	
	public void repaint() {
		if(null !=c) {
			Dimension containerSize = this.getSize();
			Dimension componentSize = c.getPreferredSize();
			
			if(stretch)
				c.setSize((int)(containerSize.width*rate),(int)(containerSize.height*rate));
			else
				c.setSize(componentSize);
			
			c.setLocation(containerSize.width/2 - c.getSize().width/2,containerSize.height/2 - c.getSize().height/2);
		}
		super.repaint();
	}

}
