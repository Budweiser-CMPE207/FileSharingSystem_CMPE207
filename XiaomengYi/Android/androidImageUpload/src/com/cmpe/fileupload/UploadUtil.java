package com.cmpe.fileupload;

import java.io.File;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

@SuppressWarnings("deprecation")
public class UploadUtil {
	private static UploadUtil uploadUtil;
	private UploadUtil() {

	}

	public static UploadUtil getInstance() {
		if (null == uploadUtil) {
			uploadUtil = new UploadUtil();
		}
		return uploadUtil;
	}

	private static final String TAG = "UploadUtil";
	private int readTimeOut = 10 * 1000; 
	private int connectTimeout = 10 * 1000; 

	private static int requestTime = 0;
	
	public static final int UPLOAD_SUCCESS_CODE = 1;

	public static final int UPLOAD_FILE_NOT_EXISTS_CODE = 2;

	public static final int UPLOAD_SERVER_ERROR_CODE = 3;
	protected static final int WHAT_TO_UPLOAD = 1;
	protected static final int WHAT_UPLOAD_DONE = 2;
	
	public void uploadFile(String filePath,  String RequestURL) {
		if (filePath == null) {
			sendMessage(UPLOAD_FILE_NOT_EXISTS_CODE,"文件不存在");
			return;
		}
		try {
			File file = new File(filePath);
			uploadFile(file,  RequestURL);
		} catch (Exception e) {
			sendMessage(UPLOAD_FILE_NOT_EXISTS_CODE,"文件不存在");
			e.printStackTrace();
			return;
		}
	}

	public void uploadFile(final File file,
			final String RequestURL) {
		if (file == null || (!file.exists())) {
			sendMessage(UPLOAD_FILE_NOT_EXISTS_CODE,"File Doesn't Exist");
			return;
		}

		Log.i(TAG, "Request URL=" + RequestURL);
		Log.i(TAG, "Request fileName=" + file.getName());
		new Thread(new Runnable() {  //开启线程上传文件
			@Override
			public void run() {
				toUploadFile(file, RequestURL);
			}
		}).start();
		
	}

	private void toUploadFile(File f,String RequestURL) {
		try {
			
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(RequestURL);
			MultipartEntity multiPart = new MultipartEntity();
			FileBody fileBody = new FileBody(f);

			
            HttpPost uploadFile = new HttpPost(RequestURL);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            multiPart.addPart("user", new StringBody("Xiaomeng From Android"));
            multiPart.addPart("key", new StringBody("Budweiser"));
            multiPart.addPart("file", fileBody);
            post.setEntity(multiPart);
            HttpResponse response = client.execute(post);
			HttpEntity resEntity = response.getEntity();		
			String result = EntityUtils.toString(resEntity);
            sendMessage(200,"Uploaded Successfully!");
            return;
     
		} catch (IOException e) {
			sendMessage(UPLOAD_SERVER_ERROR_CODE,"Upload Failed:error=" + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	private void sendMessage(int responseCode,String responseMessage)
	{
		onUploadProcessListener.onUploadDone(responseCode, responseMessage);
	}

	public static interface OnUploadProcessListener {

		void onUploadDone(int responseCode, String message);

		void onUploadProcess(int uploadSize);

		void initUpload(int fileSize);
	}
	private OnUploadProcessListener onUploadProcessListener;
	

	public void setOnUploadProcessListener(
			OnUploadProcessListener onUploadProcessListener) {
		this.onUploadProcessListener = onUploadProcessListener;
	}

	public int getReadTimeOut() {
		return readTimeOut;
	}

	public void setReadTimeOut(int readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public static int getRequestTime() {
		return requestTime;
	}
	
	public static interface uploadProcessListener{
		
	}
	
	public static String getPath(Context context, Uri uri) {

        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection,null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
  
            }
        }

        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
	
	
}
