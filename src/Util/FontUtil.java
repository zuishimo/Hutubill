package Util;

import java.awt.Font;

import javax.swing.JComponent;

public class FontUtil {

	public static void setFont(JComponent... cs) {
		for (JComponent jComponent : cs) {
			jComponent.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,23));
		}
	}
}
