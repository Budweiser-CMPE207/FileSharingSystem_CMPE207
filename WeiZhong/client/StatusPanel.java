
import java.awt.Color;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StatusPanel extends JPanel {

	public StatusPanel(String status)
	{
		super();
		setOpaque(false);
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		changeStatus(status);
	}

	public void changeStatus(String newStat)
	{
		Box boxy = Box.createHorizontalBox();
		
		Box vert = Box.createVerticalBox();
		this.removeAll();
		Color c = new Color(37,2,80);
		JLabel lab = new JLabel(newStat);
		lab.setForeground(c);
		vert.add(lab);
		
		boxy.add(Box.createHorizontalStrut(15));
		boxy.add(vert);
		boxy.add(Box.createGlue());
		this.add(boxy);
		
	}
}
