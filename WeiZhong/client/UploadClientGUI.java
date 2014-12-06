

public class UploadClientGUI {

	//static String phpURL = "http://localhost/php_test/";	
	
	static String phpURL = "http://weizhongsjsu.com/";
	public static void main(String[] args) {

        Thread thread=new Thread(new MainProcessWindow(phpURL));  
        thread.start();  
	}

}
