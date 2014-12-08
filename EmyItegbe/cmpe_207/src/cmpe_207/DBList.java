/*
 *  Created By Emy Itegbe
 *  Group Budweier
 *  CMPE 207
 *  008740953
 */
package cmpe_207;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class DBList extends JPanel{
	
	public DBList(ArrayList<DBInfo> arrayList)
	{
		//sets the font for the headers.
		Font font = new Font("Courier", Font.BOLD+Font.ITALIC,13);
	
	       this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
	      

		//creates the title for the list of files in the database.
		String[] titles = {"File ID", "File Name", "File Size in (KB)", "File Created By","File Type Content"};

		//if array is Empty add pass FilesDataInfo 
		if(arrayList.isEmpty())		
			arrayList.add(new DBInfo("","","","",""));

		String[][] dbArray = new String[arrayList.size()][]; 		
		for(int j=0;j<arrayList.size();j++)
		{
			DBInfo arrayFiles = arrayList.get(j);
			String[] array = new String[5];
			array[0] = arrayFiles.getIdNo();
			array[1] = arrayFiles.getFileName();
			array[2] = arrayFiles.getSizeOfFile();
			array[3] = arrayFiles.getFileCreator();
			array[4] = arrayFiles.getFileType();
			dbArray[j] = array;
		}
		
		//creates a table to store the file info from server
		JTable fileTable = new JTable(dbArray,titles);
		fileTable.getTableHeader().setFont(font);
		fileTable.setBorder(BorderFactory.createLineBorder(Color.RED,1));
		fileTable.setEnabled(true);
		//fileTable.setCellSelectionEnabled(true);
		fileTable.setShowGrid(true);
		fileTable.setShowHorizontalLines(true);
		fileTable.setShowVerticalLines(true);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		fileTable.getColumnModel().getColumn(3).setCellRenderer(r);
	
		//creates the scrollpane for the table
		JScrollPane scrollpane = new JScrollPane(fileTable);
		scrollpane.getVerticalScrollBar().setUnitIncrement(10);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(Box.createHorizontalStrut(15));
		this.add(scrollpane);
}
	
}