package FileHttpClient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * Created by leituo56 on 11/7/14.
 */
public class HttpClientTuoLei {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String url = "http://tuolei.org/upload.php";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();

            System.out.println("Input your uploading file name, type 'quit' to exit!");
            String msg = br.readLine();
            while (!msg.equalsIgnoreCase("quit")){
                File file = new File(msg);
                if (!file.exists()) {//Create a File if there no such file name
                    String content = "This is a new file created at:" + System.currentTimeMillis();
                    file.createNewFile();
                    System.out.println("File not exist, create a file for you!!");
                    System.out.println(content);
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
                    bw.write(content);
                    bw.close();
                    System.out.println("Done");
                }else {
                    System.out.println("File found:" + file.getName());
                    System.out.println("Start Uploading!!!");

                    HttpPost uploadFile = new HttpPost(url);
                    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                    builder.addTextBody("user", "Tuo Lei JAVA");
                    builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
                    HttpEntity multipart = builder.build();
                    uploadFile.setEntity(multipart);
                    HttpResponse response = httpClient.execute(uploadFile);
                    HttpEntity responseEntity = response.getEntity();

                    System.out.println("##################################");
                    //System.out.println(response.toString());
                    BufferedReader br2 = new BufferedReader(
                            new InputStreamReader((responseEntity.getContent())));
                    String output;
                    while ((output = br2.readLine()) != null) {
                        System.out.println(output);
                    }
                    System.out.println("##################################");
                }
                System.out.println("Input your uploading file name, type 'quit' to exit!");
                msg = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
