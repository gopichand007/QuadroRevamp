package genericLib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class WriteTextfile {
	
	
	
	public static void writeTextFile(String Filepath, String output) throws FileNotFoundException{
	
	
	
	File file = new File(Filepath);
	FileOutputStream fos = new FileOutputStream(file);
	PrintStream ps = new PrintStream(fos);
	PrintStream console = System.out;
	System.setOut(ps);
	System.out.println(output);
	System.setOut(console);
	
	}
	
	public static void main(String args[]) throws FileNotFoundException{
		WriteTextfile file = new WriteTextfile();
		file.writeTextFile("C://Users/gchand/Documents//out.txt", "output");
		
		
	}
}


