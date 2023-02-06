package com.example.generic_paradigm.xml;

import android.content.Context;

import com.example.generic_paradigm.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLActivity {

    /**
     * Dom解析
     * SAX解析
     *
     * Android特有的Pull解析
     */


    public void domParse(Context context) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(context.getResources().openRawResource(R.raw.student));
        //通过Document对象的getElementByTagName()返回节点的一个list集合
        NodeList studentList = document.getElementsByTagName("student");
        for (int i = 0; i < studentList.getLength(); i++) {
            Student student = new Student();
            // 循环遍历获取每一个student
            Node studentNode = studentList.item(i);
            if (((Element)studentNode).hasAttribute("id")) {
                student.setId(Integer.parseInt(((Element)studentNode).getAttribute("id")));
            }
            // 解析student的子节点
            NodeList childList = studentNode.getChildNodes();
            for (int k = 0; k < childList.getLength(); k++) {

            }
        }


    }

    public void saxParse(){

    }

    public void pullParse(){

    }


}
