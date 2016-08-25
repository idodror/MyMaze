package io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This class represent compressor for maze3d (from any stream)
 */
public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	private BufferedOutputStream outBuff;
	private final int sizeOfBuffer = 255;
	private int currentBufferSize;
	
	/**
	 * Constructor
	 * @param out OutputStream
	 */
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
		this.outBuff = new BufferedOutputStream(this.out, this.sizeOfBuffer);
		this.currentBufferSize = 0;
	}
	
	/**
	 * This method get a Maze represent by bytes array,
	 * compress it and write it to the stream
	 */
	@Override
	public void write(byte[] b) throws IOException {
		int currentByteValue = b[9];
		// Count zero/ones from the array
		int counter = 1;
		this.outBuff.write(b, 0, 9);
		for (int i = 10; i < b.length; i++) {
			if (b[i] == currentByteValue) {
				counter++;
			}
			else {
				if (!this.canGet2MoreBytes()) {
					this.outBuff.flush();
					this.currentBufferSize = 0;
				}
				this.outBuff.write(counter);
				this.outBuff.write(currentByteValue);
				this.currentBufferSize += 2;
				currentByteValue = b[i];
				counter = 1;
			}
		}
		this.outBuff.write(counter);
		this.outBuff.write(currentByteValue);
		this.outBuff.flush();
	}

	/**
	 * Checks if the buffer ready to get 2 more bytes
	 * @return true if can get
	 * @return false if not
	 */
	private boolean canGet2MoreBytes() {
		if ((sizeOfBuffer - currentBufferSize) >= 2)
			return true;
		else return false;
	}

	/**
	 * this method write byte to the OutputStream out data member
	 * @param arg0 int, the byte to write to the output stream
	 * @throws IOException
	 */
	@Override
	public void write(int arg0) throws IOException {
		this.out.write(arg0);
	}

}
