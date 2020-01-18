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

//ͼ�ν���Ĺ�����
public class GUIUtil {

	//У������������Ƿ�Ϊ��
	public static boolean checkEmpty(JTextField tf,String input) {
		String text = tf.getText().trim();
		if(0 == text.length()) {
			JOptionPane.showMessageDialog(null, input + " ����Ϊ��");
			return true;
		}
		return false;
	}
	
	//У������������Ƿ������ָ�ʽ
	public static boolean checkNumber(JTextField tf,String input) {
		//���Ϊ�գ�����true,ִ������Ĵ���,��������,�����Ƿ�Ϊ�����ж�
		if(checkEmpty(tf, input))
			return false;
		String text = tf.getText().trim();
		try {
			//��������֣������ɹ�������true
			Integer.parseInt(tf.getText());
			return true;
		} catch (NumberFormatException e) {
			//����������֣��׳��쳣������false
			JOptionPane.showMessageDialog(null, input + " ��Ҫ������");
			tf.grabFocus();
			return false;
		}
	}
	
	//У������������Ƿ�Ϊ����
	public static boolean checkNagative(JTextField tf,String input) {
		//��������֣������Ƿ�Ϊ�����ж�
		if(checkNumber(tf, input)) {
			int num = Integer.parseInt(tf.getText());
			if(num<0) {
				JOptionPane.showMessageDialog(null, input + "����Ϊ����");
				tf.grabFocus();
				return true;
			}
			else
				return false;
		}
		
		return false;
	}
	
	//У������������Ƿ�Ϊ��
	public static boolean checkZero(JTextField tf,String input) {
		//���и�ʽ�жϣ��Ƿ�Ϊ����,��������֣����������Ž����Ƿ�Ϊ���жϣ�����ִ����������Ĵ���
		if(!checkNumber(tf, input)) {
			return false;
		}
		String text = tf.getText().trim();
		if(0 == Integer.parseInt(text)) {
			JOptionPane.showMessageDialog(null, input + " ����Ϊ��");
			tf.grabFocus();
			return true;
		}
		if(checkNagative(tf, input)) {
			return true;
		}
		return false;
	}
	
	//������������ǰ��ɫ
	public static void setColor(Color color,JComponent... cs) {
		for (JComponent c : cs) {
			c.setForeground(color);
		}
	}
	
	//����ť����ͼ��������Լ���ʾ����
	public static void setImageIcon(JButton b,String fileName,String tip) {
		ImageIcon i = new ImageIcon(new File(fileName).getAbsolutePath());
		b.setIcon(i);
		b.setPreferredSize(new Dimension(61,81));
		b.setToolTipText(tip);
		b.setVerticalTextPosition(JButton.BOTTOM);
		b.setHorizontalTextPosition(JButton.CENTER);
		b.setText(tip);
	}
	
	//ʹ��ˮ��Ƥ��
	public static void useLNF() {
		try {
			javax.swing.UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//������ʾ�������
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
