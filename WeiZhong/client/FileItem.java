
public class FileItem {
	public static String IDNAME = "ID";
	public static String FILENAME = "File Name";
	public static String SIZE = "size";
	public static String OWNER = "Owner";
	public static String TYPE = "type";

    private String id;
    private String fileName;
    private String size;
    private String owner;
    private String type;

    public FileItem(String id, String name, String owner, String size, String type)
    {
    	this.id = id;
    	this.size = size;
    	this.fileName = name;
    	this.owner = owner;
    	this.type = type;
    } 
    
    public String getID(){
    	return this.id;
    }
    public String getFileName(){
    	return this.fileName;
    }    
    public String getSize(){
    	return this.size;
    }    
    public String getOwner(){
    	return this.owner;
    }   
    public String getType(){
    	return this.type;
    }     
}
