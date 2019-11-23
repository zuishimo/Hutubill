package Gui.Panel;

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

import Util.ColorUtil;
import Util.GUIUtil;

public class BackupPanel extends JPanel{
	
	public static BackupPanel instance = new BackupPanel();
	
	public String dirPath;
	public JButton bSavePath = new JButton("保存位置");
	public JButton bBackup = new JButton("备份");
	public JFileChooser fc = new JFileChooser();
	
	//进行文件筛选,允许选择文件夹
	public FileFilter dirFilter = new FileFilter() {
		
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "文件夹";
		}
		
		@Override
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			return f.isDirectory();
		}
	};
	
	
	private BackupPanel() {
		this.setLayout(new BorderLayout());
		
		GUIUtil.setColor(ColorUtil.blueColor, bBackup,bSavePath);
		this.add(north(),BorderLayout.NORTH);
		this.add(center(),BorderLayout.CENTER);
		
		fc.setFileFilter(dirFilter);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		bSavePath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int returnVal = fc.showSaveDialog(bSavePath);
				File f = fc.getSelectedFile();
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(bSavePath, "计划将文件保存到：" + f.getAbsolutePath());
					dirPath = fc.getSelectedFile().getAbsolutePath();
				}
			}
		});
	}
	
	
	private JPanel north() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(bSavePath);
		return p;
	}
	
	private JPanel center() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(bBackup);
		return p;
	}
	
	public static void main(String[] args) {
		GUIUtil.showPanel(BackupPanel.instance);
	}
}
