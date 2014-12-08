/*
 * Emy Itegbe
 * Group Budweiser
 * CMPE 207
 * 008740953
 * This file creates the panel for the table as well
 * as the layout. It stores the information of the Database
 * file in arrayList and designs the layout.
 */
package cmpe_207;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class DBDesignPanel extends JPanel {

    private DBList db;

	public DBDesignPanel(ArrayList<DBInfo> table)
	{
		//super();;
		setMinimumSize(new Dimension(700,500));
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		Box sideStuff = Box.createHorizontalBox();
		sideStuff.add(Box.createHorizontalGlue());
		sideStuff.add(Box.createHorizontalStrut(5));
		sideStuff.add(Box.createHorizontalGlue());
		add(sideStuff);

		db = new DBList(table);
		add(db);
	}
}
