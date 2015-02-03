/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.xml.api.lab1;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author eltntawy
 */
public class SAXTraverse {

    Stack<String> stack = new Stack<String>();

    public SAXTraverse() throws ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException, IOException {
        File xmlFile = new File(getClass().getResource("myXML.xml").getPath());
        System.out.println("xml File Path " + xmlFile.getPath());
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();

        SAXParser sAXParser = parserFactory.newSAXParser();

        sAXParser.parse(xmlFile, new DefaultHandler() {

            boolean isElementValue = false;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

                isElementValue = true;
                System.out.println("element : " + qName);
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {

                isElementValue = false;

            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {

                System.out.println("value : " + new String(ch, start, length));

                stack.push(new String(ch, start, length));
            }

        }
        );

        for (String s : stack) {
            System.out.println(s);
        }
    }

    public static void main(String args[]) {
        try {
            new PrintXMLWithSAX();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PrintXMLWithSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(PrintXMLWithSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(PrintXMLWithSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintXMLWithSAX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
