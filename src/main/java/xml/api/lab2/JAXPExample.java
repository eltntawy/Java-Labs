
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.xml.api.lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author eltntawy
 */
public class JAXPExample {

    public JAXPExample() throws JAXBException, FileNotFoundException {
        
        File xmlFile = new File(getClass().getResource("XMLDocument.xml").getPath());
        JAXBContext context = JAXBContext.newInstance("main.java.xml.api.lab2");
        
        Unmarshaller unmarshal;
        unmarshal = context.createUnmarshaller();
        
        JAXBElement rootElement = (JAXBElement) unmarshal.unmarshal(xmlFile);
        
        MyMsg myMsg = (MyMsg)rootElement.getValue();
        
        System.out.println("Message Header : "+ myMsg.getHeader());
        
        System.out.println("Message From : "+ myMsg.getMsg().getFrom());
        
        for(String to : myMsg.getMsg().getTo()){
            System.out.println("Message to : "+ to);
        }
        
        System.out.println("Message data : "+ myMsg.getMsg().getData());
        System.out.println("Message body : "+ myMsg.getMsg().getFrom());
        
        
        
        /////////////////////////////////////////////////////////////////////////////
        
        
        
        myMsg.setHeader("New Header");
        
        ObjectFactory factory = new ObjectFactory();
        MyMsg newMyMsg = factory.createMyMsg();
        newMyMsg.setHeader("New Header");
        MsgBody newMyBody = new MsgBody();
        newMyBody.setBody("new BOdy");
        newMyBody.setData("new Data");
        newMyBody.setFrom("new Form");
        newMyMsg.setMsg(newMyBody);
        Marshaller marshaller = context.createMarshaller();
        
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        marshaller.marshal(newMyMsg, new FileOutputStream( "newXml.xml"));
        
    }
    
    
    public static void main(String[] args) {
        try {
            new JAXPExample();
        } catch (JAXBException ex) {
            Logger.getLogger(JAXPExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JAXPExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
