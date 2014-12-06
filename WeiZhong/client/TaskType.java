import java.io.File;

	public class TaskType
	{
		private int operation = 0; //0 list, 1:add, 2:delete
		private File fileHandle;
		private String  fileID;
		
		public TaskType(int op, File file)
		{
			this.operation = op;
			this.fileHandle = file;
		}
		
		public TaskType(int op, String id)
		{
			this.operation = op;
			this.fileID = id;
		}		
		
		public TaskType(int op) //list
		{
			this.operation = op;
		}	
		
		public int getOp() //list
		{
			return this.operation;
		}	
		
		public String getID() //list
		{
			return this.fileID;
		}	
		
		public File getFileHandle() //list
		{
			return this.fileHandle;
		}	
		
	}