package jiami;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;

public class MyFile_Byte_BIG {
	// 文件路径
	public static String path = "/home/terdy/Videos/SERTORTUESNINJASEP101IMX50PAL1-8240.lgym";
	// 缓冲区大小为3M
	final int BUFFER_SIZE = 0x300000;
	final int CATCH_SIZE = 16;
	static BufferedInputStream bis;

	static BufferedOutputStream bos;
	static FileInputStream fis;
	static FileOutputStream fos;
	static ByteArrayBuffer bab;
	static ByteInputStream byis;

	public List<Byte> getByte_From_BigFiel(String path)
			throws FileNotFoundException, IOException {
		// final int BUFFER_SIZE = 0x300000;// 缓冲区大小为3M
		final int BUFFER_SIZE = 1024 * 512;// 缓冲区大小为3M
		File f = new File(path);
		List<Byte> bytelist = new ArrayList<Byte>();
		/**
		 * 
		 * map(FileChannel.MapMode mode,long position, long size)
		 * 
		 * mode - 根据是按只读、读取/写入或专用（写入时拷贝）来映射文件，分别为 FileChannel.MapMode 类中所定义的
		 * READ_ONLY、READ_WRITE 或 PRIVATE 之一
		 * 
		 * position - 文件中的位置，映射区域从此位置开始；必须为非负数
		 * 
		 * size - 要映射的区域大小；必须为非负数且不大于 Integer.MAX_VALUE
		 * 
		 * 所以若想读取文件后半部分内容，如例子所写；若想读取文本后1/8内容，需要这样写map(FileChannel.MapMode.
		 * READ_ONLY, f.length()*7/8,f.length()/8)
		 * 
		 * 想读取文件所有内容，需要这样写map(FileChannel.MapMode.READ_ONLY, 0,f.length())
		 * 
		 */

		MappedByteBuffer inputBuffer = new RandomAccessFile(f, "rw")
				.getChannel()
				.map(FileChannel.MapMode.READ_WRITE, 0, f.length());

		byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容

		// long start = System.currentTimeMillis();

		for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {

			if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {

				for (int i = 0; i < BUFFER_SIZE; i++) {
					dst[i] = inputBuffer.get(offset + i);
				}
			} else {

				for (int i = 0; i < inputBuffer.capacity() - offset; i++) {

					dst[i] = inputBuffer.get(offset + i);
				}
			}
			// int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ?
			// BUFFER_SIZE
			// : inputBuffer.capacity() % BUFFER_SIZE;
			byte[] temps = new String(dst, 0, 32).getBytes();
			for (byte b : temps) {
				bytelist.add(b);
			}
			// String(dst,0,length)这样可以取出缓存保存的字符串，可以对其进行操作
			bytelist.toArray();

		}

		// long end = System.currentTimeMillis();
		// System.out.println("读取文件文件一半内容花费：" + (end - start) + "毫秒" + "count:"
		// + count);
		return bytelist;
	}

	public byte[] byteList_byte(List<Byte> bytelist) {
		int len = bytelist.size();
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			bytes[i] = bytelist.get(i);
		}
		return bytes;
	}

	public void usecheckInPutStream(String path) {
		try {
			CheckedInputStream cis = new CheckedInputStream(
					new FileInputStream(path), new CRC32());
			long l = cis.getChecksum().getValue();
			System.out.println("L" + l);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String crc32(String path) {

		CRC32 crc32 = new CRC32();
		FileInputStream fileinputstream = null;
		CheckedInputStream checkedinputstream = null;
		String crc = null;
		try {
			fileinputstream = new FileInputStream(new File(path));
			checkedinputstream = new CheckedInputStream(fileinputstream, crc32);
			while (checkedinputstream.read() != -1) {
			}
			crc = Long.toHexString(crc32.getValue()).toUpperCase();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileinputstream != null) {
				try {
					fileinputstream.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			if (checkedinputstream != null) {
				try {
					checkedinputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return crc;
	}

	public List<Byte> test1(String path) throws IOException {
		final long jump = 1024 * 512 - 32;
		final int buffer = 32;
		List<Byte> bytelist = new ArrayList<Byte>();
		byte[] bytes = new byte[buffer];
		// char[] temps = new char[buffer];
		FileInputStream fis = new FileInputStream(new File(path));
		BufferedInputStream bis = new BufferedInputStream(fis);
		// InputStreamReader isr = new InputStreamReader(fis);
		// BufferedReader br = new BufferedReader(isr, buffer);
		// ByteInputStream byteis = new ByteInputStream(bytes, buffer);
		while (bis.read(bytes) != -1) {
			for (byte b : bytes) {
				bytelist.add(b);
			}
			bis.skip(jump);
		}
		bis.close();
		fis.close();
		return bytelist;
	}

	public List<Byte> test2(String path) throws IOException {
		final long jump = 1024 * 16 - 4;// 3M
		long jtemp = 0;
		List<Byte> bytelist = new ArrayList<Byte>();
		byte[] bytes = new byte[4];
		RandomAccessFile raf = new RandomAccessFile(new File(path), "r");
		while (raf.read(bytes) != -1) {
			for (byte b : bytes) {
				bytelist.add(b);
			}
			jtemp += jump;
			raf.seek(jtemp);
		}
		raf.close();
		return bytelist;
	}

	public List<Byte> test3(String path) throws IOException {
		final int BUFFER_SIZE = 0x300000;// 缓冲区大小为1M
		int CATCH_SIZE = 32;
		File f = new File(path);
		List<Byte> bytelist = new ArrayList<Byte>();
		MappedByteBuffer inputBuffer = new RandomAccessFile(f, "r")
				.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, f.length());
		byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容
		long start = System.currentTimeMillis();
		for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {

			if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {

				for (int i = 0; i < BUFFER_SIZE; i++) {
					dst[i] = inputBuffer.get(offset + i);
				}
			} else {

				for (int i = 0; i < inputBuffer.capacity() - offset; i++) {

					dst[i] = inputBuffer.get(offset + i);
				}
			}
			int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
					: inputBuffer.capacity() % BUFFER_SIZE;
			for (int i = 0; i < CATCH_SIZE; i++) {
				bytelist.add(dst[i]);
			}
		}
		return bytelist;
	}

	public String getCRC32(byte[] bytes) {
		int size = 1024 * 1024;
		CRC32 crc32 = new CRC32();
		InputStream in = null;
		CheckedInputStream checkedinputstream = null;
		String crc = null;
		try {
			in = new ByteInputStream(bytes, bytes.length);
			checkedinputstream = new CheckedInputStream(in, crc32);
			byte[] bufferbyte = new byte[size];
			while (checkedinputstream.read() != -1) {
			}
			crc = Long.toHexString(crc32.getValue());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			if (checkedinputstream != null) {
				try {
					checkedinputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return crc;
	}

	public String getCRC32(String path) {
		CRC32 crc32 = new CRC32();
		InputStream in = null;
		CheckedInputStream checkedinputstream = null;
		String crc = null;
		try {
			in = new FileInputStream(path);
			checkedinputstream = new CheckedInputStream(in, crc32);
			byte[] bufferbyte = new byte[1024];
			while (checkedinputstream.read() != -1) {
			}
			crc = Long.toHexString(crc32.getValue());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			if (checkedinputstream != null) {
				try {
					checkedinputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return crc;
	}
}
