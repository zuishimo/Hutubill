package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import Panel.BackupPanel;
import Panel.ConfigPanel;
import Panel.MainPanel;
import Panel.RecoverPanel;
import Service.ConfigService;
import Util.MysqlUtil;

public class RecoverListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		RecoverPanel p = RecoverPanel.instance;
		String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
		File fOpen = new File(mysqlPath + "/备份位置.txt");
		String str = null;
		
		//判断保存位置是否存在
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(fOpen));
			char path[] = new char[(int)fOpen.length()];
			reader.read(path);
			reader.close();
			str = new String(path);
		} catch (FileNotFoundException e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(p, "你还没有备份,请先进行备份");
			MainPanel.instance.workingPanel.show(BackupPanel.instance);
		} catch (IOException e2) {
			// TODO: handle exception
		}

		File savePath = new File(str.replace("\\", "/"));
		if(!savePath.exists()) {
			JOptionPane.showMessageDialog(p, "你还没有备份,请先进行备份");
			MainPanel.instance.workingPanel.show(BackupPanel.instance);
			return;
		}
		
		JFileChooser fc = new JFileChooser();
//		fc.setSelectedFile(new File("hutubill.sql"));
		fc.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return ".sql";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				return f.isDirectory()|f.isFile();
			}
		});
		
		int returnVal = fc.showOpenDialog(p);
		File f = fc.getSelectedFile();
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				MysqlUtil.recover(mysqlPath, f.getAbsolutePath());
				JOptionPane.showMessageDialog(p, "恢复成功");
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				JOptionPane.showMessageDialog(p, e2.getMessage());
			}
			
		}
		
		
		
		
		
	}

}
