
import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class FileTablePanel extends JPanel {

	public boolean autoComplete = false;
    private TableList curTable;

	public FileTablePanel(List<FileItem> itemList)
	{
		super();
		setOpaque(false);
		this.setMinimumSize(new Dimension(700,500));
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		Box sideStuff = Box.createHorizontalBox();
		sideStuff.add(Box.createHorizontalGlue());
		sideStuff.add(Box.createHorizontalStrut(5));
		sideStuff.add(Box.createHorizontalGlue());
		this.add(sideStuff);

		curTable = (new TableList(itemList));
		this.add(curTable);
	}

}
