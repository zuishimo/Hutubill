package Panel;
 
import javax.swing.JButton;


import javax.swing.JPanel;

import Listener.RecoverListener;
import Util.ColorUtil;
import Util.GUIUtil;
 
public class RecoverPanel extends WorkingPanel {

    public static RecoverPanel instance = new RecoverPanel();
 
    public JButton bRecover =new JButton("»Ö¸´");
 
    private RecoverPanel() {
 
        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        this.add(bRecover);
        addListener(); 
    }

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		RecoverListener listener = new RecoverListener();
		bRecover.addActionListener(listener);
	}
 
}