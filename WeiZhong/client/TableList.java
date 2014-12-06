
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class TableList extends JPanel{
	private static Font itemFont = new Font("Arial Narrow",Font.PLAIN,15);
	private static Font headerFont = new Font("Arial Narrow",Font.BOLD,14);
	private static Color color = new Color(45,61,78);

	@SuppressWarnings("null")
	public TableList(List<FileItem> itemList)
	{
		super();
	
		setOpaque(false);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		
		String[] titles = {FileItem.IDNAME, FileItem.FILENAME, FileItem.SIZE, FileItem.OWNER, FileItem.TYPE};

		
		if(itemList.isEmpty())		
			itemList.add(new FileItem("","","","",""));
		
		Object[][] fileInfo = new Object[itemList.size()][5]; 		
		for(int i=0;i<itemList.size();i++)
		{
			FileItem item = itemList.get(i);
			Object[] row = new String[5];
			row[0] = item.getID();
			row[1] = item.getFileName();
			row[2] = item.getSize();
			row[3] = item.getOwner();
			row[4] = item.getType();
			fileInfo[i] = row;
		}
		
		JTable table = new JTable(fileInfo,titles);
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		table.setFont(itemFont);
		table.setForeground(color);
		table.getTableHeader().setFont(headerFont);
		table.setBorder(null);

		JScrollPane pane = new JScrollPane(table);
		pane.getViewport().setBackground(Color.white);
		pane.getViewport().setOpaque(false);
		pane.getViewport().setBorder(null);
		pane.setOpaque(false);
		pane.setBackground(Color.white);
		this.add(Box.createHorizontalStrut(15));
		this.add(pane);
		
		this.add(Box.createHorizontalStrut(15));
	}
	
}
