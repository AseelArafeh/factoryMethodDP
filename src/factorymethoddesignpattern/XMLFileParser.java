
package factorymethoddesignpattern;

import com.jcabi.xml.*; 
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;



public class XMLFileParser implements FileParser {
    
    public String parseFile(String originPath) throws IOException {

        
        String msg = "";
        try { 
            

            File xmlFile = new File(originPath);
            
            String TXTcontent = "";
            String newestPath = originPath.substring(0,originPath.lastIndexOf(".")).concat(".txt");
            FileWriter file = new FileWriter(newestPath);
 
            //Get Document Builder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();

            //Build Document
            org.w3c.dom.Document xmlDom = docBuilder.parse(xmlFile);
       
            //Normalize the XML Structure; It's just too important !!
            xmlDom.getDocumentElement().normalize();
       
            
            //Here comes the root node
            //Element root = (Element) xmlDom.getDocumentElement();
            //System.out.println(root.getNodeName());
       
            //Get all 
            NodeList nList = xmlDom.getElementsByTagName(xmlDom.getDocumentElement().getNodeName());
            TXTcontent = visitChildNodes(nList);
      
        
            file.write(TXTcontent);
            file.close();
            
            msg = "Parsing DONE succssefully :)";
            
           
        }  catch(FileNotFoundException e) {
            msg = "There is no file with this path";
        }  catch (Exception ex) {
            msg = "Some error occurred. Please try again";
        } 

        return msg;
        
    }
    
    
    
   //This function is called recursively
   private String visitChildNodes(NodeList nList) {
        String txt = "";
       
        for (int temp = 0; temp < nList.getLength(); temp++) {
          
            Node nd = nList.item(temp);
            if (nd.getNodeType() == Node.ELEMENT_NODE) {

                txt += "Type: " + nd.getNodeName().substring(0, 1).toUpperCase() + nd.getNodeName().substring(1, nd.getNodeName().length()) + "\n------------------\n";
                Element element = (Element) nd;
                NodeList inner = element.getChildNodes();
                for (int i = 0; i < inner.getLength(); i++) {
                    Node nde = inner.item(i);
                    if (nde.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) nde;
                        txt += e.getTagName().substring(0, 1).toUpperCase() + e.getTagName().substring(1, e.getTagName().length()) + ": " + e.getTextContent() + "\n";
                    }
                }
            }
        }

        return txt;
   
   }
    
//            
//            //Check all attributes
//            if (node.hasAttributes()) {
//               // get attributes names and values
//               NamedNodeMap nodeMap = node.getAttributes();
//               for (int i = 0; i < nodeMap.getLength(); i++)
//               {
//                   Node tempNode = nodeMap.item(i);
//                   System.out.println("Attr name : " + tempNode.getNodeName()+ "; Value = " + tempNode.getNodeValue());
//               }
//               if (node.hasChildNodes()) {
//                  //We got more childs; Let's visit them as well
//                  visitChildNodes(node.getChildNodes());
//               }
//           }
//         }
//            }
//            
        
}


