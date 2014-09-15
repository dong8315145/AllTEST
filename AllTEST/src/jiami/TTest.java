package jiami;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.junit.Test;
import org.xml.sax.SAXException;

public class TTest {
	/*
	 * @Test public void test1() {
	 * 
	 * System.out.println(DES3.byte2hex(new byte[] { 32, 65, 100, 42 }));
	 * System.out.println("----------------"); // DES3.encryptMode(keybyte,
	 * src); // DES3.decryptMode(keybyte, src); new DES3().main(null); ; }
	 * 
	 * @Test public void test2() { Encryption en = new Encryption(); String temp
	 * = en.SimpleＥncryption(26, "dong8315145"); en.SimpleDencryption(26, temp);
	 * }
	 * 
	 * @Test public void test3() throws IOException { PostExample example = new
	 * PostExample(); String json = example.bowlingJson(); String response =
	 * example .post(
	 * "http://218.244.131.178:8080/com.conny.test.demo.mac/main/listandroidparameter"
	 * , json); System.out.println(response); }
	 */
	// @Test
	// public void test4() {// 99M文件要46.432秒
	// // String src ="/home/terdy/Pictures";
	// String src = "/media/terdy/0403-0201/.mtv/videos";
	// FileUtil fileUitl = new FileUtil();
	// String[] allfile = fileUitl.file_allfile(src);
	// for (String filename : allfile) {
	// File file = new File(src + "/" + filename);
	// byte[] bytes = new File_Byte().getBytesFromFile(file);
	// System.out.println(filename + ":" + new CRC(bytes));
	// }
	// }

	// @Test
	// public void test5() {
	// String src =
	// "/home/terdy/Videos/SERTORTUESNINJASEP101IMX50PAL1-8240.lgym";
	// // File file = new File(
	// "/media/terdy/0403-0201/.mtv/videos/SERBIGTIMERUSHEP101IMX50PAL169-7A75.lgym");
	// File file = new File(src);
	// byte[] bytes = new File_Byte().getBytesFromFile(file);
	// System.out.println("BOB04MOVXXXXXXXXXXXXXXXXXXXXXX-7598.lgym:"
	// + new CRC(bytes));
	// }
	//
//	@Test
//	public void test6() throws FileNotFoundException, IOException {
//		// String src ="/home/terdy/Pictures/231906uwaagphlptyw2lu0.jpg";
//		String src = "/home/terdy/Videos/SERTORTUESNINJASEP101IMX50PAL1-8240.lgym";
//		MyFile_Byte_BIG mbb = new MyFile_Byte_BIG();
//		CRC32 crc = new CRC32();
//		// String CRC = mbb.getCRC32(mbb.byteList_byte(mbb.test3(src)));
//		String CRC = mbb.getCRC32(src);
//		System.out.println("CRC=" + CRC);
//
//		// System.out.println(new MyFiel_Byte_BIG().crc32(src));
//
//	}

	// @Test
	// public void test7() throws FileNotFoundException, IOException {
	// // String src ="/home/terdy/Pictures/231906uwaagphlptyw2lu0.jpg";
	// String src =
	// "/home/terdy/Videos/SERTORTUESNINJASEP101IMX50PAL1-8240.lgym";
	// MyFiel_Byte_BIG mbb = new MyFiel_Byte_BIG();
	// CRC32 crc = new CRC32();
	// crc.update(mbb.byteList_byte(mbb.test2(src)));
	// System.out.println("CRC" + crc.getValue());
	//
	// //System.out.println(new MyFiel_Byte_BIG().crc32(src));
	//
	// }

	@Test
	public void test7() throws IOException, DocumentException,
			ParserConfigurationException, SAXException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("filename", "ABCDEF");
		map.put("crc", "234567");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("filename", "abcdef");
		map.put("crc", "hijklmn");
		list.add(map);
		XMLDao dao = new XMLDao();
		dao.wirte(list);
		dao.read(namelist)
	}
	// @Test
	// public void test8() {
	// String path =
	// "/home/terdy/Videos/SERTORTUESNINJASEP101IMX50PAL1-8240.lgym";
	// try {
	// new MyFile_Byte_BIG().getByte_From_BigFiel(path);
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

//	@Test
//	public void test9() {
//		int buffer = 1024 * 1024;
//		String path = "/home/terdy/Videos/SERTORTUESNINJASEP101IMX50PAL1-8240.lgym";
//		System.out
//				.println(new kid_crc().nativeGetFileCrc(path, buffer, buffer));
//	}

}
