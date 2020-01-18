package Listener;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import Panel.BackupPanel;
import Panel.ConfigPanel;
import Panel.MainPanel;
import Service.ConfigService;
import Util.MysqlUtil;

public class BackupListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		BackupPanel p = BackupPanel.instance;
		
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		
		if(0==mysqlPath.length()) {
			JOptionPane.showMessageDialog(p, "备份前请事先配置MySQL的安装路径");
			MainPanel.instance.workingPanel.show(ConfigPanel.instance);
			ConfigPanel.instance.tfMysqlPath.grabFocus();
			return;
		}
		
		
		JFileChooser fc = new JFileChooser();	
		//进行文件筛选,允许选择文件夹
		FileFilter dirFilter = new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return ".sql";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				return f.isDirectory() | f.isFile();
			}
		};
		
		fc.setFileFilter(dirFilter);
		fc.setSelectedFile(new File("hutubill.sql"));
		
		int returnVal = fc.showSaveDialog(p);
		File f = fc.getSelectedFile();
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			if(!f.getName().toLowerCase().endsWith(".sql")) {
				f = new File(f.getParent(),f.getName() + ".sql");
				
			}
			try {
				MysqlUtil.backup(mysqlPath, f.getAbsolutePath());
				File saveFile = new File(mysqlPath + "/备份位置.txt");
				OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(saveFile));
				writer.write(f.getAbsolutePath());
				writer.flush();
				JOptionPane.showMessageDialog(p, "备份成功，备份文件位于: \r\n" + f.getAbsolutePath());
				writer.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				JOptionPane.showMessageDialog(p, "备份失败，错误:\r\n" + e2.getMessage());
			}
		}
	}
}
