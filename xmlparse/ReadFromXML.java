package xmlparse;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

/**
 * 
 * @author chengcheng
 * @time 2016年6月14日 下午8:07:37
 *
 */
/**
 * <?xml version="1.0" encoding="UTF-8"?>

<students name="zhangsan">
    <hello name="lisi">hello Text1</hello>
    <hello name="lisi2">hello Text2</hello>
    <hello name="lisi3">hello Text3</hello>
    <world name="wangwu">world text1</world>
    <world name="wangwu2">world text2</world>
    <world >world text3</world>
	</students>
 *
 */
public class ReadFromXML {
	public static void main(String[] args) throws DocumentException, ParserConfigurationException, SAXException, IOException {
		
		
		SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("students.xml"));

        // 获取根元素
        Element root = document.getRootElement();
        System.out.println("Root: " + root.getName()); //students

        // 获取所有子元素
        List<Element> childList = root.elements();
        System.out.println("total child count: " + childList.size()); //6

        // 获取特定名称的子元素
        List<Element> childList2 = root.elements("hello");
        
        System.out.println("hello child: " + childList2.size()); //3

        // 获取名字为指定名称的第一个子元素
        Element firstWorldElement = root.element("world");
        // 输出其属性
        System.out.println("first World Attr: "
                + firstWorldElement.attribute(0).getName() + "="
                + firstWorldElement.attributeValue("name"));

        System.out.println("迭代输出-----------------------");
        // 迭代输出
        for (Iterator iter = root.elementIterator(); iter.hasNext();)
        {
            Element e = (Element) iter.next();
            
            System.out.println(e.attributeValue("name"));
            System.out.println(e.getText());
        }

        System.out.println("用DOMReader-----------------------");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 注意要用完整类名
        org.w3c.dom.Document document2 = db.parse(new File("students.xml "));

        DOMReader domReader = new DOMReader();

        // 将JAXP的Document转换为dom4j的Document
        Document document3 = domReader.read(document2);

        Element rootElement = document3.getRootElement();

        System.out.println("Root: " + rootElement.getName());
	}
}
