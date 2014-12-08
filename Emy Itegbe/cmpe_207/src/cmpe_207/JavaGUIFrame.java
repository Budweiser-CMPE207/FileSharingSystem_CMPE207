package cmpe_207;
/*
 * Created by Emy Itegbe
 * CMPE 207
 * Group: Budweiser
 *  This file creates the frame for the GUI text Editor with 
 *  additional features of uploading a file as well viewing 
 *  the files stored on the server.
 */

import javax.swing.JFrame;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class JavaGUIFrame extends JFrame{
	
	private JavaGUIEditor panel;
	static String phpURL = "http://emysdomain.com/";
	
	//constructor
	public JavaGUIFrame()
	{
		CloseableHttpClient httpClient = null;  
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
		httpClient = httpClientBuilder.build();  
		
		panel = new JavaGUIEditor(httpClient,phpURL );
		
		setSize(800,590);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(panel);
		pack();
	}

}