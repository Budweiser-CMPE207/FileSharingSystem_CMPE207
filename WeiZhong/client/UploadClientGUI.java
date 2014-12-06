import javax.swing.JOptionPane;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;



public class UploadClientGUI {

	static String phpURL = "http://localhost/php_test/";	

	//static String phpURL = "http://weizhongsjsu.com/";
	public static void main(String[] args) {
		CloseableHttpClient httpClient = null;  
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
		httpClient = httpClientBuilder.build();  
		
//		UserLoginDig login = new UserLoginDig();
//		if(!UploadClient.auth(httpClient, login.getUserName(), login.getPassword(), phpURL))
//		{
//			JOptionPane.showMessageDialog(null, "User authentication fail!");
//			System.exit(0);
//		}
		
        Thread thread=new Thread(new MainProcessWindow(httpClient, phpURL));  
        thread.start();  
	}

}
