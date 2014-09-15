package jiami;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FileUtil implements Serializable {    

	private static final long serialVersionUID = 1L;

	/**
	 * 删除某个文件夹下的所有文件夹和文件
	 * 
	 * @param delpath
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return boolean
	 */
	public static boolean deletefile(String delpath)
			throws FileNotFoundException, IOException {
		try {

			File file = new File(delpath);
			if (!file.isDirectory()) {
				System.out.println("1");
				file.delete();
			} else if (file.isDirectory()) {
				System.out.println("2");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File delfile = new File(delpath + "" + filelist[i]);
					if (!delfile.isDirectory()) {
						System.out.println("path=" + delfile.getPath());
						System.out.println("absolutepath="
								+ delfile.getAbsolutePath());
						System.out.println("name=" + delfile.getName());
						delfile.delete();
						System.out.println("删除文件成功");
					} else if (delfile.isDirectory()) {
						deletefile(delpath + "" + filelist[i]);
					}
				}
				file.delete();

			}

		} catch (FileNotFoundException e) {
			System.out.println("deletefile() Exception:" + e.getMessage());
		}
		return true;
	}

	/**
	 * 
	 * 读取某个文件夹下的所有文件夹和文件, 返回所有文件名
	 * 
	 * @param filepath
	 *            String
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return Map<Integer, String> pathMap
	 * 
	 */
	public static Map<Integer, String> readfile(String filepath,
			Map<Integer, String> pathMap) throws Exception {
		if (pathMap == null) {
			pathMap = new HashMap<Integer, String>();
		}

		File file = new File(filepath);
		// 文件
		if (!file.isDirectory()) {
			pathMap.put(pathMap.size(), file.getPath());

		} else if (file.isDirectory()) { // 如果是目录， 遍历所有子目录取出所有文件名
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "/" + filelist[i]);
				if (!readfile.isDirectory()) {
					pathMap.put(pathMap.size(), readfile.getPath());

				} else if (readfile.isDirectory()) { // 子目录的目录
					readfile(filepath + "/" + filelist[i], pathMap);
				}
			}
		}
		return pathMap;
	}

	public static String[] file_allfile(String filepath) {

		File file = new File(filepath);
		String[] allfile = file.list();
		return allfile;

	}

}