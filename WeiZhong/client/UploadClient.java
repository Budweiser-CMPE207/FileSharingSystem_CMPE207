
import java.io.*;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;  
import org.apache.http.impl.client.CloseableHttpClient;


public class UploadClient {
    private static String login_sessionid = null;
	
    public static boolean uploadFile(CloseableHttpClient httpClient, File file, String uploadURL){
        HttpPost httppost      = null;
		HttpEntity resEntity   = null;
		HttpResponse response  = null;
		
		try {
		    httppost = new HttpPost(uploadURL+"upload.php");  
		    
		    MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		    multipartEntity.addTextBody("user", "Wei Zhong");
		    multipartEntity.addTextBody("session_id", login_sessionid);
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
    
    
    public static List<FileItem> getFileList(CloseableHttpClient httpClient, String uploadURL){
        HttpPost httppost      = null;
		HttpEntity resEntity   = null;
		HttpResponse response  = null;
		List<FileItem> fileList = new LinkedList<FileItem>();
		
		try {
		    httppost = new HttpPost(uploadURL+"listfile.php");  
		    
		    MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		    multipartEntity.addTextBody("user", "Wei Zhong");
		    multipartEntity.addTextBody("session_id", login_sessionid);
		    httppost.setEntity(multipartEntity.build());  
            response  = httpClient.execute(httppost);  
   
            resEntity = response.getEntity();
            if (resEntity != null)  {
                BufferedReader br = new BufferedReader(new InputStreamReader((resEntity.getContent())));
                String row;
                while ((row = br.readLine()) != null) {
                    String[] output = row.split(":");
                    if(output.length == 5)
                        fileList.add(new FileItem(output[0], output[1],output[2],output[3]+" KB",output[4]));
                }
                EntityUtils.consume(resEntity); 
            }
             
        }  
        catch(Exception e) {
        	e.printStackTrace();  
        }
		
		return fileList;
    }  
    
    public static boolean deleteFile(CloseableHttpClient httpClient, String id, String uploadURL){
        HttpPost httppost      = null;
		HttpEntity resEntity   = null;
		HttpResponse response  = null;
		
		try {
		    httppost = new HttpPost(uploadURL+"delete.php");  
		    
		    MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		    multipartEntity.addTextBody("id", id);
		    multipartEntity.addTextBody("session_id", login_sessionid);
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
    
    public static boolean auth(CloseableHttpClient httpClient, String username, String passwd, String uploadURL){
        HttpPost httppost      = null;
		HttpEntity resEntity   = null;
		HttpResponse response  = null;
		
		try {
		    httppost = new HttpPost(uploadURL+"login.php");  
		    
		    MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		    multipartEntity.addTextBody("username", username);
		    multipartEntity.addTextBody("password", passwd);
		    httppost.setEntity(multipartEntity.build());  
            response  = httpClient.execute(httppost);  
  
            resEntity = response.getEntity();
            if (resEntity != null)  {
                BufferedReader br = new BufferedReader(new InputStreamReader((resEntity.getContent())));
                String row;
                while ((row = br.readLine()) != null) {
                	String[] output = row.split(":");
                	if(output.length == 2 && output[0].equals("Success_user")){
                		login_sessionid = output[1];
                		return true;
                	}
                }
                EntityUtils.consume(resEntity); 
            }
        }  
        catch(Exception e) {
        	e.printStackTrace();  
        	return false;
        }

		return false;
    }  
}