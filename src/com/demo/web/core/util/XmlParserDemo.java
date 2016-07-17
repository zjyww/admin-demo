package com.demo.web.core.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * XmlParserDemo类
 *
 */
public class XmlParserDemo {

    /**
     * 方法parseXml
     * 
     * @param xml 传入参数
     * @return 返回值String[][]
     */
    public static String[][] parseXml(String xml) throws DocumentException {
        String[][] resultArys = null;
        if (StringUtils.isEmpty(xml)) {
            return null;
        }
        Document doc = DocumentHelper.parseText(xml);
        // 获取Row节点集合
        List list = doc.selectNodes("RBSPMessage/Method/Items/Item/Value/Row");
        // 判断节点是否为空同时节点大于2
        if (list != null && list.size() > 2) {
            // 获取第一个Row节点
            Element stateElement = (Element) list.get(0);
            // 获取返回数据状态
            String state = ((Element) stateElement.elements().get(0)).getText();
            if (state.equalsIgnoreCase("000")) {// 状态等于000代表结果返回正常
                Element fieldElement = (Element) list.get(1);
                // 初始化二维数组
                resultArys = new String[list.size() - 2][fieldElement.elements().size()];
                for (int i = 2; i < list.size(); i++) {
                    Element data = (Element) list.get(i);
                    List fields = data.elements();
                    for (int j = 0; j < fields.size(); j++) {
                        Element tmpE = (Element) fields.get(j);
                        // 二维数组赋值
                        resultArys[i - 2][j] = tmpE.getText();
                    }
                }
            }
        }
        return resultArys;
    }

    /**
     * 方法main
     * 
     * @param args 传入参数
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String resultXml = "<?xml version=\"1.0\" encoding=\"GBK\"?>"
                + "<RBSPMessage><Version></Version><ServiceID>C00-00001085"
                + "</ServiceID><TimeStamp></TimeStamp><Validity></Validity>"
                + "<Security><Signature Algorithm=\" \"/>"
                + "<CheckCode Algorithm=\" \"/><Encrypt></Encrypt>"
                + "</Security><Method><Name>Query</Name><Items><Item>"
                + "<Value Type=\"arrayOfArrayOf_string\"><Row><Data>000"
                + "</Data><Data/></Row><Row><Data>XM</Data><Data>SFZH</Data>"
                + "<Data>XB</Data></Row><Row><Data>孙灵燕</Data>"
                + "<Data>513231198301205929</Data><Data>0</Data></Row><Row>"
                + "<Data>蔡思海</Data><Data>320525194401162762</Data><Data>1</Data>"
                + "</Row></Value></Item></Items></Method></RBSPMessage>";
        try {
            String[][] resultArys = XmlParserDemo.parseXml(resultXml);
            for (int i = 0; i < resultArys.length; i++) {
                for (int j = 0; j < resultArys[i].length; j++) {
                    System.out.println(resultArys[i][j]);
                }
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
