package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

	private InputStream in;
	private BufferedInputStream inBuff;
	private final int sizeOfBuffer = 255;
	private int currentBufferSize;
	private int readerIndex;
	
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
		this.inBuff = new BufferedInputStream(this.in, this.sizeOfBuffer);
		this.currentBufferSize = 0;
		this.readerIndex = 0;
	}
	
	@Override
	public int read(byte[] b) throws IOException {
		byte[] readStream = new byte[sizeOfBuffer];
		// Count zero/ones from the array
		while (this.inBuff.read(readStream) > -1) {
			flushBuffer(readStream, b);
		}
		return 0;
	}
	
	/**
	 * Decompress the data from the buffer to the decompressed byte[]
	 * @param compressed the byte[] of the compressed data from the input stream
	 * @param decompressed the byte[] of the decompressed data
	 */
	private void flushBuffer(byte[] compressed, byte[] decompressed) {
		for (int i = 0; i < compressed.length; i+=2) {
			for (int j = 0; j < compressed[i]; j++) {
				decompressed[this.readerIndex] = compressed[i+1];
				this.readerIndex++;
			}
		}
	}

	@Override
	public int read() throws IOException {
		return 0;
	}

}
