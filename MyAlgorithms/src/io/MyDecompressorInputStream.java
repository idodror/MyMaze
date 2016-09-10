package io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * This class represent decompressor for maze3d (from any stream)
 */
public class MyDecompressorInputStream extends InputStream {

	private InputStream in;
	private BufferedInputStream inBuff;
	private final int sizeOfBuffer = 254;
	private int readerIndex;		// index of next free space in the byte[]
	private int currentReadSize;	// size of last read from stream to the buffer
	
	/**
	 * Constructor
	 * @param in InputStream
	 */
	public MyDecompressorInputStream(InputStream in) {
		this.in = in;
		this.inBuff = new BufferedInputStream(this.in, this.sizeOfBuffer);
		this.readerIndex = 0;
		this.currentReadSize = 0;
	}
	
	/**
	 * read with buffered input stream from any stream to array of bytes
	 * @param b byte[]
	 * @throws IOException
	 */
	@Override
	public int read(byte[] b) throws IOException {
		byte[] readStream = new byte[9];
		// Read: size of maze dimensions + start position + goal position
		this.inBuff.read(readStream, 0, 9);
		System.arraycopy(readStream, 0, b, 0, 9);
		this.readerIndex += 9;
		
		readStream = new byte[sizeOfBuffer];	// clear the readStream and increase it
		
		while ((this.currentReadSize = this.inBuff.read(readStream)) > -1) {
			flushBuffer(readStream, b);
		}
		return readerIndex;		// the amount of bytes readed
	}
	
	/**
	 * Decompress the data from the buffer to the decompressed byte[]
	 * @param compressed the byte[] of the compressed data from the input stream
	 * @param decompressed the byte[] of the decompressed data
	 */
	private void flushBuffer(byte[] compressed, byte[] decompressed) {
		for (int i = 0; i < this.currentReadSize; i+=2) {
			for (int j = 0; j < compressed[i]; j++) {
				decompressed[this.readerIndex] = compressed[i+1];
				this.readerIndex++;
			}
		}
	}

	@Override
	public int read() throws IOException {
		byte[] sizeOfData = new byte[4];
		this.inBuff.read(sizeOfData);
		return new BigInteger(sizeOfData).intValue();
	}

}
