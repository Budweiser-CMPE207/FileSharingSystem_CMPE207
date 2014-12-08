/*  Emy Itegbe
 *  CMPE 207
 *  Group Budweiser
 *  This is the main driver file that
 *  calls the applications. it first authenticates the user 
 *  and upon accepting the request it permits the client to 
 *  use the text editor, save a file and upload the file
 *  to the server.
 * 
 */
  package cmpe_207;
 

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
//import javaClient;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ClientUploadGui {

	static CloseableHttpClient httpClient;
	static String phpURL = "http://emysdomain.com/";
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
		   
		HttpClientBuilder httpClientCreate = HttpClientBuilder.create();  
		httpClient = httpClientCreate.build();  
		
		LoginInfo login = new LoginInfo();
		if(!JavaHttpClient.Login(httpClient, login.getUserName(), login.getPassword(), phpURL))
		{
			JOptionPane.showMessageDialog(null, "Wrong Information Entered!!!","Error",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		JavaGUIFrame client = new JavaGUIFrame();
		client.setVisible(true);
			}
		});
}
}

