package main.java.test.update.utils.XML;

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

        //url
        Element firstName = document.createElement("url");
        firstName.appendChild(document.createTextNode(XMLtoUploader.getUrl()));
        root.appendChild(firstName);

        //versionOld
        Element lastname = document.createElement("versionOld");
        lastname.appendChild(document.createTextNode(XMLtoUploader.getVersionOld()));
        root.appendChild(lastname);

        //versionNew
        Element email = document.createElement("versionNew");
        email.appendChild(document.createTextNode(XMLtoUploader.getVersionNew()));
        root.appendChild(email);

        //is Installer?
        Element department = document.createElement("installer");
        department.appendChild(document.createTextNode(String.valueOf(XMLtoUploader.isInstaller())));
        root.appendChild(department);
        //md5
        Element md5 = document.createElement("md5");
        md5.appendChild(document.createTextNode(String.valueOf(XMLtoUploader.getMd5())));
        root.appendChild(md5);
        //path
        Element path = document.createElement("path");
        path.appendChild(document.createTextNode(String.valueOf(XMLtoUploader.getPath())));
        root.appendChild(path);
        //fileName
        Element fileName = document.createElement("fileName");
        fileName.appendChild(document.createTextNode(String.valueOf(XMLtoUploader.getFileName())));
        root.appendChild(fileName);

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
        /*
        File file = new File(PATH + "/bin/toUpdater.xml");
        System.out.println(file.getAbsolutePath());
        try {
            Document dowloadPrincipal = ListWeb.parseFile(WEB_DOWNLOADS, "list.html");
            XMLtoUploader toXML = CallUpdater.chekUpdateMajor(dowloadPrincipal);
            //TODO XMLtoUploader toXML = null;
            if (toXML == null) {
                System.out.println("SUBVERSION");
                toXML = CallUpdater.chekUpdateMinor(dowloadPrincipal);
            }
            if (toXML != null) {
                System.out.println(toXML);
                GererateXMLtoUpdater.generar(file, toXML);
                try {
                    (new Thread() {
                        public void run() {
                            try {
                                Runtime.getRuntime().exec("cmd /c "+PATH+"/bin/updater.exe", null, new File(PATH+"/bin\\"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+PATH+"/bin/updater.exe", null, new File(PATH+"/bin\\"));
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("NO UPDATES");
                //TODO TODO CORRECTO
            }
        } catch (IOException | ParserConfigurationException | TransformerException e) {
            //} catch (IOException e) {
            e.printStackTrace();
            //TODO LANZAR ERROR
        }
*/
}
