
/*CMPE 207 wei zhong 009433424 -- text client*/


import java.io.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


public class UploadClientTerminal {
	private static CloseableHttpClient httpClient = null;  
	
    public static void main(String[] args){	
		String userInput;
		File   userFile = null; 	
		String phpURL = "http://localhost/php_test/upload.php";	

		try 
		{
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
			httpClient = httpClientBuilder.build();  
	
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please input the name of file you want to upload: ");
			
	        while ((userInput = stdIn.readLine()) != null) {	    
				if(userInput.equals("QUIT")) 
	                break;

 	            String[] fileList = userInput.split(" ");
 			    for(int i=0; i<fileList.length; i++) {
 				    userFile = new File(fileList[i]);
 				    if(!userFile.exists()) {
 					    System.out.println("input error: file "+ fileList[i] +" not exists, skip it.");
 					    continue;
 				    }

					if(UploadClient.uploadFile(httpClient, userFile, phpURL))
		                System.out.println("file \""+ fileList[i] +"\"("+userFile.length()+"bytes) has been transmitted to server.");
                    else
		                System.out.println("error in uploading file \""+ fileList[i] +"\"("+userFile.length()+"bytes), skip it.");
				}
				  
			    System.out.println("\nPlease input the name of file you want to upload: ");
	        }  
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: localhost. " + e);
		}
		catch (Exception e) {
			System.err.println("Exception in client: " + e);
		}

		
		try {
			httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  

	}
}