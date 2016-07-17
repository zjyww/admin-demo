package com.demo.web.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * XmlUtil类
 *
 * @version 1.0
 */
public class XmlUtil {
    /**
     * 方法LogFactory.getLog
     * 
     * @param XmlUtil.class 传入参数
     * @return 返回值=
     */
    private static Log logger = LogFactory.getLog(XmlUtil.class);

    /**
     * 方法docToString
     * 
     * @param document 传入参数
     * @return 返回值String
     */
    public static String docToString(Document document) {
        String s = "";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            OutputFormat format = new OutputFormat("  ", true, "UTF-8");
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            s = out.toString("UTF-8");
        } catch (Exception ex) {
            logger.error("docToString error:" + ex.getMessage());
        }
        return s;
    }

    /**
     * 方法stringToDocument
     * 
     * @param s 传入参数
     * @return 返回值Document
     */
    public static Document stringToDocument(String s) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(s);
        } catch (Exception ex) {
            logger.error("stringToDocument error:" + ex.getMessage());
        }
        return doc;
    }

    /**
     * 方法docToXmlFile
     * 
     * @param document 传入参数
     * @param filename 传入参数
     * @return 返回值boolean
     */
    public static boolean docToXmlFile(Document document, String filename) {
        boolean flag = true;
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileWriter(new File(filename)), format);
            writer.write(document);
            writer.close();
        } catch (Exception ex) {
            flag = false;
            logger.error("docToXmlFile error:" + ex.getMessage());
        }
        return flag;
    }

    /**
     * 方法stringToXmlFile
     * 
     * @param str 传入参数
     * @param filename 传入参数
     * @return 返回值boolean
     */
    public static boolean stringToXmlFile(String str, String filename) {
        boolean flag = true;
        try {
            Document doc = DocumentHelper.parseText(str);
            flag = docToXmlFile(doc, filename);
        } catch (Exception ex) {
            flag = false;
            logger.error("stringToXmlFile error:" + ex.getMessage());
        }
        return flag;
    }

    /**
     * 方法load
     * 
     * @param filename 传入参数
     * @return 返回值Document
     */
    public static Document load(String filename) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            saxReader.setEncoding("UTF-8");
            saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            document = saxReader.read(new File(filename));
        } catch (Exception ex) {
            logger.error("load XML File error:" + ex.getMessage());
        }
        return document;
    }

    /**
     * 方法load
     * 
     * @param file 传入参数
     * @return 返回值Document
     */
    public static Document load(File file) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            saxReader.setEncoding("UTF-8");
            saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            document = saxReader.read(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }

    /**
     * 方法load
     * 
     * @param is 传入参数
     * @return 返回值Document
     */
    public static Document load(InputStream is) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();

            saxReader.setEncoding("UTF-8");
            document = saxReader.read(is);
        } catch (Exception ex) {
            logger.error("load XML File error:" + ex.getMessage());
        }
        return document;
    }

    /**
     * 方法load
     * 
     * @param is 传入参数
     * @param encode 传入参数
     * @return 返回值Document
     */
    public static Document load(InputStream is, String encode) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            saxReader.setEncoding(encode);
            document = saxReader.read(is);
        } catch (Exception ex) {
            logger.error("load XML File error:" + ex.getMessage());
        }
        return document;
    }

    /**
     * 方法styleDocument
     * 
     * @param document 传入参数
     * @param stylesheet 传入参数
     * @return 返回值Document
     */
    public static Document styleDocument(Document document, String stylesheet) throws Exception {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(stylesheet));
        DocumentSource source = new DocumentSource(document);
        DocumentResult result = new DocumentResult();
        transformer.transform(source, result);
        Document transformedDoc = result.getDocument();
        return transformedDoc;
    }

    /**
     * 在某个节点下面插入孩子节点元素
     * 
     * @author nice
     * @param document
     * @param parentElement
     * @param elementTagName
     * @return
     */
    public static Element insertElement(Document document, String parentElementName, String elementTagName) {
        Element root = document.getRootElement();
        Element parentElement = (Element) root.selectSingleNode("//" + parentElementName);
        return parentElement.addElement(elementTagName);
    }

    /**
     * 根据传入的XML和元素标签名称获取到节点列表
     * 
     * @author nice
     * @param xmlStr
     * @param elementTagName
     * @return
     */
    public static List<Node> findChildNodes(String xmlStr, String elementTagName) {
        Document document = XmlUtil.stringToDocument(xmlStr.toString());
        Element root = document.getRootElement();
        List<Node> list = root.selectNodes("//" + elementTagName);
        return list;
    }

}
