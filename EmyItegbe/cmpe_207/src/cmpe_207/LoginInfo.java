/*  This class set ups the login info
 *	Also creates the frame which prompts the 
 *  user to enter their user information to 
 *  access the text editor and upload the file to
 *  Browser.
 *  
*/
package cmpe_207;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class LoginInfo extends JFrame {
	
	//variable declarations.
    private String userName;
    private String identifier;
    private JTextField fieldName;
    private JPasswordField psswd;
    private JPanel panel, panel1, panel2;
    
    //constructor and initializes the frame and layout
	public LoginInfo()
	{
	    this.fieldName = new JTextField(15);
		this.psswd = new JPasswordField(15);         
	    panel = new JPanel(new BorderLayout(5,5));    
		
		panel1 = new JPanel(new GridLayout(0, 1, 4, 4));
	    panel1.add(new JLabel("Username", SwingConstants.RIGHT));
	    panel1.add(new JLabel("Password", SwingConstants.RIGHT));
	    panel.add(panel1, BorderLayout.WEST);
	    
	    panel2 = new JPanel(new GridLayout(0, 1, 2, 2));
	    panel2.add(this.fieldName);
	    panel2.add(this.psswd);
	    panel.add(panel2, BorderLayout.CENTER);
		
        //windows prompting user information.
        JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION);

        this.userName = fieldName.getText();
        this.identifier = new String(psswd.getPassword());
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	//returns the password and user name
	public String getPassword()
	{
		return this.identifier;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
}
