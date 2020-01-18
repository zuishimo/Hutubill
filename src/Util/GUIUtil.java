package Util;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.birosoft.liquid.util.Panel;

//图形界面的工具类
public class GUIUtil {

	//校验输入框内容是否为空
	public static boolean checkEmpty(JTextField tf,String input) {
		String text = tf.getText().trim();
		if(0 == text.length()) {
			JOptionPane.showMessageDialog(null, input + " 不能为空");
			return true;
		}
		return false;
	}
	
	//校验输入框内容是否是数字格式
	public static boolean checkNumber(JTextField tf,String input) {
		//如果为空，返回true,执行里面的代码,否则跳过,进行是否为整数判断
		if(checkEmpty(tf, input))
			return false;
		String text = tf.getText().trim();
		try {
			//如果是数字，解析成功，返回true
			Integer.parseInt(tf.getText());
			return true;
		} catch (NumberFormatException e) {
			//如果不是数字，抛出异常，返回false
			JOptionPane.showMessageDialog(null, input + " 需要是整数");
			tf.grabFocus();
			return false;
		}
	}
	
	//校验输入框内容是否为负数
	public static boolean checkNagative(JTextField tf,String input) {
		//如果是数字，进行是否为负数判断
		if(checkNumber(tf, input)) {
			int num = Integer.parseInt(tf.getText());
			if(num<0) {
				JOptionPane.showMessageDialog(null, input + "不能为负数");
				tf.grabFocus();
				return true;
			}
			else
				return false;
		}
		
		return false;
	}
	
	//校验输入框内容是否为零
	public static boolean checkZero(JTextField tf,String input) {
		//进行格式判断，是否为数字,如果是数字，跳过，接着进行是否为零判断；否则执行条件里面的代码
		if(!checkNumber(tf, input)) {
			return false;
		}
		String text = tf.getText().trim();
		if(0 == Integer.parseInt(text)) {
			JOptionPane.showMessageDialog(null, input + " 不能为零");
			tf.grabFocus();
			return true;
		}
		if(checkNagative(tf, input)) {
			return true;
		}
		return false;
	}
	
	//给多个组件设置前景色
	public static void setColor(Color color,JComponent... cs) {
		for (JComponent c : cs) {
			c.setForeground(color);
		}
	}
	
	//给按钮设置图标和文字以及提示文字
	public static void setImageIcon(JButton b,String fileName,String tip) {
		ImageIcon i = new ImageIcon(new File(fileName).getAbsolutePath());
		b.setIcon(i);
		b.setPreferredSize(new Dimension(61,81));
		b.setToolTipText(tip);
		b.setVerticalTextPosition(JButton.BOTTOM);
		b.setHorizontalTextPosition(JButton.CENTER);
		b.setText(tip);
	}
	
	//使用水晶皮肤
	public static void useLNF() {
		try {
			javax.swing.UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//快速显示面板内容
	public static void showPanel(JPanel p,double rate) {
		JFrame f = new JFrame();
		f.setSize(500,500);
		f.setLocationRelativeTo(null);
		CenterPanel cp = new CenterPanel(rate);
		f.setContentPane(cp);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		cp.show(p);
	}
	
	public static void showPanel(JPanel p) {
		showPanel(p,0.85);
	}
}
