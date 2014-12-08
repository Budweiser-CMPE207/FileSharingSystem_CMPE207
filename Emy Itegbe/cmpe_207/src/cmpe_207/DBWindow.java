/*  Created by Emy Itegbe
 *  Group Budweiser
 *  Cmpe 207
 *  008740953
 *  This file creates the panel layout for the database files
 *  as well as the function to delete the files from the
 *  database either by the file name or by the ID of the file.
 *  It passes the file name or ID of file to the JavaHttpClient.
 */

package cmpe_207;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.http.impl.client.CloseableHttpClient;

public class DBWindow extends JFrame {
	
private JPanel panel;
private Box box;
ArrayList<DBInfo> dbfiles;
CloseableHttpClient httpClient = null;  
static String phpURL = "http://emysdomain.com/";

//constructor
public DBWindow()
{
	super("Files in Database");
	initialize(httpClient, phpURL);
}

//initializes the frame.
private void initialize(CloseableHttpClient httpCLient, String phpURL)
{
	this.httpClient=httpCLient;
	this.phpURL = phpURL;
	dbfiles = new ArrayList<DBInfo>();
	
	TablePanel contain = new TablePanel(this);
	
    dbfiles = JavaHttpClient.getFileList(httpClient, phpURL);
		
	panel = new DBDesignPanel(dbfiles);
	box = Box.createHorizontalBox();
	box.add(panel);
	contain.add(box);
	this.add(contain);
	this.setVisible(true);
	this.setLocationRelativeTo(null);
	this.pack();
	this.setSize(800, 400);
}  

}
  
  class TablePanel extends JPanel implements ActionListener
  {
	  JButton button1, button2;
	  TablePanel(DBWindow frame)
	  {
		super();
		this.setPreferredSize(new Dimension(500, 400));
		JPanel panel = new JPanel();
		button1 = new JButton("Delete");
	    button1.addActionListener(this);
	    button2 = new JButton("Search");
	    panel.add(button1);
		this.setLayout(new BorderLayout());
		add(panel, BorderLayout.NORTH);
		this.setBackground(Color.white);
  }
	  //Listens to the button and deletes the file 
	  //either by a name or an ID.
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==button1)
	{
		int reply = JOptionPane.showConfirmDialog(null, "Do you want to delete a file", "Delete File", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	String[] options = new String[]{"File Name","ID"};
        	int response = JOptionPane.showOptionDialog(null, "Message", "Title",
        	        JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
        	        null, options, options[0]);
        
        	 if(response == 0)
        	{
        		String userInput = (String)JOptionPane.showInputDialog(
        				null,
        				"Enter the File Name",
        				"Input",
        				JOptionPane.PLAIN_MESSAGE,
        				null,
        				null,
        				null);
        		if(userInput==null){
        			JOptionPane.showMessageDialog(null,
    						"You did not input a file", "Error!",
    						JOptionPane.NO_OPTION);
        		}
        		CloseableHttpClient httpClient = null;
        		String phpURL = "http://emysdomain.com/";
        		JavaHttpClient.deleteFileByName(httpClient, userInput, phpURL);
        		repaint();
        		
        	}
        	else {
        		String Input = (String)JOptionPane.showInputDialog(
        				null,
        				"Enter the File's ID",
        				"Input",
        				JOptionPane.DEFAULT_OPTION,
        				null,
        				null,
        				null);
        		if(Input==null){
        			JOptionPane.showMessageDialog(null,
    						"You did not input an ID number", "Error!",
    						JOptionPane.NO_OPTION);
        		}
        		
        		CloseableHttpClient httpClient = null;
        		String phpURL = "http://emysdomain.com/";
	        		JavaHttpClient.deleteFileById(httpClient, Input, phpURL);
        		}
	        		
	        	}
	        
	        }
	        else {
	           return ;
	        }
		}
	}
  
  