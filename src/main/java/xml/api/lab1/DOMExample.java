/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.xml.api.lab1;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author eltntawy
 */
public class DOMExample {

    public DOMExample() throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

        File xmlFile = new File(getClass().getResource("myXML.xml").getPath());
        System.out.println("xml File Path " + xmlFile.getPath());
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder;
        docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(xmlFile);

        NodeList nodeList = doc.getChildNodes();
        Node rootNode = nodeList.item(0);
        NodeList childsNode = rootNode.getChildNodes();
        for (int i = 0; i < rootNode.getChildNodes().getLength(); i++) {

            Node node = childsNode.item(i);
            if (node != null && node.hasChildNodes()) {
                System.out.println("Node is " + node.getNodeName());
                node.replaceChild(doc.createTextNode("Hello DOM API"), node.getFirstChild());
            }

        }

        DOMSource source = new DOMSource(doc);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        StreamResult streamResult = new StreamResult(System.out);
        transformer.transform(source, streamResult);

    }

    public DOMExample(int x) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {

        File xmlFile = new File(getClass().getResource("myXML.xml").getPath());
        System.out.println("xml File Path " + xmlFile.getPath());
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder;
        docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(xmlFile);

        if (x == 1) {
            NodeList nodelist = doc.getElementsByTagName("message");
            for (int i = 0; i < nodelist.getLength(); i++) {

                Node node = nodelist.item(i);
                if (node != null && node.hasChildNodes()) {
                    System.out.println("Node is " + node.getNodeName());
                    node.getChildNodes().item(0).setNodeValue("Hello DOM API - 2");
                }

            }
        } else {
            NodeList nodelist = doc.getElementsByTagName("message");
            for (int i = 0; i < nodelist.getLength(); i++) {

                Node node = nodelist.item(i);
                if (node != null && node.hasChildNodes()) {
                    System.out.println("Node is " + node.getNodeName());
                    node.removeChild(node.getChildNodes().item(0));
                    node.appendChild(doc.createTextNode("Hello DOM API - 3"));
                }

            }
        }

        DOMSource source = new DOMSource(doc);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        StreamResult streamResult = new StreamResult(System.out);
        transformer.transform(source, streamResult);

    }

    public static void main(String args[]) {

        try {
            //new DOMExample();
            new DOMExample(1);
            //new DOMExample(2);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DOMExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DOMExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DOMExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DOMExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
