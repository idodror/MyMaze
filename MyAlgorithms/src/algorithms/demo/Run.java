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
//		byte[] arr = new byte[18];
//		// size of the maze
//		arr[0] = 1;
//		arr[1] = 3;
//		arr[2] = 3;
//		
//		// start position
//		arr[3] = 0;
//		arr[4] = 1;
//		arr[5] = 1;
//		
//		// goal position
//		arr[6] = 0;
//		arr[7] = 1;
//		arr[8] = 1;
//		
//		// maze data
//		arr[9] = 1;
//		arr[10] = 1;
//		arr[11] = 0;
//		arr[12] = 0;
//		arr[13] = 1;
//		arr[14] = 1;
//		arr[15] = 1;
//		arr[16] = 1;
//		arr[17] = 1;
//		
//		OutputStream out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
//		out.write(arr);
//		out.flush();
//		out.close();
//		InputStream in = new MyDecompressorInputStream(new FileInputStream("1.maz"));
//		byte[] b = new byte[18];
//		in.read(b);
//		in.close();
//		Maze3d myMaze = null;
//		try {
//			myMaze = new Maze3d(b);
//		} catch (IndexOutOfBoundsException e) {
//		    System.err.println(e.getMessage());
//		}
//		
//		System.out.println(myMaze.toByteArray());
//		System.out.print(myMaze);
//	}
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
