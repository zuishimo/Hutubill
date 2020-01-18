package StartUp;

import javax.swing.SwingUtilities;

import Frame.MainFrame;
import Panel.MainPanel;
import Panel.SpendPanel;
import Util.GUIUtil;

public class BootStarp {

	public static void main(String[] args) throws Exception {
		
		GUIUtil.useLNF();
		
		SwingUtilities.invokeAndWait (new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainFrame.instance.setVisible(true);
				MainPanel.instance.workingPanel.show(SpendPanel.instance);
			}
		} );
		
	}
	
}
