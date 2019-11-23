package Gui.Panel;
 
import javax.swing.JButton;
import javax.swing.JPanel;
 
import Util.ColorUtil;
import Util.GUIUtil;
 
public class RecoverPanel extends JPanel {

    public static RecoverPanel instance = new RecoverPanel();
 
    public JButton bRecover =new JButton("»Ö¸´");
 
    private RecoverPanel() {
 
        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        this.add(bRecover);
         
    }
 
}