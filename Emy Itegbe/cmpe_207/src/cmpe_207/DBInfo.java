/*
 *  Emy Itegbe
 *  Group Budweiser
 *  CMPE 207
 *  This file holds the information for the list of files
 *  matches the information to that of the database.
 *  It holds parameters of strings and this later stored in an
 *  arrayList .
 */

package cmpe_207;

public class DBInfo {
	
	//variables fields for files stored in database..
    private String id;
    private String file_name;
    private String file_size;
    private String file_owner;
    private String type;

    //Constructor
    public DBInfo(String id, String name, String owner, String size, String type)
    {
    	this.id = id;
    	this.file_size = size;
    	this.file_name = name;
    	this.file_owner = owner;
    	this.type = type;
    } 
    
    //returns the variables 
    public String getIdNo(){
    	return this.id;
    }
    public String getFileName(){
    	return this.file_name;
    }    
    public String getSizeOfFile(){
    	return this.file_size;
    }    
    public String getFileCreator(){
    	return this.file_owner;
    }   
    public String getFileType(){
    	return this.type;
    }     
}