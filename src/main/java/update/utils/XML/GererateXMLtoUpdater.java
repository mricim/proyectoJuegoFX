package main.java.update.utils.XML;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class GererateXMLtoUpdater {
    public static void generar(File file, XMLtoUploader XMLtoUploader) throws TransformerException, ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        // root element
        Element root = document.createElement("toUpload");
        document.appendChild(root);

        // set an attribute to staff element
        /*
        Attr attr = document.createAttribute("id");
        attr.setValue("10");
        root.setAttributeNode(attr);
         */

        //you can also use staff.setAttribute("id", "1") for this

        // firstname element
        Element firstName = document.createElement("url");
        firstName.appendChild(document.createTextNode(XMLtoUploader.getUrl()));
        root.appendChild(firstName);

        // lastname element
        Element lastname = document.createElement("versionOld");
        lastname.appendChild(document.createTextNode(XMLtoUploader.getVersionOld()));
        root.appendChild(lastname);

        // email element
        Element email = document.createElement("versionNew");
        email.appendChild(document.createTextNode(XMLtoUploader.getVersionNew()));
        root.appendChild(email);

        // department elements
        Element department = document.createElement("Installer");
        department.appendChild(document.createTextNode(String.valueOf(XMLtoUploader.isInstaller())));
        root.appendChild(department);

        // create the xml file
        //transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);

        // If you use
        // StreamResult result = new StreamResult(System.out);
        // the output will be pushed to the standard output ...
        // You can use that for debugging

        transformer.transform(domSource, streamResult);

        System.out.println("Done creating XML File");
    }

}
