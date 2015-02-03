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
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author eltntawy
 */
public class DOMCreateTree {

    public DOMCreateTree() throws ParserConfigurationException, SAXException, IOException, TransformerException {
        File xmlFile = new File(getClass().getResource("newTree.xml").getPath());
        System.out.println("xml File Path " + xmlFile.getPath());
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder;
        docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // create nodes
        Element parentNode = doc.createElement("parent");

        parentNode.setAttribute("name", "");
        
        Element childNode = doc.createElement("child");
        childNode.setAttribute("order", "");
        
        Element childNodeName = doc.createElement("name");
        
        childNode.appendChild(childNodeName);
        
        Element jobNode = doc.createElement("job");
        jobNode.setAttribute("title", "");
        
        parentNode.appendChild(childNode);
        parentNode.appendChild(jobNode);
        
        doc.appendChild(parentNode);

        DOMSource source = new DOMSource(doc);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        StreamResult streamResult = new StreamResult(xmlFile);
        transformer.transform(source, streamResult);

    }

    public static void main(String arg[]) {
        try {
            new DOMCreateTree();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMCreateTree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DOMCreateTree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DOMCreateTree.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DOMCreateTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
