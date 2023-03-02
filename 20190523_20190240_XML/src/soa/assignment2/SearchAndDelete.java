/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.assignment2;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import soa.assignment2.Book;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author Lap Smart
 */
public class SearchAndDelete {
   
    /**
     * @param args the command line arguments
     */
    String Attribute;
    public void searchByAttribute(String attribute,String value){
         try {
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse("Books.xml");

            List<Book> Books = new ArrayList<Book>();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    String test = node.getAttributes().getNamedItem("ID").getNodeValue();
                    try{
                     Attribute = elem.getElementsByTagName(attribute)
                           .item(0).getChildNodes().item(0).getNodeValue();
                    }
                    catch(Exception e){
                        Attribute="";
                    }
                    if((Attribute.toLowerCase()).equals(value.toLowerCase()) ||  test.equals(value)){
                        String ID = node.getAttributes().getNamedItem("ID").getNodeValue();
                        String Title = elem.getElementsByTagName("Title").item(0)
                                                    .getChildNodes().item(0).getNodeValue();    
                        String Author = elem.getElementsByTagName("Author").item(0)
                                                    .getChildNodes().item(0).getNodeValue();
                        String Gerne = elem.getElementsByTagName("Gerne").item(0)
                                                    .getChildNodes().item(0).getNodeValue();
                        String Price = elem.getElementsByTagName("Price").item(0)
                                                    .getChildNodes().item(0).getNodeValue();
                        String PublishDate = elem.getElementsByTagName("PublishDate").item(0)
                                                    .getChildNodes().item(0).getNodeValue();
                        String Description = elem.getElementsByTagName("Description").item(0)
                                                    .getChildNodes().item(0).getNodeValue();
                  

                    Books.add(new Book(ID,Author,Title,Gerne,Price,PublishDate,Description));
                }
                    else{
                        continue;
                    }
            }

            }  
            System.out.println("Number of found books = "+Books.size());
            for (Book book1 : Books) {
              
                System.out.println(book1.toString());
                
            }

         
        }catch (Exception e) {
            e.printStackTrace();
        }

        
    }
     
     
    public Boolean IsExist(String id) {
      
            boolean flag=true;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse("Books.xml");

            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    String ID = elem.getAttribute("ID");
                    if(ID.contains(id)){
                        flag = true;
                        break;
                    }
                    else {
                        flag= false;
                    }
                }
            }
            return flag;
        }
    public Boolean DetectSpecialCharacters(String s) {
     
     Pattern p = Pattern.compile("[^A-Za-z]");
     Matcher m = p.matcher(s);
     boolean b = m.find();
     return b;
 }
   
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

boolean DetectDateFormat(String input) {
      boolean isMatch = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((19|2[0-9])[0-9]{2})$")
               .matcher(input)
               .find(); 
      return isMatch;
}



        public void Delete(String ID) {
     
        
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse("Books.xml");
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            Element root=doc.getDocumentElement();
            
            
        for (int i=0;i<nodeList.getLength();++i)
        {
            Node node = nodeList.item(i);
            if (node.getNodeType()==Node.ELEMENT_NODE)
            {
                Element eleme= (Element)node;
                if (eleme.getAttributeNode("ID").getValue().equals(ID))
                {
                   root.removeChild(eleme);
                    System.out.println("Book is deleted Successfully");
                }
            }
        }
         
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(doc),new StreamResult("Books.xml") ); 
} 
}
