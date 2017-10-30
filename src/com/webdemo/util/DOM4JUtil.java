package com.webdemo.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

public class DOM4JUtil {
    // 禁止创建类对象
    private DOM4JUtil() {}

    // student.xml的路径
    public static String xmlFilePath;

    static {
        // 设置student.xml的路径（保存在字节码classes目录下）
        xmlFilePath = DOM4JUtil.class.getResource("/student.xml").getPath();
    }

    // 返回Document对象
    public static Document getDocument() throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(xmlFilePath);
    }

    // 将Document对象写入到文件中
    public static void write2XML(Document document) throws IOException {
        OutputStream out = new FileOutputStream(xmlFilePath);
        XMLWriter writer = new XMLWriter(out, OutputFormat.createPrettyPrint());
        writer.write(document);
        writer.close();
    }
}
