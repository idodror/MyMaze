package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.search.Maze3dDomain;
import algorithms.search.Searchable;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * Boot class
 * includes the main method
 */
public class Run {
	
	public static void main(String[] args) throws IOException {
		//Demo.run();
		Maze3dGenerator gen = new GrowingTreeGenerator();
		Maze3d maze = gen.generate(5, 5 , 5) ;
		//... generate it   
	// save it to a file
		
	OutputStream out=new MyCompressorOutputStream( new FileOutputStream("1.maz")); 
	out.write( (maze).toByteArray());
	out.flush(); 
	out.close();  
	InputStream in=new MyDecompressorInputStream( new FileInputStream("1.maz")); 
	byte b[]=new byte[ (maze).toByteArray().length];
	in.read(b);
	in.close();  
	Maze3d loaded=new Maze3d(b); 
	System.out.println(loaded.toString());
	System.out.println("another maze!");
	System.out.println(maze.toString());
	System.out.println(loaded.equals(maze));
	}
}
