package jiami;

/*
 * 用于网络的简单加密
 * 从网络获取一个ＫＥＹ，
 * 根据这个ＫＥＹ再实现移位
 */
public class Encryption {

	public static int publicKey = (int) (Math.random() * 100);

	// /加密方法

	public String SimpleＥncryption(int key, String encryption) {

		int len = encryption.length();
		char[] encryptions = new char[len];
		encryptions = encryption.toCharArray();
		encryption = "";
		for (int i = 0; i < len; i++) {
			encryptions[i] = (char) (encryptions[i] + key);
			// //System.out.println(encryptions[i]);
			encryption = encryption + encryptions[i];
		}
		// System.out.println(encryption);
		return encryption;
	}

	public String SimpleＥncryption(String encryption) {
		int len = encryption.length();
		char[] encryptions = new char[len];
		encryptions = encryption.toCharArray();
		encryption = "";
		for (int i = 0; i < len; i++) {
			encryptions[i] = (char) (encryptions[i] + publicKey);
			// //System.out.println(encryptions[i]);
			encryption = encryption + encryptions[i];
		}
		// System.out.println(encryption);
		return encryption;
	}

	// 解密方法

	public String SimpleDencryption(int key, String encryption) {

		int len = encryption.length();
		char[] encryptions = new char[len];
		encryptions = encryption.toCharArray();
		encryption = "";
		for (int i = 0; i < len; i++) {
			encryptions[i] = (char) (encryptions[i] - key);
			// //System.out.println(encryptions[i]);
			encryption = encryption + encryptions[i];
		}
		// System.out.println(encryption);
		return encryption;
	}

	public String SimpleDencryption(String encryption) {

		int len = encryption.length();
		char[] encryptions = new char[len];
		encryptions = encryption.toCharArray();
		encryption = "";
		for (int i = 0; i < len; i++) {
			encryptions[i] = (char) (encryptions[i] - publicKey);
			// //System.out.println(encryptions[i]);
			encryption = encryption + encryptions[i];
		}
		// System.out.println(encryption);
		return encryption;
	}
}
