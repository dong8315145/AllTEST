package jiami;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;

public class ReadBig {
	// public static String fff = "C:\\mq\\read\\from.xml";
	public static String fff = "/home/terdy/Videos/SERTORTUESNINJASEP101IMX50PAL1-8240.lgym";

	public static void main1(String[] args) throws Exception {

		final int BUFFER_SIZE = 0x300000;// 缓冲区大小为3M

		File f = new File(fff);

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
				.getChannel().map(FileChannel.MapMode.READ_WRITE,
						f.length() / 2, f.length() / 2);

		byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容

		long start = System.currentTimeMillis();

		for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {

			if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {

				for (int i = 0; i < BUFFER_SIZE; i++)

					dst[i] = inputBuffer.get(offset + i);

			} else {

				for (int i = 0; i < inputBuffer.capacity() - offset; i++)

					dst[i] = inputBuffer.get(offset + i);  

			}

			int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
					: inputBuffer.capacity() % BUFFER_SIZE;
		
			
		System.out.println(new String(dst, 0, length).getBytes());// new
			// String(dst,0,length)这样可以取出缓存保存的字符串，可以对其进行操作

		}

		long end = System.currentTimeMillis();

		System.out.println("读取文件文件一半内容花费：" + (end - start) + "毫秒");

	}

	public static void main2(String[] args) throws Exception {
		int bufSize = 1024;
		byte[] bs = new byte[bufSize];
		ByteBuffer byteBuf = ByteBuffer.allocate(1024);
		FileChannel channel = new RandomAccessFile(fff, "r").getChannel();
		while (channel.read(byteBuf) != -1) {
			int size = byteBuf.position();
			byteBuf.rewind();
			byteBuf.get(bs); // 把文件当字符串处理，直接打印做为一个例子。
			System.out.print(new String(bs, 0, size));
			byteBuf.clear();
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(fff));
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}

	// public static void main(String[] args) throws Exception {
	// int bufSize = 1024;
	// byte[] bs = new byte[bufSize];
	// ByteBuffer byteBuf = ByteBuffer.allocate(1024);
	// FileChannel channel = new RandomAccessFile("d:\\filename", "r")
	// .getChannel();
	// while (channel.read(byteBuf) != -1) {
	// int size = byteBuf.position();
	// byteBuf.rewind();
	// byteBuf.get(bs);
	// // 把文件当字符串处理，直接打印做为一个例子。
	// System.out.print(new String(bs, 0, size));
	// byteBuf.clear();
	// }
	// }

}
