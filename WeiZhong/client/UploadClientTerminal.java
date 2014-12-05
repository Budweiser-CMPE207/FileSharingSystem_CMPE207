
/*CMPE 207 wei zhong 009433424 -- text client*/


import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;  
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


public class UploadClientTerminal {
	private CloseableHttpClient httpClient = null;  

    private boolean uploadFile(String fileName, String uploadURL){
        HttpPost httppost      = null;
		HttpEntity resEntity   = null;
		HttpResponse response  = null;
		File file = new File(fileName);
		
		try {
		    httppost = new HttpPost(uploadURL);  
		    
		    MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		    multipartEntity.addTextBody("user", "Wei Zhong");
		    multipartEntity.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
		    httppost.setEntity(multipartEntity.build());  
            response  = httpClient.execute(httppost);  
  
            resEntity = response.getEntity();
            if (resEntity != null)  
                EntityUtils.consume(resEntity);  
            
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode <= HttpStatus.SC_TEMPORARY_REDIRECT 
            	&& statusCode >= HttpStatus.SC_OK) //SC_OK = 200  
            {  
                return true;
            }  
        }  
        catch(Exception e) {
        	e.printStackTrace();  
        	return false;
        }

		return false;
    }

	
    public static void main(String[] args){	
		String userInput;
		File   userFile = null; 	
		String phpURL = "http://localhost/php_test/upload.php";	

		UploadClientTerminal client = new UploadClientTerminal();
		try 
		{
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
			client.httpClient = httpClientBuilder.build();  
	
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

					if(client.uploadFile(fileList[i], phpURL))
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
			client.httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  

	}
}