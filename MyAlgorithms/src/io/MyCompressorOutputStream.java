package io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	private BufferedOutputStream outBuff;
	private final int sizeOfBuffer = 255;
	private int currentBufferSize;
	
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
		int currentByteValue = b[0];
		// Count zero/ones from the array
		int counter = 1;
		
		for (int i = 1; i < b.length; i++) {
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

	@Override
	public void write(int arg0) throws IOException {
		this.out.write(arg0);
	}

	public int getCurrentBufferSize() {
		return currentBufferSize;
	}

}
