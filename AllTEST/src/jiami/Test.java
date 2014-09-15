package jiami;

import java.util.Locale;
import java.util.zip.CRC32;

public class Test {
	public static void main(String[] args) {
		String str = "ABCDEF";
		byte[] bytestr = str.getBytes();
		long start;
		start = System.currentTimeMillis();
		CRC32 crc32 = new CRC32();
		crc32.update(bytestr);
		System.out.println(String.format(Locale.US, "%x", crc32.getValue()));
		System.out.println(System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		System.out.println(new MyCRC().getCRC32(bytestr));
		System.out.println(System.currentTimeMillis() - start);

	}

}
