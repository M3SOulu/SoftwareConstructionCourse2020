package fi.oulu.softwareconstruction.distributedsystems.DemoXML;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOM {
    public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {
        File inputFile = new File("example.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        NodeList people = doc.getElementsByTagName("person");
        System.out.println("----------------------------");
         
        for (int i = 0; i < people.getLength(); i++) {
            Node personNode = people.item(i);
            System.out.println("\nCurrent Element :" + personNode.getNodeName());
            
            if (personNode.getNodeType() == Node.ELEMENT_NODE) {
               Element person = (Element) personNode;
               printNames(person);
               printAge(person);
               printAddress(person);
               printPhoneNumbers(person);
            }
        }
    }

    private static void printAge(Element person) throws DOMException {
        System.out.println("Age: " + getTextElement(person, "age"));
    }

    private static void printNames(Element person) throws DOMException {
        System.out.println("First Name: " + getTextElement(person, "firstname"));
        System.out.println("Last Name: " + getTextElement(person, "lastname"));
    }

    private static void printAddress(Element person) throws DOMException {
        Node address = person.getElementsByTagName("address").item(0);
        if (address == null) {
            System.out.println("Address is missing");
        } else {
            System.out.println("Address:");
            NodeList nodes = address.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(node.getNodeName() + ": " + node.getTextContent());
                }
            }
        }
    }

    private static void printPhoneNumbers(Element person) throws DOMException {
        NodeList phoneNumbers = person.getElementsByTagName("number");
        for (int i = 0; i < phoneNumbers.getLength(); i++) {
            Node number = phoneNumbers.item(i);
            String type = ((Element) number).getAttribute("type");
            System.out.println(type + " phone number: " + number.getTextContent());
        }
    }

    private static String getTextElement(Element person, String name) throws DOMException {
        return person.getElementsByTagName(name).item(0).getTextContent();
    }
}
