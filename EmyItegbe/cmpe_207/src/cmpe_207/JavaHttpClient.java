/* This class sets up the method to upload file
 * as well as delete the file by name and id
 * Also it returns the list and displays the files 
 * on the frame from what has been stored on the database
 * 
 * Created By Emy Itegbe
 * Group Budweiser.
 * CMPE 207 Project
 * 12/08/2014.
 * 
**/
package cmpe_207;

import java.io.*;

import java.util.ArrayList;

//external Libraries.
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;  
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class JavaHttpClient {

	static final int LENGTH = 5;
	  //constructor..
	  public JavaHttpClient(){}
	  
    //uploads the file 
    public static boolean uploadFile(CloseableHttpClient httpClient, File filename, String urlName)
    {
    	HttpPost http_Upload;
  		HttpEntity resEntity;
  		HttpResponse response;
  		
  		httpClient = HttpClients.createDefault();
  		
		try {
		    http_Upload = new HttpPost(urlName+"upload.php");  //uploads the file to the php file 
		    
		    MultipartEntityBuilder entityBuild = MultipartEntityBuilder.create();
		    entityBuild.addTextBody("user", "Emy Itegbe");
		    entityBuild.addBinaryBody("file", filename, ContentType.APPLICATION_OCTET_STREAM, filename.getName());
		    http_Upload.setEntity(entityBuild.build());  
            response  = httpClient.execute(http_Upload);  
  
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
    
    
    public static ArrayList<DBInfo> getFileList(CloseableHttpClient httpClient, String urlName)
    {
        HttpPost http_Upload;    
		HttpEntity res_Entity;   
		HttpResponse response;  
		
		httpClient = HttpClients.createDefault();
		
		ArrayList<DBInfo> dbinfo = new ArrayList<DBInfo>();
		
		try {
			http_Upload = new HttpPost(urlName+"data.php");  
		    
		    MultipartEntityBuilder entityBuild = MultipartEntityBuilder.create();
		    entityBuild.addTextBody("list", "Emy Itegbe");
		    http_Upload.setEntity(entityBuild.build());  
            response  = httpClient.execute(http_Upload);  
   
            res_Entity = response.getEntity();
            if (res_Entity != null)  {
                BufferedReader br = new BufferedReader(new InputStreamReader((res_Entity.getContent())));
                String line;
                while ((line = br.readLine()) != null)
                {
                    String[] dblist = line.split(":");
                    if(dblist.length == LENGTH)
                        dbinfo.add(new DBInfo(dblist[0], dblist[1],dblist[2],dblist[3],dblist[4]));
                }
                EntityUtils.consume(res_Entity); 
            }
             
        }  
        catch(Exception e) {
        	e.printStackTrace();  
        }
		
		return dbinfo;
    }  
 
    //authenticates the Login process 
    //prompts user to enter user name and password and verifies with database.
    public static boolean Login(CloseableHttpClient httpClient, String username, String passwd, String urlName){
        HttpPost http_post      = null;
		HttpEntity res_Entity   = null;
		HttpResponse response  = null;
		BufferedReader reader = null;
		
		try {
		    http_post = new HttpPost(urlName+"loginInfo.php");  //information is verified with loginInfo.php
		    
		    MultipartEntityBuilder entityBuild = MultipartEntityBuilder.create();
		    entityBuild.addTextBody("username", username);
		    entityBuild.addTextBody("password", passwd);
		    http_post.setEntity(entityBuild.build());  
            response  = httpClient.execute(http_post);  
  
            res_Entity = response.getEntity();
            if (res_Entity != null)  {
                reader = new BufferedReader(new InputStreamReader((res_Entity.getContent())));
                String line;
                while ((line = reader.readLine()) != null) {
                	if(line.equals("Success_user"))
                		return true;
                }
                EntityUtils.consume(res_Entity); 
            }
        }  
        catch(Exception e) {
        	e.printStackTrace();  
        	return false;
        }

		return false;
    }  
    
    //method deletes the requested file on the server by using the id
    public static boolean deleteFileById(CloseableHttpClient httpClient, String id, String uploadURL){
        HttpPost httppost      = null;
		HttpEntity resEntity   = null;
		HttpResponse response  = null;
		httpClient = HttpClients.createDefault();
		
		try {
		    httppost = new HttpPost(uploadURL+"delete.php");  //processes requested service to delete.php
		    
		    MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		    multipartEntity.addTextBody("id", id);
		    httppost.setEntity(multipartEntity.build());  
            response  = httpClient.execute(httppost);  
  
            resEntity = response.getEntity();
            if (resEntity != null)  
                EntityUtils.consume(resEntity);  
            
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode <= HttpStatus.SC_TEMPORARY_REDIRECT 
            	&& statusCode >= HttpStatus.SC_OK) 
            {  
                return true;
            }  
        }  
        catch(IOException e) {
        	e.printStackTrace();  
        	return false;
        }

		return false;
    }  
    
    //method deletes file name on the server
    public static boolean deleteFileByName(CloseableHttpClient httpClient, String name, String uploadURL){
        HttpPost httppost      = null;
		HttpEntity resEntity   = null;
		HttpResponse response  = null;
		httpClient = HttpClients.createDefault();
		
		try {
		    httppost = new HttpPost(uploadURL+"deletename.php");  
		    
		    MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		    multipartEntity.addTextBody("name", name);
		    httppost.setEntity(multipartEntity.build());  
            response  = httpClient.execute(httppost);  
  
            resEntity = response.getEntity();
            if (resEntity != null)  
                EntityUtils.consume(resEntity);  
            
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode <= HttpStatus.SC_TEMPORARY_REDIRECT 
            	&& statusCode >= HttpStatus.SC_OK) 
            {  
                return true;
            }  
        }  
        catch(IOException e) {
        	e.printStackTrace();  
        	return false;
        }

		return false;
    }  
}
