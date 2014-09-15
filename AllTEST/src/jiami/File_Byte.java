package jiami;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class File_Byte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static int mybyte = 1024 * 1024;
	static BufferedOutputStream bos;
	static BufferedInputStream bis;
	static FileInputStream fis;
	static ByteArrayOutputStream byteos;

	/**
	 * 获取当前文件夹下的全部子文件
	 * 
	 * @param file
	 * @return
	 */

	public static String[] file_allfile(String filepath) {

		File file = new File(filepath);
		String[] allfile = file.list();
		return allfile;
	}

	/**
	 * 文件转化为字节数组
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getBytesFromFile(File file) {
		byte[] ret = null;
		try {
			if (file == null) {
				// log.error("helper:the file is null!");
				return null;
			}
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			byteos = new ByteArrayOutputStream(mybyte);
			bos = new BufferedOutputStream(byteos);

			byte[] b = new byte[mybyte];
			int n;
			while ((n = fis.read(b)) != -1) {
				byteos.write(b, 0, n);
			}
			fis.close();
			byteos.close();
			ret = byteos.toByteArray();
		} catch (IOException e) {
			// log.error("helper:get bytes from file process error!");
			e.printStackTrace();
		}
		// 新添加的缩小文件方法
		ret = getShotBytes(ret);
		return ret;
	}

	public static byte[] getBytesFromFile(String src) {
		File file = new File(src);
		byte[] ret = null;
		try {
			if (file == null) {
				// log.error("helper:the file is null!");
				return null;
			}
			fis = new FileInputStream(file);
			byteos = new ByteArrayOutputStream(mybyte);
			byte[] b = new byte[mybyte];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			ret = byteos.toByteArray();
		} catch (IOException e) {
			// log.error("helper:get bytes from file process error!");
			e.printStackTrace();
		}
		// 新添加的缩小文件方法
		ret = getShotBytes(ret);
		return ret;
	}

	/**
	 * 把字节数组保存为一个文件
	 * 
	 * @param b
	 * @param outputFile
	 * @return
	 */
	public static File getFileFromBytes(byte[] b, String outputFile) {
		File ret = null;
		BufferedOutputStream stream = null;
		try {
			ret = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(ret);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			// log.error("helper:get file from byte process error!");
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// log.error("helper:get file from byte process error!");
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	/**
	 * 把文件减少为原来的１byte/1M
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getShotBytes(byte[] bytes) {
		final int K = 1024 * 1024 * 16;// 16M
		List<Byte> bytelist = new ArrayList<Byte>();
		int leng = bytes.length;
		int start = 0, end = 15;
		while (start < leng && end < leng) {
			for (; start <= end; start++) {
				bytelist.add(bytes[start]);
			}
			start += K;
			end += K;
		}
		int I = bytelist.size();
		byte[] temps = new byte[I];
		for (int i = 0; i < I; i++) {
			temps[i] = bytelist.get(i);
		}
		return temps;
	}

}
