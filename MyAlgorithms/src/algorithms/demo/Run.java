package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * Boot class
 * includes the main method
 */
public class Run {

	public static void main(String[] args) throws IOException {
		//Demo.run();
		byte[] arr = new byte[18];
		// size of the maze
		arr[0] = 1;
		arr[1] = 3;
		arr[2] = 3;
		
		// start position
		arr[3] = 0;
		arr[4] = 1;
		arr[5] = 1;
		
		// goal position
		arr[6] = 0;
		arr[7] = 1;
		arr[8] = 1;
		
		// maze data
		arr[9] = 1;
		arr[10] = 1;
		arr[11] = 0;
		arr[12] = 0;
		arr[13] = 1;
		arr[14] = 1;
		arr[15] = 1;
		arr[16] = 1;
		arr[17] = 1;
		
		OutputStream out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(arr);
		out.flush();
		out.close();
		InputStream in = new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte[] b = new byte[18];
		in.read(b);
		in.close();
		Maze3d myMaze = null;
		try {
			myMaze = new Maze3d(b);
		} catch (IndexOutOfBoundsException e) {
		    System.err.println(e.getMessage());
		}
		System.out.print(myMaze);
	}

}
