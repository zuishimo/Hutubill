package Panel;

import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import Listener.BackupListener;
import Util.ColorUtil;
import Util.GUIUtil;

public class BackupPanel extends WorkingPanel{
	
	public static BackupPanel instance = new BackupPanel();

	public JButton bBackup = new JButton("±¸·Ý");
	
	private BackupPanel() {
		this.setLayout(new BorderLayout());
		
		GUIUtil.setColor(ColorUtil.blueColor, bBackup);
		this.add(center(),BorderLayout.CENTER);
		addListener();
	}

	private JPanel center() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(bBackup);
		return p;
	}


	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		BackupListener listener = new BackupListener();
		bBackup.addActionListener(listener);
	}
}
