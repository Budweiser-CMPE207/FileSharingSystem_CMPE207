
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


public class MainProcessWindow extends JFrame implements ActionListener, Runnable {

	private StatusPanel statusPane;
	private JPanel curWindow;
	private Box windowHolder;
	private static ConcurrentLinkedQueue<TaskType> taskQueue = new ConcurrentLinkedQueue<TaskType>();
	CloseableHttpClient httpClient = null;  
	static String phpURL = "http://localhost/php_test/upload.php";	
	List<FileItem> fileList = new LinkedList<FileItem>();
	
	public MainProcessWindow(CloseableHttpClient httpClient, String phpURL)
	{
		super("CMPE207 Course Project");
		this.setLocation(300,30);
		this.setFocusable(true);
		BackgroundPanel pan = new BackgroundPanel(this);
		
		this.requestFocusInWindow();
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
		this.setMinimumSize(new Dimension(700,700));

		Box v = Box.createVerticalBox();
		Box b = Box.createHorizontalBox();
		
		statusPane = new StatusPanel("Current File list as below:");
		b.add(statusPane);
		b.add(Box.createHorizontalGlue());
		
		JButton button = new JButton("Refresh Table");
		button.setActionCommand("refresh");
		button.addActionListener(this);
		b.add(button);

		b.add(Box.createHorizontalStrut(30));
		v.add(Box.createVerticalStrut(10));
		v.add(b);
		v.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray),
                v.getBorder()));
		pan.add(v);

		taskQueue.add(new TaskType(0));
		curWindow = new FileTablePanel(fileList);
		windowHolder = Box.createHorizontalBox();
		windowHolder.add(curWindow);
		pan.add(windowHolder);
		
		JButton but = new JButton("Upload File");
		but.addActionListener(this);
		but.setActionCommand("uploadfile");
		but.setMinimumSize(new Dimension(15,10));
		Box x = Box.createHorizontalBox();
		x.add(Box.createHorizontalStrut(15));
		x.add(but);
		x.add(Box.createGlue());
		
		but = new JButton("Upload Directory");
		but.addActionListener(this);
		but.setActionCommand("uploaddir");
		x.add(Box.createHorizontalStrut(15));
		x.add(but);
		x.add(Box.createGlue());
		
		but = new JButton("Delete a File");
		but.addActionListener(this);
		but.setActionCommand("delete");
		x.add(but);
		x.add(Box.createHorizontalStrut(60));
		
		Box another = Box.createHorizontalBox();
		another.add(Box.createHorizontalGlue());
		another.add(Box.createHorizontalGlue());
		pan.add(another);
		pan.add(Box.createVerticalStrut(10));
		
		pan.add(x);
		pan.add(Box.createVerticalStrut(10));
		this.add(pan);
		
		pack();
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		repaint();

		this.httpClient = httpClient;
		this.phpURL = phpURL;
	}

	@Override
	public void run() {
		TaskType task;
		
		while(true) {
		try {
		    if(taskQueue.isEmpty())
		    	Thread.sleep(100);
		    else {  	
		    	task = taskQueue.remove();
		    	if(task.getOp() == 0)
		    		refreshTable();
		    	if(task.getOp() == 1)
		    		uploadFiles(task.getFileHandle());	
		    	if(task.getOp() == 2)
		    		deleteFile(task.getID());	
		    }
			statusPane.changeStatus("Current File list as below: ");
			pack();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		File file = null;
		File dir;
		String id = null;
		int result = 0;
		
		String cmd = e.getActionCommand();
		if("refresh".equals(cmd)){
			//taskQueue.add(new TaskType(0));
		}
		else if("uploadfile".equals(cmd)){
			fileChooser.setApproveButtonText("OK");
			fileChooser.setDialogTitle("Open File");
			result = fileChooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION){
				file = fileChooser.getSelectedFile();
			}
			else return;
	
			taskQueue.add(new TaskType(1, file));
		}
		else if("uploaddir".equals(cmd)){
			fileChooser.setApproveButtonText("OK");
			fileChooser.setDialogTitle("Open Directory");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			result = fileChooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION){
				dir = fileChooser.getSelectedFile();
				if(!dir.isDirectory())
					return;
			}
			else return;
	
			taskQueue.add(new TaskType(1, dir));
		}
		else if("delete".equals(cmd)) {
		    id  = JOptionPane.showInputDialog("please choose id to delete file");
		    if(id != null && !id.isEmpty())
			    taskQueue.add(new TaskType(2, id));
		    else return;
		}
		
		taskQueue.add(new TaskType(0));	
	}
	
	public void refreshTable()
	{
		statusPane.changeStatus("refresh file list, please wait... ");
		pack();
		fileList = UploadClient.getFileList(httpClient, phpURL);
		windowHolder.remove(curWindow);
		curWindow = new FileTablePanel(fileList);
		windowHolder.add(curWindow);
	}
	
	public void uploadFiles(File file)
	{
		 if(!file.isDirectory()) {
			 statusPane.changeStatus("uploading file: "+file.getName());
			 pack();
			 if(!UploadClient.uploadFile(httpClient, file, phpURL)){
				 statusPane.changeStatus("uploading file: "+file.getName()+" fail!");
				 pack();
			 }	 
			 
		     return;
		 }
		 
		 File[] files = file.listFiles();
		 for(File file1: files){  
			 if(file1.isDirectory())
				 continue;
			 statusPane.changeStatus("uploading file: "+file1.getName());
			 pack();
			 if(!UploadClient.uploadFile(httpClient, file1, phpURL)){
				 statusPane.changeStatus("uploading file: "+file1.getName()+" fail!");
				 pack();
				 return;
			 }
		 }
	}
	
	public void deleteFile(String id)
	{
		String fileName = "none";
		for(FileItem file: fileList){
			if(file.getID() == id)
				fileName = file.getFileName();
		}
		statusPane.changeStatus("delete file "+ fileName +" associated with id "+id);
		pack();
		UploadClient.deleteFile(httpClient, id, phpURL);
	}
	
	public class BackgroundPanel extends JPanel
	{
		
		public BackgroundPanel(MainProcessWindow parent)
		{
			super();
			//this.setMaximumSize(new Dimension(10,10));
			//this.setMinimumSize(new Dimension(10,10));
			this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
			this.setBackground(Color.white);
		}

	}
}