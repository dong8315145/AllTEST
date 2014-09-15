package jiami;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DiaoYong_C {

	public void useTerminal() {

		String[] Terminals = new String[2];
		Terminals[0] = "ls";
		Terminals[1] = "/home/terdy";
		try {
			Process process = Runtime.getRuntime().exec(Terminals);
			// 运行的结果输入流
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String strtemp = "";
			while ((strtemp = br.readLine()) != null) {
				strtemp += strtemp;
				strtemp += "\n";
			}
			System.out.println(strtemp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String useC_os() {
		String output = "";
		// android.os.Exec中不包括的android.jar，所以我们需要使用反射。
		Class<?> execClass;
		try {
			execClass = Class.forName("android.os.Exec");

			Method createSubprocess = execClass.getMethod("createSubprocess",
					String.class, String.class, String.class, int[].class);
			Method waitFor = execClass.getMethod("waitFor", int.class);

			// 执行命令。
			// 注意createSubprocess（）是异步的。
			int[] pid = new int[1];
			FileDescriptor fd = (FileDescriptor) createSubprocess.invoke(null,
					"/system/bin/ls", "/sdcard", null, pid);

			// 读取标准输出。
			// 注意您可以使用新的写命令的标准输入
			// 的FileOutputStream（FD）。
			FileInputStream in = new FileInputStream(fd);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));

			try {
				String line;
				while ((line = reader.readLine()) != null) {
					output += line + "/n";
				}
			} catch (IOException e) {
				// 看来，当它到达EOF则抛出IOException。
			}

			// 等待命令完成。
			waitFor.invoke(null, pid[0]);

			return output;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block

			e1.printStackTrace();

		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return output;
	}
}
