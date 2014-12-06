import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class UserLoginDig {
    private String name   = null;
    private String passwd = null;
    
	public UserLoginDig()
	{
		JFrame logFrame = new JFrame();
		logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logFrame.setVisible(false);
		logFrame.setBounds(250,250,400,300);   
        
		JTextField username = new JTextField(10);
		JPasswordField password = new JPasswordField(10);         
		JPanel pan = new JPanel();        
        
		Box box = new Box(BoxLayout.Y_AXIS);
        JPanel u_pan = new JPanel();
        u_pan.add(new JLabel("User Name",JLabel.RIGHT));
        u_pan.add(username);
        box.add(u_pan);
        
        u_pan = new JPanel();
        u_pan.add(new JLabel(" Password",JLabel.RIGHT));
        u_pan.add(password);
        box.add(u_pan);
        pan.add(box);
        pan.setBorder(BorderFactory.createTitledBorder(
        		BorderFactory.createLineBorder(new Color(244,144,44)),
        		"User Authentication"));      

        Object[] options = {"Login","Cancel"};
        int ret = JOptionPane.showOptionDialog(null,pan,"CMPE207 Course Project",
        		JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
        if(ret != 0 )
        	System.exit(0);

        name = username.getText();
        passwd = new String(password.getPassword());
	}

	public String getPassword()
	{
		return passwd;
	}
	
	public String getUserName()
	{
		return name;
	}
}
