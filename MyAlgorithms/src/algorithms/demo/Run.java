package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

/**
 * Boot class
 * includes the main method
 */
public class Run {

	public static void main(String[] args) throws IOException {
		//Demo.run();
		byte[] arr = new byte[16];
		// size of the maze
		arr[0] = 7;
		arr[1] = 7;
		arr[2] = 7;
		
		// start position
		arr[3] = 3;
		arr[4] = 2;
		arr[5] = 2;
		
		// goal position
		arr[6] = 4;
		arr[7] = 4;
		arr[8] = 4;
		
		// maze data
		arr[9] = 1;
		arr[10] = 1;
		arr[11] = 0;
		arr[12] = 0;
		arr[13] = 1;
		arr[14] = 1;
		arr[15] = 1;
		
		OutputStream out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(arr);
		out.flush();
		out.close();
		InputStream in = new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte[] b = new byte[16];
		in.read(b);
		in.close();
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i]);
		}
	}

}
