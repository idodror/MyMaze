package algorithms.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		Demo.run();
		/*byte[] arr = new byte[8];
		arr[0] = 1;
		arr[1] = 1;
		arr[2] = 1;
		arr[3] = 0;
		arr[4] = 0;
		arr[5] = 1;
		arr[6] = 1;
		arr[7] = 1;
		OutputStream out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		out.write(arr);
		out.flush();
		out.close();
		InputStream in = new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte[] b = new byte[8];
		in.read(b);
		System.out.println();*/
	}

}
