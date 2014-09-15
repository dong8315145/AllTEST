package jiami;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

public class XMLDao {

	public XMLDao() {
		super();
		this.xmlFilePath = "/home/terdy/Public/student3.xml";
	}

	public XMLDao(String xmlFilePath) {
		super();
		this.xmlFilePath = xmlFilePath;
	}

	String xmlFilePath = null;

	public void wirte(List<Map<String, String>> list) throws IOException {
		// 创建文档并设置文档的根元素节点 ：第一种方式
		// Document document = DocumentHelper.createDocument();
		//
		// Element root = DocumentHelper.createElement("student");
		//
		// document.setRootElement(root);

		// 创建文档并设置文档的根元素节点 ：第二种方式
		Element root = DocumentHelper.createElement("crc");
		Document document = DocumentHelper.createDocument(root);

		root.addAttribute("name", "vediocrc");

		for (Map<String, String> map : list) {
			Element crcElement = root.addElement(map.get("filename"));
			crcElement.setText(map.get("crc"));
		}

		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.write(document);

		OutputFormat format = new OutputFormat("    ", true);

		XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream(xmlFilePath),
				format);
		xmlWriter2.write(document);
		xmlWriter2.close();

	}

	public List<Map<String, String>> read(List<Map<String, String>> namelist)
			throws DocumentException, ParserConfigurationException,
			SAXException, IOException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(new File(xmlFilePath));
		Element root = doc.getRootElement();
		for (Map<String, String> namemap : namelist) {
			String name = namemap.get("filename");
			map = new HashMap<String, String>();
			map.put("file", name);
			map.put("crc", root.element(name).getText());
			list.add(map);
		}
		// System.out.println("123:"+ root.element( "ABCDEF").getText());
		// System.out.println("abc:"+ root.element( "abcdef").getText());
		return list;
	}

	public boolean[] checkCRC(List<Map<String, String>> namelist)
			throws DocumentException {
		boolean[] booleans = new boolean[namelist.size()];
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(new File(xmlFilePath));
		Element root = doc.getRootElement();
		for (int i = 0, len = booleans.length; i < len; i++) {
			booleans[i] = root.element(namelist.get(i).get("filename"))
					.getText().equals(namelist.get(i).get("crc"));
		}
		return booleans;
	}

}
